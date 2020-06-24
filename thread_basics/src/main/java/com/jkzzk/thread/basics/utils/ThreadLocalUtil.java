package com.jkzzk.thread.basics.utils;

public class ThreadLocalUtil extends Thread{

    private String name;

    private ThreadLocal<String> stringThreadLocal;

    public ThreadLocalUtil() {
    }

    public ThreadLocalUtil(String name, ThreadLocal<String> stringThreadLocal) {
        this.name = name;
        this.stringThreadLocal = stringThreadLocal;
    }

    @Override
    public void run() {
        this.stringThreadLocal.set("zzk_" + 1);
        System.out.println(this.name + "获取"+stringThreadLocal.get());
    }

    public static void main(String[] args) {

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

        ThreadLocalUtil threadLocalUtil = new ThreadLocalUtil("线程_1",stringThreadLocal);
        threadLocalUtil.start();

        System.out.println("主线程获取"+stringThreadLocal.get());
    }
}
