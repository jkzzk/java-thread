package com.jkzzk.thread.basics.utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUtil extends Thread {

    public static void main(String[] args) {

        int sutdentNumbers = 3;

        CountDownLatch countDownLatch = new CountDownLatch(sutdentNumbers);

        Teacher teacher = new Teacher("数学老师",countDownLatch);
        teacher.start();

        for (int i = 0; i < sutdentNumbers; i++) {
            Student student = new Student("学生"+i,countDownLatch);
            student.start();
        }

    }

}
