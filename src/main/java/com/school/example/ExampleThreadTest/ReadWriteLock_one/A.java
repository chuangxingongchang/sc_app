package com.school.example.ExampleThreadTest.ReadWriteLock_one;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class A {

private  int data = 0;
int i = 123;
private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();



    public void get(){
       readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"_准备读_");
            Thread.sleep(1000);
        }catch (Exception e){

        }

        System.out.println(Thread.currentThread().getName()+"_读完了_"+data);
       readWriteLock.readLock().unlock();
    }

    public void put(int i){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"准备写");
        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        this.data = i ;
        System.out.println(Thread.currentThread().getName()+"_写完了_"+i);
        readWriteLock.writeLock().unlock();
    }



}
