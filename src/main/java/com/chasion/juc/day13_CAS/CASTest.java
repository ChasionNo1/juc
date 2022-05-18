package com.chasion.juc.day13_CAS;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CASTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/18 16:08
 *
 * 按照a线程可以执行，b线程不能执行的情况，分析
 * 如果a线程修改成功，两次都可以成功，则b不成功
 * 如果b成功，则a不能成功
 */
public class CASTest {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("a1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 旧的期望值，新的修改值，旧的版本号，新的版本号
            // 将 1 ---> 2
            // 比较旧的期望值和旧的版本号和主内存里的是否一致，如果一致，将通过CAS操作来完成Pair对象的替换
            System.out.println("a1 " + atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2 => " + atomicStampedReference.getStamp());
            System.out.println("a2 refer" + atomicStampedReference.getReference());
            // 上一步执行过后，主内存里的期望值为2，版本号为2

            // 将 2 ----> 1，这里期望值为2，版本号为2 和主内存一致，可以完成修改
            System.out.println("a2" + atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            // 修改完后，期望值为1，版本号为3
        }, "a").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 这里期望值1 版本号为1，与主内存不匹配，不能完成修改
            System.out.println("b2" + atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("b2 => " + atomicStampedReference.getStamp());
        }, "b").start();
    }


    @Test
    public void test(){
        Integer integer = new Integer(2020);
        Integer integer1 = new Integer(2020);
        // false
        System.out.println(integer == integer1);

        Integer integer2 = Integer.valueOf(2020);
        Integer integer3 = Integer.valueOf(2020);
        // false
        System.out.println(integer2 == integer3);
        // true
        System.out.println(integer2.equals(integer3));
    }
}
