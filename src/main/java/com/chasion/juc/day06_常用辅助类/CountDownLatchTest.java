package com.chasion.juc.day06_常用辅助类;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/15 14:01
 *
 * 计数器
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "get out");
                countDownLatch.countDown();  // 数量-1
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零，然后再向下执行
        countDownLatch.await();
        System.out.println("close");

    }
}


