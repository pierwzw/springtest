package com.pier.mq.kafka.serialize.serializer;

import com.pier.mq.kafka.consumer.Consumer;
import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @auther zhongweiwu
 * @date 2019/5/6 15:55
 */
public class UserSerializer implements Serializer {

    private static Logger log = LoggerFactory.getLogger(UserSerializer.class);

    private ObjectMapper objectMapper;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] ret = null;
        try {
            ret = objectMapper.writeValueAsString(data).getBytes("utf-8");
        } catch (Exception e) {
            log.error("failed to serialize the object: {}", data, e);
        }
        return ret;
    }

    @Override
    public void close() {

    }
}
