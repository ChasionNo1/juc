package com.chasion.juc.day07_读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 14:16
 * <p>
 * 读写锁
 * 独占锁（写锁）：一次只能被一个线程占有
 * 共享锁（读锁）：多个线程可以同时占有
 * readWriteLock
 * 读-读：可以共存
 * 读-写：不可以共存
 * 写-写：不可以共存
 *
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
            //Variable used in lambda expression should be final or effectively final
            //在lambda表达式中使用的变量应该是最终变量或有效的最终变量。
                myCache.put(String.valueOf(temp), temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                //Variable used in lambda expression should be final or effectively final
                //在lambda表达式中使用的变量应该是最终变量或有效的最终变量。
                myCache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }
}
/**
 * 自定义缓存
 *
 * */
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    // 写入，写入的时候只有一个线程写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入ok");
    }

    // 读出，读的时候可以有多个线程读
    public Object get(String key) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        System.out.println(Thread.currentThread().getName() + "写入ok");
        return map.get(key);
    }
}

// 加锁的
class MyCacheLock{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 写入，写入的时候只有一个线程写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    // 读出，读的时候可以有多个线程读
    public Object get(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读出" + key);
            System.out.println(Thread.currentThread().getName() + "读出ok");
            return map.get(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
