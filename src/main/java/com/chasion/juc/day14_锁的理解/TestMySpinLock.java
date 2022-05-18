package com.chasion.juc.day14_锁的理解;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestMySpinLock
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/18 19:57
 *
 * t1===> lock
 * t2===> lock
 * t1====> unlock
 * t2====> unlock
 *
 * 按照顺序，t1先执行lock，属性初始化赋值的时候是null，所以第一次的时候，cas是返回true的，t1线程先进来，修改了期望值为t1_thread
 * 当t2再执行lock的时候，此时主内存里已经是t1_thread，t2就在自旋（while循环）
 * 然后t1线程执行unlock操作，将t1_thread还原为null时，t2结束自旋，跳出while循环，接着也会执行unlock
 */
public class TestMySpinLock {

    public static void main(String[] args) {
        // 底层使用的自旋锁
        MySpinLock mySpinLock = new MySpinLock();
        new Thread(() -> {
            // 加锁
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // 解锁
                mySpinLock.myUnLock();
            }
        }, "t1").start();

        new Thread(() -> {
            // 加锁
            mySpinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // 解锁
                mySpinLock.myUnLock();
            }
        }, "t2").start();
    }
}
