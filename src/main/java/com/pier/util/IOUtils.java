package com.pier.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @auther zhongweiwu
 * @date 2019/4/29 11:27
 */
public class IOUtils {

    private static final String srcPath = "D:\\controller.log";
    private static final String dstPath = "D:\\controller.log.bak";

    public static void copyByNIOTransfer(String srcPath, String dstPath) {

        try (
             FileInputStream fis = new FileInputStream(srcPath);
             FileOutputStream fos = new FileOutputStream(dstPath);
             FileChannel fisChannel = fis.getChannel();
             FileChannel fosChannel = fos.getChannel()
        ){
            long len = fisChannel.transferTo(0, fisChannel.size(), fosChannel);
            System.out.println("has writen " + len + " bytes!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        copyByNIOTransfer(srcPath, dstPath);
    }
}
