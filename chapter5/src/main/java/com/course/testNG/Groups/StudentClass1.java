package com.course.testNG.Groups;

import org.testng.annotations.Test;

@Test(groups = "CLASS1")
public class StudentClass1 {

    public void student1(){
        System.out.println("我是class1的学生1");
    }


    public void student2(){
        System.out.println("我是class1的学生2");
    }
}
