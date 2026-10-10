package com.navr.core.concepts.threads.classic;

/**
 * This class demonstrates various ways to create and manage threads in Java.
 * It includes examples of creating threads by extending the Thread class,
 * implementing the Runnable interface, using anonymous classes, and using lambda expressions.
 */
public class ThreadCreationDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started. tName: " + Thread.currentThread().getName());

        ThreadCreationDemo demo = new ThreadCreationDemo();
        demo.simpleThread(); // legacy
        demo.runnableThread(); // legacy
        demo.runnableAnonymous(); // legacy
        demo.runnableLambda();

        System.out.println("Main thread ENDED. tName: " + Thread.currentThread().getName());
    }

    /**
     * Demonstrates creating a thread using a Runnable implementation with a lambda expression.
     */
    private void runnableLambda() throws InterruptedException {
        System.out.println("runnableLambda method started by " + Thread.currentThread().getName());
        Runnable runnable = () -> {
            System.out.println("Runnable started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Sleep for 2 seconds to simulate work
            } catch (InterruptedException e) {
                System.out.println("Runnable interrupted: " + Thread.currentThread().getName());
            }
            System.out.println("Runnable ended: " + Thread.currentThread().getName());
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join(); // Wait for the thread to finish before proceeding
        System.out.println("runnableLambda method ENDED. tName: " + Thread.currentThread().getName());
    }

    /**
     * Demonstrates creating a thread using a Runnable implementation.
     */
    private void runnableThread() throws InterruptedException {
        System.out.println("runnableThread method started by " + Thread.currentThread().getName());
        Runnable runnable = new SimpleRunnableThread(2);
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join(); // Wait for the thread to finish before proceeding
        System.out.println("runnableThread method ENDED. tName: " + Thread.currentThread().getName());
    }

    /**
     * Demonstrates creating a thread using an anonymous Runnable implementation.
     */
    private void runnableAnonymous() throws InterruptedException {
        System.out.println("runnableAnonymous method started by " + Thread.currentThread().getName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable started: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds to simulate work
                } catch (InterruptedException e) {
                    System.out.println("Runnable interrupted: " + Thread.currentThread().getName());
                }
                System.out.println("Runnable ended: " + Thread.currentThread().getName());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join(); // Wait for the thread to finish before proceeding
        System.out.println("runnableAnonymous method ENDED. tName: " + Thread.currentThread().getName());
    }

    /**
     * Demonstrates creating a custom thread by extending the Thread class.
     */
    private void simpleThread() throws InterruptedException {
        System.out.println("simpleThread method started by " + Thread.currentThread().getName());
        SimpleThread t1 = new SimpleThread("SimpleThread-1", 1);
        SimpleThread t2 = new SimpleThread("SimpleThread-2", 2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("simpleThread method ENDED. tName: " + Thread.currentThread().getName());
    }
}
