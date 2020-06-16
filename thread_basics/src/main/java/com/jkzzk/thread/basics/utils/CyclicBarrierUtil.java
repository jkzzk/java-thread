package com.jkzzk.thread.basics.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUtil extends Thread{

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierUtil(){}

    public CyclicBarrierUtil(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始等待");
        try {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "开始工作");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "工作完成");
    }

    public static void main(String[] args) {

        int threadNumbers = 3;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNumbers);

        for (int i = 0; i < threadNumbers; i++) {
            CyclicBarrierUtil cyclicBarrierUtil = new CyclicBarrierUtil(cyclicBarrier);
            cyclicBarrierUtil.start();
        }
    }
}
