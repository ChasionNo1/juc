package com.chasion.juc.day01_java线程;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ThreadTest02
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/12 15:15
 */
@Slf4j
public class ThreadTest02 {

    public static void main(String[] args) {
        Runnable r = () -> log.debug("runnable running");

        Thread t = new Thread(r, "t2");
        t.start();
        log.debug("main running");
    }
}
