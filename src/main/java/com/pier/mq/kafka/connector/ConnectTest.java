package com.pier.mq.kafka.connector;

import org.apache.kafka.connect.cli.ConnectStandalone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther zhongweiwu
 * @date 2019/5/17 19:41
 */
public class ConnectTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectTest.class);

    public void testConnectStandalone() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //String workerConfigFile = classLoader.getResource("config\\connect-standalone.properties").toString();
        //String sourceConnectFile = classLoader.getResource("config\\connect-file-source.properties").toString();
        //String sinkConnectFile = classLoader.getResource("config\\connect-file-sink.properties").toString();
        String workerConfigFile = "D:\\SpringTest\\src\\main\\java\\com\\pier\\mq\\kafka\\connector\\config\\connect-standalone.properties";
        String sourceConnectFile = "D:\\SpringTest\\src\\main\\java\\com\\pier\\mq\\kafka\\connector\\config\\connect-file-source.properties";
        String sinkConnectFile = "D:\\SpringTest\\src\\main\\java\\com\\pier\\mq\\kafka\\connector\\config\\connect-file-sink.properties";
        String[] args = {workerConfigFile, sourceConnectFile, sinkConnectFile};
        try {
            ConnectStandalone.main(args);
        } catch (Exception e) {
            LOGGER.error("error ", e);
        }
    }

    public static void main(String[] args) {
        new ConnectTest().testConnectStandalone();
    }

}