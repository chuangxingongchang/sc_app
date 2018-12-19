package com.school.example.ExampleThreadTest.synchronized_two;

public class A {

    public static void get(){
        System.out.println(Thread.currentThread().getName()+"--"+test2.map.get(Thread.currentThread().getName()));
    }
}
