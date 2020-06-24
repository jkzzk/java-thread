package com.jkzzk.thread.basics.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class AddForkTask extends RecursiveTask<Long> {
    public static final Long MAX = 10000000L;

    private Long startNumber;

    private Long endNumber;

    public AddForkTask() {
    }

    public AddForkTask(Long startNumber, Long endNumber) {
//        System.out.println("开始值：" + startNumber + "--结束值：" + endNumber);
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    // 进行分析，是否需要再次进行分割
    @Override
    protected Long compute() {
        if(endNumber - startNumber < MAX) {
            Long sum = 0L;
            for(Long i = startNumber; i <= endNumber; i++) {
                sum += i;
            }

            return sum;
        }else {
            AddForkTask addForkTask_1 = new AddForkTask(startNumber,(startNumber+endNumber)/2);
            addForkTask_1.fork();
            AddForkTask addForkTask_2 = new AddForkTask((startNumber+endNumber)/2+1,endNumber);
            addForkTask_2.fork();

            return addForkTask_1.join() + addForkTask_2.join();
        }
    }

    public static void main(String[] args) {
        Long endNumber = 100000000L;

        Long startTime = System.currentTimeMillis();
        Long endTime = null;

        AddForkTask addForkTask = new AddForkTask(1L,endNumber);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(addForkTask);
        try {
            Long resault = submit.get();
            System.out.println("结果为：" + resault);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("fork/join框架运行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= endNumber; i++) {
            sum += i;
        }
        System.out.println("结果为：" + sum);
        endTime = System.currentTimeMillis();
        System.out.println("for循环运行时间：" + (endTime - startTime));
    }
}
