package com.pier.mq.rabbitmq.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @auther zhongweiwu
 * @date 2019/3/13 17:13
 */
@Service
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void init(){
        rabbitTemplate.setMandatory(true);
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                    System.out.println("ack: " + ack + ". correlationData: " + correlationData + "cause : " + cause);
                }
            });
        }
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("text: " + replyText + " code: " + replyCode + " exchange: " + exchange + " routingKey :" + routingKey);
            }
        });
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(message);
    }
}
