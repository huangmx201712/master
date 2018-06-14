package com.zheng.demo.kafakaconnetor;

import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.StoreBuilder;

import java.util.Map;

public class MyStoreBuilder implements StoreBuilder {

    @Override
    public StoreBuilder withCachingEnabled() {
        return null;
    }

    @Override
    public StoreBuilder withLoggingEnabled(Map map) {
        return null;
    }

    @Override
    public StoreBuilder withLoggingDisabled() {
        return null;
    }

    @Override
    public StateStore build() {
        return null;
    }

    @Override
    public Map<String, String> logConfig() {
        return null;
    }

    @Override
    public boolean loggingEnabled() {
        return false;
    }

    @Override
    public String name() {
        return null;
    }
}
