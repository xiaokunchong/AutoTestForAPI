package com.course.testNG;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void IgnoreTest1(){
        System.out.println("我是忽略测试1");
    }

    @Test(enabled = false)
    public void IgnoreTest2(){
        System.out.println("我是忽略测试2");
    }

    @Test(enabled = true)
    public void IgnoreTest3(){
        System.out.println("我是忽略测试3");
    }

}
