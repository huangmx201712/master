package com.zheng.thread;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("线程已经启动");
        super.run();
    }
}
