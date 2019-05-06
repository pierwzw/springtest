package com.pier.mq.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @auther zhongweiwu
 * @date 2019/3/13 17:25
 */
public abstract class AbstractListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            consume(message, channel);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            onFailure(message, channel, e);
        } finally {
            cleanUp(message, channel);
        }
    }

    @Override
    public void onMessage(Message message){

    }

    protected abstract void consume(Message message, Channel channel) throws Exception;

    protected abstract void onFailure(Message message, Channel channel, Exception e);

    protected void cleanUp(Message message, Channel channel) {}
}
