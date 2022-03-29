package com.course.testNG.Suit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
public class suitConfig {

    @BeforeSuite
    public void beforSuit(){
        System.out.println("befor suit 运行啦");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("after suit 运行啦");
    }

    @BeforeTest
    public void beforTest(){
        System.out.println("测试用例执行前的操作");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("测试用例执行后的操作");
    }

}
