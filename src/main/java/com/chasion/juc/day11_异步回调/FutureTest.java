package com.chasion.juc.day11_异步回调;


import java.util.concurrent.CompletableFuture;

/**
 * @ClassName FutureTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/17 15:02
 *
 * 异步调用：ajax
 */
public class FutureTest {

    public static void main(String[] args) {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(FutureTest::fetchPrice);
        // 如果执行成功
        cf.thenAccept(result -> System.out.println("price: " + result));
        // 如果执行异常
        cf.exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static Double fetchPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if (Math.random() < 0.3){
            throw new RuntimeException("fetch price failed");
        }
        return 5 + Math.random() * 20;
    }

}
