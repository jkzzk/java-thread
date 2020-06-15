package com.jkzzk.thread.basics.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadThread extends Thread {

    private ReentrantReadWriteLock reentrantReadWriteLock;

    public ReadThread() {}

    public ReadThread(ReentrantReadWriteLock reentrantReadWriteLock) {
        this.reentrantReadWriteLock = reentrantReadWriteLock;
    }

    @Override
    public void run() {
        reentrantReadWriteLock.readLock().lock();
        try{
            int count = 0;
            while(true) {
                if(NumberList.numbers.size() == 0) {
                    reentrantReadWriteLock.readLock().unlock();
                }
                count++;
                if(count == 2) {
                    break;
                }
                for (Integer number : NumberList.numbers) {
                    System.out.println(Thread.currentThread().getName() + "抛出一个数字" + number);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            reentrantReadWriteLock.readLock().unlock();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
