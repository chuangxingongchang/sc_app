package com.school.example.ExampleThreadTest.ThreadLock_one;

import java.util.Random;

public class test2 {
    private static test2 t = new test2();
    private test2(){};
    public static test2 getInstance(){
        return t;
    }

    //线程之间的数据共享之 Thread Local 一个Local只能存储一个变量（Object）
    public static void main(String[] args) {
    for (int i=0; i<2; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyUser user = MyUser.getInstance();
                user.setAge(new Random().nextInt());
                System.out.println(Thread.currentThread().getName()+"__insert data="+user.getAge());
                new A().get();
                new B().get();
            }
        }).start();

    }
    }



}
