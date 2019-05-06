package com.pier.util;

import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther zhongweiwu
 * @date 2019/3/20 11:27
 */
public class CheckDuplicate {

    private static Logger log = Logger.getLogger(CheckDuplicate.class);

    public static void checkDuplicate(String path, boolean failOnError) {
        try {
            // search in caller's classloader
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
            Set<String> files = new HashSet<String>();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String file = url.getFile();
                    if (file != null && file.length() > 0) {
                        files.add(file);
                    }
                }
            }
            // duplicated jar is found
            if (files.size() > 1) {
                String error = "Duplicate class " + path + " in " + files.size() + " jar " + files;
                if (failOnError) {
                    throw new IllegalStateException(error);
                } else {
                    log.error(error);
                }
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }
}
