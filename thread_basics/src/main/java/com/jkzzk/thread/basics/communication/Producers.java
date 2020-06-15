package com.jkzzk.thread.basics.communication;

public class Producers extends Thread{

    private Object obj;

    public Producers(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (this.obj) {
            while(true) {
                if (NumberList.numbers.size() != 0) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < 10; i++) {
                    NumberList.numbers.add(i);
                }

                obj.notify();
            }
        }
    }
}
