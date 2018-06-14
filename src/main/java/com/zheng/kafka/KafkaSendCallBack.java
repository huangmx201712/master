package com.zheng.kafka;

import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaSendCallBack implements ListenableFutureCallback {

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println("发送回调失败");
    }

    @Override
    public void onSuccess(@Nullable Object o) {
        System.out.println("发送回调成功");

    }
}
