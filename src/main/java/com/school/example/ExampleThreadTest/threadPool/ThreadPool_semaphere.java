package com.school.example.ExampleThreadTest.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadPool_semaphere {

    private  static  ExecutorService executorService = Executors.newCachedThreadPool();
    private static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {

        for (int i=0;i<10;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"——进入。"+"当前有灯 3-拿走的灯= _ "+(3-semaphore.availablePermits()));
                    }catch (Exception E){

                    }
                    try {
                        Thread.sleep((int)(Math.random()*10000));
                        System.out.println(Thread.currentThread().getName()+"——拿走-"+"灯--"+1);
                        semaphore.release();

                    }catch (Exception E){

                    }


                }
            });
        }
    }

}
