package com.jkzzk.thread.basics.communication;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class Customers extends Thread{
    private Object obj;

    public Customers(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {

        synchronized (this.obj) {
            while(true) {
                if(NumberList.numbers.size() == 0) {
                    try {
                        obj.wait();
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

                obj.notify();
            }
        }
    }
}
