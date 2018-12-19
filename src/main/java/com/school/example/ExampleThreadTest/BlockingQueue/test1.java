package com.school.example.ExampleThreadTest.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class test1 {
    private BlockingQueue blockingQueue1 = new ArrayBlockingQueue(1);
    private BlockingQueue blockingQueue2 = new ArrayBlockingQueue(1);
    {
        try {
            blockingQueue2.put(1);
        }catch (Exception E){

        }

    }
    public   void  sub(int i) {
        try {
            blockingQueue1.put(1);

            for (int j = 1; j < 11; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
        }catch (Exception E){

        }

        try {
            blockingQueue2.take();

        }catch (Exception E) {

        }

    }

    public   void main(int i){
        try {
            blockingQueue2.put(1);
            for (int j = 1; j < 21; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
        }catch (Exception E) {

        }



        try {
            blockingQueue1.take();

        }catch (Exception E) {

        }
    }


}
