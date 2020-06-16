package com.jkzzk.thread.basics.utils;

import com.sun.xml.internal.ws.util.xml.CDATA;

import java.util.concurrent.Exchanger;

public class ExchangeUtil extends Thread{

    private String name;

    private String data;

    private Exchanger<String> stringExchanger;

    public ExchangeUtil() {
    }

    public ExchangeUtil(String name, Exchanger<String> stringExchanger,String data) {
        this.name = name;
        this.data = data;
        this.stringExchanger = stringExchanger;
    }

    @Override
    public void run() {
        System.out.println(this.name + "----" + this.data);
        try {
            this.data = stringExchanger.exchange(this.data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + "----" + this.data);
    }

    public static void main(String[] args) {

        Exchanger<String> stringExchanger = new Exchanger<>();

        ExchangeUtil exchangeUtil_1 = new ExchangeUtil("交换线程_1",stringExchanger,"数据_1");
        ExchangeUtil exchangeUtil_2 = new ExchangeUtil("交换线程_2",stringExchanger,"数据_2");

        exchangeUtil_1.start();
        exchangeUtil_2.start();
    }
}
