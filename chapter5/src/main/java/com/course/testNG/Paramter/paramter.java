package com.course.testNG.Paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class paramter {

    @Test
    @Parameters({"name","age"})
    public void paramterTest(String name,int age  ){
        System.out.println("我的姓名是："+name+";"+"今年"+age+"岁了");
    }
}
