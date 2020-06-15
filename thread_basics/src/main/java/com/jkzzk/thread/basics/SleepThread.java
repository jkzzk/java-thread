package com.jkzzk.thread.basics;

public class SleepThread extends Thread{

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            if(i == 5) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("线程意外中断！");
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
