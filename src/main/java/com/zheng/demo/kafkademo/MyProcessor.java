package com.zheng.demo.kafkademo;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Observer;


public class MyProcessor implements Processor {

    private ProcessorContext context;
    private KeyValueStore kvStore;

    @Override
    @SuppressWarnings("unchecked")
    public void init(ProcessorContext context) {
        this.context = context;
        this.context.schedule(1000);
        this.kvStore = (KeyValueStore) context.getStateStore("CREATED");
    }


    @Override
    public void punctuate(long timestamp) {
/*        KeyValueIterator iter = this.kvStore.all();

        while (iter.hasNext()) {
            KeyValue entry = (KeyValue) iter.next();
            System.out.println("key:"+entry.key+";value:"+entry.value);
            context.forward(entry.key, entry.value.toString());
        }

        iter.close();
        context.commit();*/
    }

    @Override
    public void close() {
        this.kvStore.close();
    }

    @Override
    public void process(Object dummy, Object line) {

       // this.kvStore.put(dummy,line+"123");
        context.forward(dummy, line+"1234");

       // this.kvStore.put(dummy,line+"123");
    }


}
