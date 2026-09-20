package com.navr.core.concepts.concurrency.threads;

/**
 * This class demonstrates the creation of a large number of threads to check the thread limit.
 * It creates 50,000 threads, each executing a method that simulates work by sleeping for 10 seconds.
 * The program prints messages indicating the start and end of each thread's execution.
 */
public class ThreadLimitCheck {

    public static void main(String[] args) {
        ThreadLimitCheck check = new ThreadLimitCheck();

        for (int i = 0; i < 50000; i++) {
            Thread t = new Thread(() -> {
                check.doSomething();
            });
            t.start();
        }

        System.out.println("Main thread ENDED. tName: " + Thread.currentThread().getName());
    }

    private void doSomething() {
        System.out.println("doSomething method started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000); // Sleep for 10 seconds to simulate work
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. tName: " + Thread.currentThread().getName());
            e.printStackTrace();
        }
        System.out.println("doSomething method ENDED. tName: " + Thread.currentThread().getName());
    }
}
