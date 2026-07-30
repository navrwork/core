package com.navr.core.java8;

//
// reference: https://www.baeldung.com/java-lambdas-vs-anonymous-class
//
public class LambdaExpressionExample {

    public static void main(String[] args) {
        //
        // lambda expression implementing the Runnable interface
        // Here, we’re using an anonymous method. This will resolve to the run() method of the Runnable interface.
        //
        Thread t1 = new Thread(() -> System.out.println("Thread: " + Thread.currentThread().getName() + " started"));
        t1.start();
    }
}