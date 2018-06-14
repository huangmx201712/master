package com.zheng.demo.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * kafka消费者
 */
public class TestKafkaConsumer {
    /**
     * 当某一用户组的的用户数n大于分区数m，则有m-n个用户不起作用收不到消息
     *
     */


    public static void main(String[] args) {
       /* Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092,127.0.0.1:9093");
        //指定消费者组
        props.put("group.id", "test");

        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //consumer.assign(Arrays.asList(new TopicPartition("testtopic", 0)));
        consumer.subscribe(Arrays.asList("sinktopic"));
        //定义主题topic
       // consumer.subscribe(Arrays.asList("foo", "bar"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("sinktopic>>>offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }*/

        //自动提交时我了保证消费成功，即消息带来的附加操作的成功执行

       /* Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic","testtopic"));
        final int minBatchSize = 1;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                // insertIntoDb(buffer);
                System.out.println(buffer.toString());
                //手动提交同步，告诉分区当前用户组的offset往后移动一位
                consumer.commitSync();
                buffer.clear();
            }
        }*/


        /**
         * 读取完分区的所有消息后，手动提交一个偏移量给当前分区
         *已提交的offset应始终是你的程序将读取的下一条消息的offset。因此，调用commitSync（offsets）时，你应该加1个到最后处理的消息的offset。
         */
/*        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092,127.0.0.1:9093");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.assign(Arrays.asList(new TopicPartition("testtopic", 0)));
       // consumer.subscribe(Arrays.asList("testtopic"));

         Boolean running=true;
        try {
            while(running) {
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        System.out.println(record.offset() +":"+record.key()+ ": " + record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } finally {
            consumer.close();
        }*/

        /**
         * 手动调整分区（用于限定消费者组读取哪些分区，用于个性化的分区处理，即便除了问题，该区域也不会进行分组协调）
         *
         消费者分组仍需要提交offset，只是现在分区的设置只能通过调用assign修改，因为手动分配不会进行分组协调，
         因此消费者故障不会引发分区重新平衡。每一个消费者是独立工作的（即使和其他的消费者共享GroupId）。
         为了避免offset提交冲突，通常你需要确认每一个consumer实例的gorupId都是唯一的。

         手动分配分区（即，assgin）和动态分区分配的订阅topic模式（即，subcribe）不能混合使用
         */
       /* String topic = "foo";
        TopicPartition partition0 = new TopicPartition(topic, 0);
        TopicPartition partition1 = new TopicPartition(topic, 1);
        consumer.assign(Arrays.asList(partition0, partition1));*/
    }



}
