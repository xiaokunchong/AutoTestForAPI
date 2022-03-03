package com.course.testNG.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnAnnotion {

//    invocationCount参数指的是调用的次数，默认是1个线程，调用N次；threadPoolSize指的是定义线程池中的线程数。
    @Test(invocationCount = 5,threadPoolSize = 3)
    public void test(){
        System.out.println("我是多线程测试");
        System.out.printf("当前的线程id是 ： %s%n",Thread.currentThread().getId());
    }
}
