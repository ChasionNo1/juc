package com.chasion;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * @ClassName juc
 * @Description TODO
 * @Author chasion
 * @Date 2022/5/12 12:17
 */
@Slf4j
public class juc {

   @Test
    public void test1(){
       // 获取cpu核心数
       System.out.println(Runtime.getRuntime().availableProcessors());
   }

}
