package com.chasion.juc.day08_阻塞队列;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BlockingQueueTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 15:14
 */
public class BlockingQueueTest {

    /**
     * 抛出异常
     * */
    @Test
    public void test1(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        java.lang.IllegalStateException: Queue full
//        blockingQueue.add("d");
        System.out.println("=============");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
//        java.util.NoSuchElementException
//        blockingQueue.remove();

    }

    /**
     * 有返回值，没有异常
     * */
    @Test
    public void test2(){
        // 队列大小
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
//        false，不抛出异常，添加不成功
//        System.out.println(blockingQueue.offer("d"));
        System.out.println("============");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // null 不抛出异常
//        System.out.println(blockingQueue.poll());
    }

    /**
     * 等待，阻塞（一直阻塞）
     * */
    @Test
    public void test3() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // 一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 队列没有位置了，一直阻塞
//        blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        // 没有这个元素，一直阻塞
//        blockingQueue.take();
    }

    /**
     * 等待，阻塞（超时等待）
     *
     * */
    @Test
    public void test4() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 等待超过2秒就退出
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);
        System.out.println("============");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // 等待超过2秒就退出
        blockingQueue.poll(2, TimeUnit.SECONDS);

    }

    /**
     *  LinkedBlockingQueue
     *  使用方式和数组一致
     * */
    @Test
    public void test5() throws InterruptedException {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(4);
        blockingQueue.add("a");
        blockingQueue.offer("b");
        blockingQueue.put("c");
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
        System.out.println("===========");
        blockingQueue.remove();
        blockingQueue.poll();
        blockingQueue.take();
        blockingQueue.poll(2, TimeUnit.SECONDS);
    }

}
