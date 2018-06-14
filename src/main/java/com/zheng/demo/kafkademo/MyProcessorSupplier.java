package com.zheng.demo.kafkademo;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

public class MyProcessorSupplier implements ProcessorSupplier {

    @Override
    public Processor get() {

        return new MyProcessor();
    }
}
