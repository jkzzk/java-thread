package com.jkzzk.thread.basics.utils;

import java.util.concurrent.CountDownLatch;

public class Teacher extends Thread{

    private String name;

    private CountDownLatch countDownLatch;

    public Teacher() {}

    public Teacher(String name,CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(this.name + "等待学生做作业！");
        try {
            countDownLatch.await();
            System.out.println(this.name + "开始批改作业！");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + "作业批改完了，下课！");
    }
}
