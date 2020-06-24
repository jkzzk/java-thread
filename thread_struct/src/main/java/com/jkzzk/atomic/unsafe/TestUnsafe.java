package com.jkzzk.atomic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestUnsafe {

    private volatile String name;
    private volatile Integer age = 3;

    public static void main(String[] args) throws Exception {

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        TestUnsafe testDoMain = (TestUnsafe) unsafe.allocateInstance(TestUnsafe.class);
        Field nameField = TestUnsafe.class.getDeclaredField("age");

        long nameFieldOffset = unsafe.objectFieldOffset(nameField);
        unsafe.putOrderedObject(testDoMain,nameFieldOffset,100);
/*        for (int i = 0; i < 1000000; i++) {
            unsafe.putOrderedObject(testDoMain,nameFieldOffset,""+i);
            testDoMain.age = 24;
        }*/

        new Thread(() -> {
            while(true) {
                if(testDoMain.getAge() == 0) {
                    return ;
                }
                unsafe.putOrderedObject(testDoMain, nameFieldOffset, testDoMain.getAge() - 1);
                System.out.println(Thread.currentThread().getName() + "抛出一张票" + (testDoMain.getAge() + 1));
            }
        }).start();

        new Thread(() -> {
            while(true) {
                if (testDoMain.getAge() == 0) {
                    return;
                }
                unsafe.putOrderedObject(testDoMain, nameFieldOffset, testDoMain.getAge() - 1);
                System.out.println(Thread.currentThread().getName() + "抛出一张票" + (testDoMain.getAge() + 1));
            }
        }).start();

        new Thread(() -> {
            while(true) {
                if (testDoMain.getAge() == 0) {
                    return;
                }
                unsafe.putOrderedObject(testDoMain, nameFieldOffset, testDoMain.getAge() - 1);
                System.out.println(Thread.currentThread().getName() + "抛出一张票" + (testDoMain.getAge() + 1));
            }
        }).start();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
