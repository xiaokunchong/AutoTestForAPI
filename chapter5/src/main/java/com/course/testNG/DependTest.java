package com.course.testNG;

import org.testng.annotations.Test;

public class DependTest {
//    依赖测试:
//    被依赖的步骤，如果执行失败，则依赖的步骤就会跳过。
    @Test
    public void dependTest(){
        System.out.println("这是测试用例的依赖步骤");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"dependTest"})
    public void testMain(){
        System.out.println("这是主要的测试用例");
    }
}
