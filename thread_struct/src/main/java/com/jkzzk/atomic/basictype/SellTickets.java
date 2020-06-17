package com.jkzzk.atomic.basictype;

import java.util.concurrent.atomic.AtomicInteger;

public class SellTickets extends Thread{

    public static AtomicInteger tickets = new AtomicInteger(100);

    private String name;

    public SellTickets() {
    }

    public SellTickets(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            if(SellTickets.tickets.get() == 0) {
                break;
            }

            System.out.println(this.name + "正在售第" + SellTickets.tickets.getAndDecrement() + "票！");
        }
    }

    public static void main(String[] args) {

        SellTickets sellTickets_1 = new SellTickets("售票窗口_1");
        SellTickets sellTickets_2 = new SellTickets("售票窗口_2");
        SellTickets sellTickets_3 = new SellTickets("售票窗口_3");

        sellTickets_1.start();
        sellTickets_2.start();
        sellTickets_3.start();

    }
}
