package com.chasion.juc.day02_生产者和消费者.synchronized版本;

/**
 * @ClassName A
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/13 14:50
 *
 * 线程之间的通信问题：生产者和消费者问题！ 等待唤醒，通知唤醒
 * 线程交替执行 A B 操作同一个变量 num = 0
 * A num+1
 * B num-1
 */
public class A {

    public static void main(String[] args){
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();

        // 当如果出现A B C D 四个线程操作时，存在虚假唤醒问题
        // 拿两个加法线程A、B来说，比如A先执行，执行时调用了wait方法，那它会等待，此时会释放锁，
        // 那么线程B获得锁并且也会执行wait方法，两个加线程一起等待被唤醒。此时减线程中的某一个线程执行完毕并且唤醒了这俩加线程，
        // 那么这俩加线程不会一起执行，其中A获取了锁并且加1，执行完毕之后B再执行。如果是if的话，那么A修改完num后，
        // B不会再去判断num的值，直接会给num+1。如果是while的话，A执行完之后，B还会去判断num的值，因此就不会执行。

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "D").start();
    }

}

// 判断等待，业务，通知
// 数字 资源类
class Data{
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        // 当number等于0时，生产
        if (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        // 通知其他线程，生产完成了
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 当number不等于0时，消费
        if (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        // 通知其他线程，消费完了
        this.notifyAll();
    }

}
