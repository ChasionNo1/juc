package com.chasion.juc.day12_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMMTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/17 19:24
 */
public class JMMTest {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;
    public  static void main(String[] args) {
        new Thread(() -> {
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        num = 1;
        // num 输出 1，说明已经赋值成功了，但是线程的while循环里还是num=0，对主内存的变化是不知道的
        System.out.println(num);
    }
}
