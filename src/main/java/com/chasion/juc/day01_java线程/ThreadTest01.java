package com.chasion.juc.day01_java线程;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ThreadTest01
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/12 14:56
 *
 * 创建线程的几种方式：
 * 1、继承Thread类
 * 2、实现Runnable接口
 * 3、futureTask + Callable接口，可以返回参数
 */
@Slf4j
public class ThreadTest01 {

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };

        t.start();
        log.debug("running");


    }

}
