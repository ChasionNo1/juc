package com.chasion.juc.day14_锁的理解;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Demo02
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/18 19:45
 *
 * 使用lock
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sms();
        }, "a").start();

        new Thread(phone::sms, "b").start();
    }
}

class Phone2{
    Lock lock = new ReentrantLock();
    public void sms(){
        // lock和unlock要配对，有多少个lock，就要有多少个unlock，否则会死锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "sms");
            call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "call");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
