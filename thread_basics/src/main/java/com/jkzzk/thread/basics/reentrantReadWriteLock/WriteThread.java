package com.jkzzk.thread.basics.reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteThread extends Thread {
    private ReentrantReadWriteLock reentrantReadWriteLock;

    public WriteThread() {}

    public WriteThread(ReentrantReadWriteLock reentrantReadWriteLock) {
        this.reentrantReadWriteLock = reentrantReadWriteLock;
    }

    @Override
    public void run() {
        reentrantReadWriteLock.writeLock().lock();
        try{
            for (int i = 0; i < 100; i++) {
                NumberList.numbers.add(i);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
