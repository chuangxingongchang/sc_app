package com.school.example.ExampleThreadTest.ReadWriteLock_one.Cache;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheReadWriteLock {
    static A a = new A();
    public static void main(String[] args) {
        for (int i = 0 ; i<3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.get();
                }
            }).start();
        }

    }

    static class A{
        private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private static  Object values = null;

        public void get(){
            readWriteLock.readLock().lock();
            System.out.println("before read————"+values);
            try{
                if(values == null){
                    readWriteLock.readLock().unlock();
                    readWriteLock.writeLock().lock();
                    if (values == null) {
                        values = new Random().nextInt();
                    }
                }
                readWriteLock.writeLock().unlock();
                readWriteLock.readLock().unlock();
            }catch (Exception e){
            }
            try {
                readWriteLock.readLock().unlock();
            }catch (Exception e){

            }

            System.out.println("end read————"+values);
        }
    }

}
