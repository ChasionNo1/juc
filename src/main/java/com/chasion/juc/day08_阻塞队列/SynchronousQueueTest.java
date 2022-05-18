package com.chasion.juc.day08_阻塞队列;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronousQueueTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 15:51
 *
 * 同步队列是阻塞队列的实现类
 * 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素
 *  put了一个元素，必须从里面先take取出来，否则不能在put进去值！
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        // 同步队列
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "=>" + synchronousQueue.take());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
