package com.pier.mq.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @auther zhongweiwu
 * @date 2019/3/13 17:25
 */
public class Consumer extends AbstractListener {

    @Override
    public void consume(Message message, Channel channel) throws Exception {
        System.out.println("message:" + new String(message.getBody()) + ", id:" + message.getMessageProperties().getMessageId());
    }

    @Override
    protected void onFailure(Message message, Channel channel, Exception e) {

    }
}
