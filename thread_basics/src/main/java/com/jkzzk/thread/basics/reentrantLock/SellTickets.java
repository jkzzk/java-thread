package com.jkzzk.thread.basics.reentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTickets extends Thread{

    public static int tickets = 100;

    private Lock lock;
    private String name;

    public SellTickets() {
    }

    public SellTickets(Lock lock,String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {

            try {
                lock.lock();

                if (tickets <= 0) {
                    return;
                }

                if(tickets % 5 == 0) {
                   Thread.yield();
                }

                System.out.println(this.name + "售卖了第" + tickets + "张票");

                --tickets;
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock(false);

        SellTickets sellTickets_1 = new SellTickets(reentrantLock,"售票窗口_1");
        SellTickets sellTickets_2 = new SellTickets(reentrantLock,"售票窗口_2");
        SellTickets sellTickets_3 = new SellTickets(reentrantLock,"售票窗口_3");

        sellTickets_1.start();
        sellTickets_2.start();
        sellTickets_3.start();
    }
}
