package com.school.example.ExampleThreadTest.ReadWriteLock_one;

import java.util.Random;

public class test {

    static A a = new A();
    public static void main(String[] args) {

        for (int i=0; i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.get();
                }
            }).start();
        }
        for (int i=0; i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.put(new Random().nextInt());
                }
            }).start();
        }



    }
}
