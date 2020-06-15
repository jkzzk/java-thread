package com.jkzzk.thread.basics.reentrantLock;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Customers extends Thread {

    private Lock lock;
    private Condition condition;

    public Customers() {

    }

    public Customers(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        try {
            lock.lock();
            while (true) {
                if (NumberList.numbers.size() == 0) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Iterator<Integer> iterator = NumberList.numbers.iterator();

                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    System.out.println("抛出一个数字：" + next);
                    iterator.remove();
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
