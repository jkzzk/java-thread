package com.jkzzk.thread.basics.reentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NumberList {

    public static List<Integer> numbers = new ArrayList<>();


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Customers customers = new Customers(reentrantLock,condition);
        Producers producers = new Producers(reentrantLock,condition);

        customers.start();
        producers.start();
    }
}
