package com.school.example.ExampleThreadTest.condition_one;

public class Condition {
    //以前用的都是 通过互斥 解决并发
    //condition作用是 不用互斥 实现多线程之间的通信 解决并发
    static test1 t = new test1();
    public  static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<11;i++){
                    t.sub(i);
                }

            }
        }).start();
        for (int i=1; i<11;i++){
            t.main(i);

        }

    }

}
