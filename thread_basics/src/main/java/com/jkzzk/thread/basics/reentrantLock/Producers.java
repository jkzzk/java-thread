package com.jkzzk.thread.basics.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producers extends Thread{

    private Lock lock;
    private Condition condition;

    public Producers() {

    }

    public Producers(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (true) {
                if (NumberList.numbers.size() != 0) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < 10; i++) {
                    NumberList.numbers.add(i);
                }

                condition.signal();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
