package com.jkzzk.thread.basics.reentrantReadWriteLock;

import com.jkzzk.thread.basics.reentrantLock.Customers;
import com.jkzzk.thread.basics.reentrantLock.Producers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NumberList {

    public static List<Integer> numbers = new ArrayList<>();


    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

        WriteThread writeThread = new WriteThread(reentrantReadWriteLock);
        ReadThread readThread_1 = new ReadThread(reentrantReadWriteLock);
        ReadThread readThread_2 = new ReadThread(reentrantReadWriteLock);

        writeThread.start();
        readThread_1.start();
        readThread_2.start();
    }
}
