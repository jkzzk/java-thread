package com.jkzzk.thread.basics.utils;

import javafx.scene.control.Separator;

import javax.swing.plaf.SeparatorUI;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreUtil extends Thread{

    public static int reourceNumbers = 20;

    private String name;

    private Semaphore semaphore;

    private ReentrantLock reentrantLock;

    public SemaphoreUtil() {
    }

    public SemaphoreUtil(String name, Semaphore semaphore,ReentrantLock reentrantLock) {
        this.name = name;
        this.semaphore = semaphore;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        if(SemaphoreUtil.reourceNumbers == 0) {
            return ;
        }
        try {
            semaphore.acquire();
            reentrantLock.lock();
            System.out.println(this.name + "拿到了读数许可！");
            Thread.sleep(1000);
            System.out.println(this.name + "开始读取，读出" + SemaphoreUtil.reourceNumbers);
            SemaphoreUtil.reourceNumbers--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
            System.out.println(this.name + "释放了读数许可！");
            semaphore.release();
        }
    }

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2,true);
        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            SemaphoreUtil semaphoreUtil  = new SemaphoreUtil("读数器"+i,semaphore,reentrantLock);
            semaphoreUtil.start();
        }

    }
}
