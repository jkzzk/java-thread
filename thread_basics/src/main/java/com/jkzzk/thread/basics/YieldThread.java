package com.jkzzk.thread.basics;

public class YieldThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if(i == 3) {
                System.out.print("线程让步-");
                Thread.yield();
            }
        }
    }
}
