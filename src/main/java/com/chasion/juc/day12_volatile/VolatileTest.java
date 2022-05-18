package com.chasion.juc.day12_volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VolatileTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/17 19:52
 */
public class VolatileTest {
//    private volatile static int num = 0;
    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add(){
//        num++;
        // AtomicInteger + 1 方法， CAS
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        // main gc
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
