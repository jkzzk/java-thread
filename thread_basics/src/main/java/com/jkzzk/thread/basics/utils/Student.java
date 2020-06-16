package com.jkzzk.thread.basics.utils;

import java.util.concurrent.CountDownLatch;

public class Student extends Thread {

    private String name;

    private CountDownLatch countDownLatch;

    public Student() {}

    public Student(String name,CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(this.name + "正在写作业！");
        try {
            Thread.sleep(2000);
            System.out.println(this.name + "作业写完了！");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
