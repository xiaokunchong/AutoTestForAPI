package com.course.testNG;
import org.testng.annotations.*;

public class BasicAnnotation {

//    最基本的注解，把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");

    }
    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

//    每个用例执行的前置步骤
    @BeforeMethod
    public void beforMethod(){
        System.out.println("这是在测试用例之前执行的前置步骤");

    }
//    每个用例执行的后置步骤
    @AfterMethod
    public void afterMethod(){
        System.out.println("这是在测试用例之后执行的后置步骤");
    }
    @BeforeClass
    public void beforClass(){
        System.out.println("这是类运行之前的执行的操作");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("这是类运行后执行的操作");
    }

    @BeforeSuite
    public void beforSuit(){
        System.out.println("这是测试套件执行之前运行的方法");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("这是测试套件之后执行的方法");
    }




}
