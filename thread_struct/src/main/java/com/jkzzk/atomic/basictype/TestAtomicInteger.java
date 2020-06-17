package com.jkzzk.atomic.basictype;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

        int i = atomicInteger.get();
        System.out.println("get方法: " + i);

        i = atomicInteger.getAndSet(5);
        System.out.println("getAndSet方法: " + i);

        i = atomicInteger.getAndIncrement();
        System.out.println("getAndIncrement方法: " + i);

        i = atomicInteger.getAndDecrement();
        System.out.println("getAndDecrement方法: " + i);

        i = atomicInteger.getAndAdd(10);
        System.out.println("getAndAdd方法: " + i);

        boolean b = atomicInteger.compareAndSet(20, 20);
        System.out.println("compareAndSet方法: " + atomicInteger.get());
    }

}
