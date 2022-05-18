package com.chasion.juc.day04_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/15 13:15
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        // Thread构造器里可以传入Runnable接口实现类
        // FutureTask是Runnable接口实现类
        // FutureTask的构造器传入callable接口实现类
        FutureTask<Integer> futureTask = new FutureTask<>(myThread);  // 适配类

        // 结果会被缓存，效率高，只打印一次
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        // get方法可能会产生阻塞，把它放在最后，或者使用异步通信来处理
        Integer o = futureTask.get();
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        // 耗时的操作
        return 1234;
    }
}
