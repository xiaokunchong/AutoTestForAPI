package com.course.testNG;

import org.testng.annotations.Test;

public class timeOutTest {

    @Test(timeOut = 200)  //该属性指的是，期望方法在200毫秒内执行完成。如果符合，则测试通过
    public void testSuccess() throws InterruptedException{
        Thread.sleep(100);
        System.out.printf("我是成功的超时测试");
    }

    @Test(timeOut = 2600)
    public void testTimeOutFail() throws InterruptedException{
        Thread.sleep(300);
        System.out.printf("我是失败的超时测试");
    }
}
