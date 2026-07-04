package com.navr.learn.concurrency.threads;

public class MyRunnableMain {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnableTask());
        thread.start();
    }
}
