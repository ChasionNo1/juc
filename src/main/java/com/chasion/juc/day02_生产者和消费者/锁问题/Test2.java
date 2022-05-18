package com.chasion.juc.day02_生产者和消费者.锁问题;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/13 19:25
 *
 * 3、增加了普通方法后，先执行发短信还是hello？
 *  A 线程是同步方法，有锁，但是只有它一个人有，没有竞争关系，
 *  B 线程是普通线程方法，代码执行到这里，就开始
 *
 * 4、如果有两个对象，两把锁时，各自执行同步方法， 发短信还是打电话？
 * 根据各自时间执行
 *
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> phone1.sendSms(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.call(), "B").start();
    }

}

class Phone2{

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

    // 普通方法，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}

