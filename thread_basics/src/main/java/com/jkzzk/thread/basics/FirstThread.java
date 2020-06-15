package com.jkzzk.thread.basics;

public class FirstThread implements Runnable {
    private String name = "线程";

    public FirstThread() {
    }

    public FirstThread(String name) {
        this.name = name;
    }

    public void run() {

        System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            System.out.println(this.name + ":" + i);
        }
    }
}
