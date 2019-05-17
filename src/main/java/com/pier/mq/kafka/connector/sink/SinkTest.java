package com.pier.mq.kafka.connector.sink;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.file.FileStreamSinkTask;
import org.apache.kafka.connect.sink.SinkRecord;
import org.aspectj.lang.annotation.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class SinkTest {

    private FileStreamSinkTask task;
    private ByteArrayOutputStream os;
    private PrintStream printStream;

    //@Before
    public void setup() {
        os = new ByteArrayOutputStream();
        printStream = new PrintStream(os);
        task = new FileStreamSinkTask(printStream);
    }

    //@Test
    public void testPutFlush() {
        HashMap<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        final String newLine = System.getProperty("line.separator");

        // We do not call task.start() since it would override the output stream

        task.put(Arrays.asList(
                new SinkRecord("topic1", 0, null, null, Schema.STRING_SCHEMA, "line1", 1)
        ));
        offsets.put(new TopicPartition("topic1", 0), new OffsetAndMetadata(1L));
        task.flush(offsets);
        //assertEquals("line1" + newLine, os.toString());

        task.put(Arrays.asList(
                new SinkRecord("topic1", 0, null, null, Schema.STRING_SCHEMA, "line2", 2),
                new SinkRecord("topic2", 0, null, null, Schema.STRING_SCHEMA, "line3", 1)
        ));
        offsets.put(new TopicPartition("topic1", 0), new OffsetAndMetadata(2L));
        offsets.put(new TopicPartition("topic2", 0), new OffsetAndMetadata(1L));
        task.flush(offsets);
        //assertEquals("line1" + newLine + "line2" + newLine + "line3" + newLine, os.toString());
    }
}
