package com.chasion.juc.day06_常用辅助类;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/15 14:37
 *
 * 信号量
 * 模拟抢车位
 */
public class SemaphoreTest {
    public static void main(String[] args) {
//        int permits, boolean fair
        // 线程数量：停车位 限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢道车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
