package com.school.example.ExampleThreadTest.synchronized_one;

public class ThreadTest {
    static test1 t = new test1();
    //线程之间的互斥
    public  static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<11;i++){
                    System.out.println("sub"+i);
                    t.sub(i);
                }

            }
        }).start();
        for (int i=1; i<11;i++){
            System.out.println("main"+i);
            t.main(i);

        }

    }

}
