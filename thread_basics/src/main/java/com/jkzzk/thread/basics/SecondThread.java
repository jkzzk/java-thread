package com.jkzzk.thread.basics;

public class SecondThread extends Thread {

    private String name = "线程";

    public SecondThread() {

    }

    public SecondThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println(this.name + ":" + i);
        }
    }
}
