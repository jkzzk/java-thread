package com.jkzzk.atomic.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        Map<String,String> concurrentHashMap = new ConcurrentHashMap(10);

        concurrentHashMap.put("xiao","hahaha");

        System.out.println(concurrentHashMap.get("xiao"));
    }

}
