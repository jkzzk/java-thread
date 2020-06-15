package com.jkzzk.thread.basics.communication;

import java.util.ArrayList;
import java.util.List;

public class NumberList {

    public static List<Integer> numbers = new ArrayList<>();


    public static void main(String[] args) {

        Object obj = new Object();

        Customers customers = new Customers(obj);
        Producers producers = new Producers(obj);

        customers.start();
        producers.start();
    }
}
