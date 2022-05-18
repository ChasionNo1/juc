package com.chasion.juc.day14_锁的理解;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName MySpinLock
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/18 19:53
 *
 * 自旋锁
 */
public class MySpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "===> lock");

        // 自旋锁 ！false才会执行，cas操作不了会一直循环（自旋）
        // 属性初始化赋值的时候是null，所以第一次的时候，cas是返回true的，t1线程先进来，修改了期望值为t1_thread
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    // 解锁
    // 加锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "====> unlock");
        atomicReference.compareAndSet(thread, null);
    }
}

