package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testMethodDemo {


    @Test
    public void barcodePay(){
        Assert.assertEquals(1,3);
    }

    @Test
    public void tokenPay(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void dpsReguest(){
        Assert.assertEquals("aaa","aab");
    }

    @Test
    public void refund(){
        Reporter.log("这是人工写的日志");
        throw new RuntimeException("这是程序的运行时异常");


    }


}
