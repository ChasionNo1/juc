package com.chasion.juc.day09_线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 16:21
 *
 *
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 单个线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 固定线程池大小
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 可伸缩的，遇强则强，遇弱则弱
        ExecutorService executorService = Executors.newCachedThreadPool();


        try {
            for (int i = 0; i < 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 线程池用完，程序结束，关闭线程池
            executorService.shutdown();
        }

    }
}
