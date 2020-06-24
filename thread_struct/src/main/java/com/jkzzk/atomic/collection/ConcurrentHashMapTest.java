package com.jkzzk.atomic.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentHashMapTest {

    private ConcurrentHashMapTest concurrentHashMapTest;


    public ConcurrentHashMapTest getConcurrentHashMapTest() {
        return concurrentHashMapTest;
    }

    public void setConcurrentHashMapTest(ConcurrentHashMapTest concurrentHashMapTest) {
        this.concurrentHashMapTest = concurrentHashMapTest;
    }

    public static void main(String[] args) {

        Map<String,String> concurrentHashMap = new ConcurrentHashMap(10);

        ConcurrentLinkedQueue<String> strings = new ConcurrentLinkedQueue<String>();

        concurrentHashMap.put("xiao","hahaha");

        System.out.println(concurrentHashMap.get("xiao"));

        ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();

        ConcurrentHashMapTest head = concurrentHashMapTest;

        for (long i = 0; i < 10000000000L; i++) {

            ConcurrentHashMapTest newConcurrentHashMapTest1 = new ConcurrentHashMapTest();

            concurrentHashMapTest.concurrentHashMapTest = newConcurrentHashMapTest1;

            concurrentHashMapTest = newConcurrentHashMapTest1;

            if(concurrentHashMapTest.getConcurrentHashMapTest() != null) {
                System.out.println(concurrentHashMapTest.getConcurrentHashMapTest());
            }
        }

    }

}
