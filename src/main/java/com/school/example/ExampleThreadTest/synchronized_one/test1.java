package com.school.example.ExampleThreadTest.synchronized_one;

public class test1 {
    private boolean b = false;

    public  synchronized void  sub(int i) {

        while (!b){
            for (int j = 1; j < 11; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
            b =true;
        }
            notify();
        try{
            this.wait();
        }catch (Exception e){

        }

    }

    public  synchronized void main(int i){
        while (b){
            for (int j = 1; j < 21; j++) {
                System.out.println("this " + Thread.currentThread().getName() + "___" + j);
            }
            b =false;
        }
            notify();
        try{
            this.wait();
        }catch (Exception e){

        }



    }


}
