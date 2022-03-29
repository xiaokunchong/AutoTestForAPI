package com.course.testNG.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnXml {

        @Test
      public void test1(){
          System.out.printf("test1线程id是：%s%n",Thread.currentThread().getId());
      }
       @Test
    public void test2(){
        System.out.printf("test2线程id是：%s%n",Thread.currentThread().getId());
    }
       @Test
    public void test3(){
        System.out.printf("test3线程id是：%s%n",Thread.currentThread().getId());
    }

}
