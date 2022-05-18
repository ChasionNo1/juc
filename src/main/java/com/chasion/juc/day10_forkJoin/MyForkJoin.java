package com.chasion.juc.day10_forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName MyForkJoin
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/16 21:34
 *
 * forkJoin的使用：
 * 1、通过forkJoinPool对象执行
 * 2、计算任务，forkjoinPool.execute(ForkJoinTask task)
 * 3、计算类要继承 ForkJoinTask
 */
public class MyForkJoin extends RecursiveTask<Long> {

    private long start;
    private long end;

    // 临界值
    private long temp = 10000L;

    public MyForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            // 使用forkjointask，递归任务
            long middle = start + (end - start) / 2;
            MyForkJoin task1 = new MyForkJoin(start, middle);
            // 拆分任务，把任务压入线程队列
            task1.fork();
            MyForkJoin task2 = new MyForkJoin(middle + 1, end);
            task2.fork();
            // 合并
            return task1.join() + task2.join();
        }
    }
}
