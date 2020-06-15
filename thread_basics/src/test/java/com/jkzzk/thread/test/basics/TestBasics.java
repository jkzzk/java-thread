package com.jkzzk.thread.test.basics;

import com.jkzzk.thread.basics.*;
import com.jkzzk.thread.basics.communication.Customers;
import com.jkzzk.thread.basics.communication.Producers;
import org.junit.Test;

public class TestBasics {

    @Test
    public void testFirstThread() {

        FirstThread firstThread_A = new FirstThread("线程A");
        FirstThread firstThread_B = new FirstThread("线程B");

        Thread thread_A = new Thread(firstThread_A);
        Thread thread_B = new Thread(firstThread_B);

        thread_A.start();
        thread_B.start();

        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void testSecondThread() {

        SecondThread secondThread_A = new SecondThread("线程A");
        SecondThread secondThread_B = new SecondThread("线程B");

        secondThread_A.start();
        secondThread_B.start();
    }

    @Test
    public void testJoin() throws InterruptedException {

        SecondThread secondThread_A = new SecondThread("线程A");
        SecondThread secondThread_B = new SecondThread("线程B");

        secondThread_A.start();
        secondThread_B.start();

        secondThread_A.join();
//        secondThread_B.join();

        System.out.println("等待结束，开始执行");
    }

    @Test
    public void testSleep() throws InterruptedException {
        SleepThread sleepThread = new SleepThread();
        SleepThread sleepThread_2 = new SleepThread();
        sleepThread.start();
        sleepThread_2.start();

        sleepThread.join();
        System.out.println("等待结束，开始执行");
    }

    @Test
    public void testInterrupt() throws InterruptedException {
        SleepThread sleepThread = new SleepThread();
        sleepThread.start();

//        sleepThread.interrupt();

        sleepThread.join();
        System.out.println("等待结束，开始执行");
    }

    @Test
    public void testWaitInterrupt() throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();

        waitThread.join();
        System.out.println("等待结束，开始执行");
    }

    @Test
    public void testDaemon() {
        WaitThread waitThread = new WaitThread();
        waitThread.setDaemon(true);
        waitThread.start();

        System.out.println("等待结束，开始执行");
    }

    @Test
    public void testPriority() {
        SecondThread secondThread_A = new SecondThread("线程A");
        SecondThread secondThread_B = new SecondThread("线程B");

        secondThread_A.setPriority(Thread.MIN_PRIORITY);
        secondThread_B.setPriority(Thread.MAX_PRIORITY);

        secondThread_A.start();
        secondThread_B.start();
    }

    @Test
    public void testYield() throws InterruptedException {
        YieldThread yieldThread_A = new YieldThread();
        SecondThread secondThread = new SecondThread("线程2");


        yieldThread_A.start();
        secondThread.start();

        System.out.println("main end");
    }

    @Test
    public void syncSellTickets() throws InterruptedException {
        Object obj = new Object();
        SynchronousThread syncA = new SynchronousThread("售票窗口A",obj);
        SynchronousThread syncB = new SynchronousThread("售票窗口B",obj);
        SynchronousThread syncC = new SynchronousThread("售票窗口C",obj);

        syncA.start();
        syncB.start();
        syncC.start();

        Thread.sleep(5000);
        System.out.println("main end");
    }

    @Test
    public void testCommunication() {

        Object obj = new Object();

        Customers customers = new Customers(obj);
        Producers producers = new Producers(obj);

        customers.start();
        producers.start();

    }

}
