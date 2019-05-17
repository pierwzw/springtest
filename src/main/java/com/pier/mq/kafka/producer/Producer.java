package com.pier.mq.kafka.producer;

import com.pier.mq.kafka.KafkaProperties;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @auther zhongweiwu
 * @date 2019/2/25 9:54
 */
public class Producer extends Thread{

    private String topic;
    private KafkaProducer<String,String> producer;

    public Producer(String topic) {
        this.topic = topic;
        Properties properties = new Properties();
        properties.put("bootstrap.servers",KafkaProperties.broker);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义序列化器
        //properties.put("value.serializer", "com.pier.mq.kafka.serialize.serializer.UserSerializer");
        // 实现自定义分区器
        //properties.put("partitioner.class", "com.pier.mq.kafka.producer.AuditPartitioner");
        // 启用幂等
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        // 单个broker连接在任意某个时刻只处理一个请求，若管道中存在未完成的请求，producer不会发送新的请求，该设置可以防止消息乱序
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true){
            String message = "message" + messageNo;
            System.out.println("send = "+message);
            // metadata和e至少有一个为空
            producer.send(new ProducerRecord<>(topic, message), (recordMetadata, e) -> {
                if (e != null){
                    e.printStackTrace();
                }
                if (recordMetadata != null){
                    System.out.println(message + " has sent successfully!");
                }
            });
            messageNo ++;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new Producer(KafkaProperties.topic).start();
    }
}
