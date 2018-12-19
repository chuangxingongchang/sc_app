package com.school.example.ExampleThreadTest.ThreadLock_one;

public class B {
    public void get(){
        System.out.println(Thread.currentThread().getName()+"--"+MyUser.getInstance().getAge());
    }
}
