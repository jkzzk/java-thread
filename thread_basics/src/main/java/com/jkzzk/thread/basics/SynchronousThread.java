package com.jkzzk.thread.basics;

public class SynchronousThread extends Thread {

    public static int tickets = 100;

    private Object obj;
    private String name;

    public SynchronousThread() {

    }

    public SynchronousThread(String name, Object obj) {
        this.name = name;
        this.obj = obj;
    }

    @Override
    public void run() {
        System.out.println(this.name + "打开");
        while (true) {
            synchronized (this.obj) {
                if (tickets <= 0) {
                    return;
                }

                if(tickets % 5 == 0) {
                    Thread.yield();
                }

                System.out.println(this.name + "售卖了第" + tickets + "张票");

                --tickets;
            }
        }
    }

    public static void main(String[] args) {
        Object obj = new Object();

        SynchronousThread synchronousThread_1 = new SynchronousThread("售票窗口_1",obj);
        SynchronousThread synchronousThread_2 = new SynchronousThread("售票窗口_2",obj);

        synchronousThread_1.start();
        synchronousThread_2.start();
    }
}
