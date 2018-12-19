package com.school.example.ExampleThreadTest.ThreadLock_one;

public class A {

    public static void get(){
        System.out.println(Thread.currentThread().getName()+"--"+MyUser.getInstance().getAge());
    }
}
