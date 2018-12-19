package com.school.example.ExampleThreadTest.condition_one;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test1 {
    private boolean b = false;
    private Lock lock =new ReentrantLock();
    private Condition condition =lock.newCondition();
    private Condition condition2 =lock.newCondition();
    private Condition condition3 =lock.newCondition();

    public   void  sub(int i) {
        lock.lock();
        while (!b){
            for (int j = 1; j < 11; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
            b =true;
        }
        try{
            condition.signal();
            condition.await();
        }catch (Exception e){

        }
        lock.unlock();
    }

    public   void main(int i){
        lock.lock();
        while (b){
            for (int j = 1; j < 21; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
            b =false;
        }
        try{
           condition.signal();
            condition.await();
        }catch (Exception e){

        }
        lock.unlock();



    }


}
