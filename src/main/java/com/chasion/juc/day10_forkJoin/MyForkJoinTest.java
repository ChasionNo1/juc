package com.chasion.juc.day10_forkJoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName MyForkJoinTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 21:40
 */
public class MyForkJoinTest {

    // for 循环
    @Test
    public void test1(){
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 1L; i <= 1_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        // 32
        System.out.println("sum = " + sum + "，用时 = " + (end - start));
    }

    // 使用forkJoin
    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyForkJoin myForkJoin = new MyForkJoin(0L, 1_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(myForkJoin);
        Long sum;
        try {
            sum = submit.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        // 39
        System.out.println("sum = " + sum + "，用时 = " + (end - start));
    }

    // 使用并行流
    @Test
    public void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 1_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        // 用时 = 67
        System.out.println("sum = " + sum + "，用时 = " + (end - start));
    }
}
