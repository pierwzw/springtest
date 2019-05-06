package com.pier.mq.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @auther zhongweiwu
 * @date 2019/4/30 15:56
 */
public class AuditPartitioner implements Partitioner {

    private Random random;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String tmpkey = (String)key;
        List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(topic);
        int partitionCount = partitionInfoList.size();
        // 分区号从0开始，此处取最后一个分区号
        int auditPartition = partitionCount - 1;
        return key == null || ((String) key).isEmpty() || !((String) key).contains("audit")?
        random.nextInt(partitionCount - 1) : auditPartition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        random = new Random();
    }
}
