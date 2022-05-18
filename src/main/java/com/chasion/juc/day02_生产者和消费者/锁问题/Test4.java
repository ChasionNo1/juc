package com.chasion.juc.day02_生产者和消费者.锁问题;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test4
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/13 20:05
 *
 * 7、一个静态的同步方法，一个普通的同步方法，一个对象调用，先发短信还是先打电话？
 * 两把锁，各走各的
 * 8、一个静态的同步方法，一个普通的同步方法，两个对象调用，先发短信还是先打电话？
 *
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone4{

    // 静态方法，随着类加载而加载，先于对象，锁是Phone3类对象，所以这两个同步方法锁是同一个对象
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    // 锁是调用者，this
    public synchronized void call(){
        System.out.println("打电话");
    }

}