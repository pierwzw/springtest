package com.pier.mq.kafka.serialize.deserializer;

import com.pier.bean.User;
import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @auther zhongweiwu
 * @date 2019/5/6 16:11
 */
public class UserDeserializer implements Deserializer {

    private static Logger log = LoggerFactory.getLogger(UserDeserializer.class);

    private ObjectMapper objectMapper;

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        User user;
        try{
            user = objectMapper.readValue(data, User.class);
            return user;
        } catch (Exception e){
            log.error("failed to deserialize the user data: {}", data, e);
        }
        return null;
    }

    @Override
    public void close() {

    }
}
