package com.course.testNG.Paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataproviderTest {



//    ************************************方法一：简单地把参数传到用例中****************************************
    //    这是测试主方法，其中参数做了变量处理，并且给测试方法约定参数使用dataProvider方式，
    @Test(dataProvider = "DATA")
    public void testDataProvider(String name, int age) {
        System.out.println("name is :" + name + ";age is" + age);
    }

    //    这是数据提供者，提供者的名字为DATA，提供者返回的是一个对象
    @DataProvider(name = "DATA")
    public Object[][] providerData() {
        Object[][] OBJ = new Object[][]{
                {"zhangsan", 20},
                {"lisi", 16},
                {"wangwu", 30},
                {"zhangliu", 31},
        };
        return OBJ;
    }

    //    *************************************通过用例名字，把参数传到用例中*****************************************************

    //    这是一个测试方法test1
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1的的名字是" + name + ";age是" + age);
    }

    //这是另一个测试方法test2
    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2的的名字是" + name + ";age是" + age);
    }


    //    这是一个数据提供者的方法
    @DataProvider(name="methodData")    //数据提供者的注解，以及名字定义。
    public Object[][] methodDataProvider2(Method method) {      //这个对象需要传参，这个是java里面的方法
        Object[][] result = null;    //初始化一个对象
        if (method.getName().equals("test1")) {
            result = new Object[][]{     //满足条件，则创建一个对象，并赋值给出实例
                    {"zhangsan", 20},
                    {"lisi", 18}
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"wangwu", 12},
                    {"zhaoliu", 8},
            };
        };

        return result;    //最后返回对象实例，提供给测试方法使用。
    }

}