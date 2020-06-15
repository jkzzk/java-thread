package com.jkzzk.thread.basics;

public class WaitThread extends Thread {

    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {

            if(Thread.currentThread().getState() == State.BLOCKED) {
                System.out.println("进入阻塞状态!");
            }

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
