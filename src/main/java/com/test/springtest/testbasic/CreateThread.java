package com.test.springtest.testbasic;

public class CreateThread implements Runnable {
   String name;

    public CreateThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + ":Oh, I am CreateThread");
    }
}