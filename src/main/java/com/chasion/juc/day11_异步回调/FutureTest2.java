package com.chasion.juc.day11_异步回调;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName FutureTest2
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/17 16:17
 *
 * 如果只是实现了异步回调机制，我们还看不出CompletableFuture相比Future的优势。
 * CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行，
 * 例如，定义两个CompletableFuture，第一个CompletableFuture根据证券名称查询证券代码，
 * 第二个CompletableFuture根据证券代码查询证券价格，这两个CompletableFuture实现串行操作如下：
 */
public class FutureTest2 {
    public static void main(String[] args) {
        // 第一个任务：
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));
        // cfQuery成功后继续执行下一个任务
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(FutureTest2::fetchPrice);
        // cfFetch成功后打印结果：
        cfFetch.thenAccept(result -> System.out.println("price: " + result));
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static String queryCode(String name){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "601857";
    }

    static Double fetchPrice(String code){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 5 + Math.random() * 20;
    }
}
