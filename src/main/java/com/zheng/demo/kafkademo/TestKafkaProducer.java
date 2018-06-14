package com.zheng.demo.kafkademo;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 用于测试kafka
 */
public class TestKafkaProducer {


    /**
        batch.size: 当多个消息要发送到相同分区的时，生产者尝试将消息批量打包在一起，以减少请求交互。这样有助于客户端和服务端的性能提升。
         该配置的默认批次大小（以字节为单位）：不会打包大于此配置大小的消息。发送到broker的请求将包含多个批次，每个分区一个，用于发送数据。
        buffer.memory: 生产者要用的总内存，用来缓存等待发送到服务器的消息的内存总字节数。
 */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        //阻塞时间的阈值通过max.block.ms设定
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 2; i++) {
            try {
                //testtopic    sinktopic
                producer.send(new ProducerRecord<String, String>("testtopic","g"+i,
                                i+"k"+Math.floor(Math.random()*100)),
                        new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata metadata, Exception e) {
                                if (e != null) {
                                    e.printStackTrace();
                                }
                                System.out.println("The offset of the record we just sent is: " + metadata.offset());
                            }
                        }).get();

                //.get(); 阻塞调用返回值
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        producer.close();


    }
}
