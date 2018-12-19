package com.school.example.ExampleThreadTest.ThreadLock_one;

public class MyUser {
    private static ThreadLocal<MyUser> local = new ThreadLocal();

    private String name;
    private int age;

    private MyUser(){};

    public static MyUser getInstance(){
        MyUser instance = local.get();
        while (instance == null){
            instance = new MyUser();
            local.set(instance);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
