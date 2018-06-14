package com.zheng.demo.kafkademo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.SystemTime;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.state.internals.KeyValueStoreBuilder;

import java.util.Properties;

public class KafkaStreamsDemo {

    void doTest(){
        Properties props  = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        StreamsConfig config = new StreamsConfig(props);

        Topology topology=new Topology();
        topology.addSource("SOURCE1","testtopic");
        //WordCountProcessorSupplier word= new WordCountProcessorSupplier();
        //定义数据在流动中的处理逻辑
        topology.addProcessor("PROCESSOR1",new MyProcessorSupplier(),"SOURCE1");
        KeyValueBytesStoreSupplier supplier= Stores.persistentKeyValueStore("CREATED");

        StoreBuilder storeBuilder =new KeyValueStoreBuilder(supplier,
                Serdes.String(), Serdes.String(), new SystemTime());

        topology.addStateStore(storeBuilder,"PROCESSOR1");

        topology.addSink("SINK1","sinktopic","PROCESSOR1");

        KafkaStreams streams = new KafkaStreams(topology, config);
        //topology.addSink()

        streams.start();





/*         StreamsBuilder builder = new StreamsBuilder();
        //testtopic    sinktopic
       // builder.stream("testtopic").to("sinktopic");

         Topology topology = builder.build();

         KafkaStreams streams = new KafkaStreams(topology, props);
        streams.start();*/

    }

    public static void main(String[] args) {
        KafkaStreamsDemo kafkaStreamsDemo=new KafkaStreamsDemo();
        kafkaStreamsDemo.doTest();
    }


}
