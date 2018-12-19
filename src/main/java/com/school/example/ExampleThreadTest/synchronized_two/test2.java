package com.school.example.ExampleThreadTest.synchronized_two;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class test2 {
    private static test2  t = new test2();
    private test2(){};
    public static Map map = new HashMap();
    public  static int dataInt = 0;
    public static test2 get(){
        return t;
    }
    //线程之间的数据共享
    public static void main(String[] args) {
    for (int i=0; i<2; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dataInt = new Random().nextInt();
                map.put(Thread.currentThread().getName(),dataInt);
                System.out.println(Thread.currentThread().getName()+"__insert data="+dataInt);
                new A().get();
                new B().get();
            }
        }).start();

    }
    }



}
