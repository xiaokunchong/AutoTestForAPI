package com.course.testNG.Groups;

import org.testng.annotations.Test;

@Test(groups = "CLASS2")
public class StudentClass2 {

    public void student1(){
        System.out.println("我是class2的学生1");
    }


    public void student2(){
        System.out.println("我是class2的学生2");
    }
}
