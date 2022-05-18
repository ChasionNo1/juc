package com.chasion.juc.day03_集合安全问题;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MapTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/14 17:46
 */
public class MapTest {

    public static void main(String[] args) {
//        HashMap<Object, Object> map = new HashMap<>();
//        Map<Object, Object> map = new ConcurrentHashMap<>();
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }
}
