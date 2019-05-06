package com.pier.mq.kafka.consumer;

import com.pier.mq.kafka.KafkaProperties;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @auther zhongweiwu
 * @date 2019/2/25 9:57
 */
public class Consumer extends Thread{

    private Logger log = Logger.getLogger(Consumer.class);

    KafkaConsumer<String, String> consumer;

    public Consumer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KafkaProperties.broker);
        /** 群组id可以省略，但是不常见 */
        properties.setProperty("group.id","test");
        // 默认使用的是自动提交
        properties.put("enable.auto.commit", false);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 自定义序列化器
        //properties.put("value.serializer", "com.pier.mq.kafka.serialize.serializer.UserDeserializer");
        consumer = new KafkaConsumer<>(properties);
        /** topics可以为正则表达式 */
        consumer.subscribe(Arrays.asList(KafkaProperties.topic));
    }

    @Override
    public void run() {
        try{
            while (true){
                consumer.close();
                ConsumerRecords<String,String> records = consumer.poll(100);
                for (ConsumerRecord<String,String> recode: records) {
                    System.out.println("recodeOffset = " + recode.offset() + "recodeValue = " + recode.value());
                    // 手动提交偏移量
                    //consumer.commitSync();
                }
                // 异步手动提交偏移量
                consumer.commitAsync((offsets, e) -> {
                    if (e != null){
                        log.error("commit failed for offset " + offsets, e);
                    }
                });
            }
        }catch (Exception e){
            log.error("unexpected error", e);
        }finally {
            try{
                // 手动提交会一直重试
                consumer.commitSync();
            }finally {
                consumer.close();
            }
        }

    }
    public static void main(String[] args){
        new Consumer().start();
    }
}
