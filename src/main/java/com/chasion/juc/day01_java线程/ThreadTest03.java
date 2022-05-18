package com.chasion.juc.day01_java线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadTest03
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/12 15:29
 */
@Slf4j
public class ThreadTest03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("callable running");
                return 10;
            }
        });

        Thread t3 = new Thread(futureTask, "t3");
        t3.start();

        log.debug("{}", futureTask.get());
    }
}
