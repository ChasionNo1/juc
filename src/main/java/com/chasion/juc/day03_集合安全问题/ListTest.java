package com.chasion.juc.day03_集合安全问题;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ListTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/14 12:08
 *
 * java.util.ConcurrentModificationException  并发修改异常
 */
public class ListTest {

    public static void main(String[] args) {
        // 并发下，不安全
        // 怎样解决？
        /*
        * 1、使用vector
        * 2、使用Collections.synchronizedList
        * 3、使用CopyOnWriteArrayList，写入时复制
        *    多个线程调用的时候，list，读取的时候，固定的，写入（覆盖）
        *    在写入的时候，避免覆盖，造成数据问题！
        * */
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
