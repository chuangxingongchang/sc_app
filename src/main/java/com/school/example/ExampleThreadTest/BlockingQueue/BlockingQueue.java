package com.school.example.ExampleThreadTest.BlockingQueue;

public class BlockingQueue {
    //使用BlockingQueue实现多线程之前的通信
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
