package com.chasion.juc.day14_锁的理解;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/18 19:41
 *
 * 可重入锁（递归锁）
 * 使用synchronized
 */
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        // 拿到了外面的锁之后，就可以拿到里面的锁，自动获得
        new Thread(() -> {
            phone.sms();
        }, "a").start();

        new Thread(phone::sms, "b").start();
    }
}

class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName() + "sms");
        call();
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}
