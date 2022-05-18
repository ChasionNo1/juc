package com.chasion.juc.day02_生产者和消费者.锁问题;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test3
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/13 19:59
 *
 * 5、增加两个静态的同步方法，只有一个对象，先打印发短信还是打电话？
 * 6、两个对象，增加两个静态的同步方法，先打印还是先打电话？
 *
 */
public class Test3 {

    public static void main(String[] args) {
        Phone3 phone = new Phone3();
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

class Phone3{

    // 静态方法，随着类加载而加载，先于对象，锁是Phone3类对象，所以这两个同步方法锁是同一个对象
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");
    }

}

