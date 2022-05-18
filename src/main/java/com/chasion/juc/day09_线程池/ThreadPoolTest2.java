package com.chasion.juc.day09_线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest2
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 16:51
 *
 * 手动创建一个线程池
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                // 处理核心数
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                //队列满了，尝试去和最早的竞争，也不会抛出异常！
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            // 最大承载：maxPoolSize + queue大小
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
