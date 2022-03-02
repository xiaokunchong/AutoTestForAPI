package com.course.testNG.Groups;

import org.testng.annotations.Test;

@Test(groups = "Teacher")
public class TeacherClass {

    public void student1(){
        System.out.println("我是Teacher的老师1");
    }


    public void student2(){
        System.out.println("我是Teacher的老师1");
    }
}
