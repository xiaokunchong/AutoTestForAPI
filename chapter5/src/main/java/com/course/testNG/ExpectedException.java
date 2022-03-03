package com.course.testNG;

import org.testng.annotations.Test;

public class ExpectedException {

//    什么时候会用到异常测试？
//    在我们期望结果为某一个异常的时候，如，非法传参，期望程序能抛出异常
//    也就是说我们的预期结果就是这个异常

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
//        这个用例期望的是抛出异常，但实际程序并没有问题，所以这个用例并不抛异常，不符合期望，因此这是一个失败的异常测试
        System.out.println("这是一个失败的异常测试");
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
//这个用例期望是抛出异常，程序实际运行也是抛异常的，因此是符合预期的。所以这是一个成功的异常测试
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();

    }


}
