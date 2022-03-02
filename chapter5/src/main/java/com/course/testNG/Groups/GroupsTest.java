package com.course.testNG.Groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsTest {


//    客户端组
    @Test(groups = "client")
    private void groupTest1(){
        System.out.println("这是客户端测试用例1");
    }

    @Test(groups = "client")
    private void groupTest2(){
        System.out.println("这是客户端测试用例2");
    }


//    服务端组
    @Test(groups = "server")
    private void groupTest3(){
        System.out.println("这是服务端测试用例1");
    }

    @Test(groups = "server")
    private void groupTest4(){
        System.out.println("这是服务端测试用例2");
    }



//    组方法的前置步骤
    @BeforeGroups("server")
    private void berforGroup(){
        System.out.println("这是客户端组运行的前置步骤");
    }

//    组方法测试的后置步骤
    @AfterGroups("server")
    public void afterGroup(){
        System.out.println("这是客户端组运行的后置步骤");
    }


}
