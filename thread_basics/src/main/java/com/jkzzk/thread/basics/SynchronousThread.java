package com.jkzzk.thread.basics;

public class SynchronousThread extends Thread {

    public static int tickets = 10000;

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
}
