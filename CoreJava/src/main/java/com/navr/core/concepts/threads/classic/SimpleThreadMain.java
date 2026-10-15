package com.navr.core.concepts.threads.classic;

import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates the creation and execution of threads using different approaches:
 * 1. Extending the Thread class.
 * 2. Implementing the Runnable interface.
 * 3. Using a lambda expression to implement the Runnable interface.
 * 4. Using a method reference to implement the Runnable interface.
 */
public class SimpleThreadMain {

    public static void main(String[] args) {
        SimpleThreadMain main = new SimpleThreadMain();
        System.out.println("Main thread STARTED. tName: " + Thread.currentThread().getName());
        Thread t1 = main.simpleThreadV1();
        Thread t2 = main.simpleThreadV2();
        Thread t3 = main.simpleThreadV3();
        Thread t4 = main.simpleThreadV4();
        try {
            // main thread to wait for all threads to finish before exiting the main thread
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread ENDED. tName: " + Thread.currentThread().getName());
    }

    /**
     * Start a thread by creating an instance of a class that extends the Thread class. <br/>
     * Note that the start method belongs to the Thread class which in turn invokes the run() method of the class that extends the Thread class.
     */
    private Thread simpleThreadV1() {
        SimpleThread t = new SimpleThread("SimpleThread-1", 2);
        t.start();
        return t;
    }

    /**
     * Start a thread by creating an instance of a class that implements the Runnable interface. <br/>
     * Note that the run() method belongs to the Runnable interface, whereas the start() method belongs to the Thread class.
     */
    private Thread simpleThreadV2() {
        Runnable r = new SimpleRunnableThread(2);
        Thread t = new Thread(r, "SimpleRunnableThread-2");
        t.start();
        return t;
    }

    /**
     * Start a thread by creating an instance of the Thread class and passing a lambda expression that implements the run() method of the Runnable interface. <br/>
     */
    private Thread simpleThreadV3() {
        Thread t = new Thread(() -> {
            // run() method of the Runnable interface is implemented using a lambda expression
            System.out.println("simpleThreadV3: SimpleThread using lambda started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                System.out.println("simpleThreadV3: SimpleThread using lambda interrupted: " + Thread.currentThread().getName());
            }
            System.out.println("simpleThreadV3: SimpleThread using lambda ended: " + Thread.currentThread().getName());
        }, "SimpleThread-3");
        t.start();
        return t;
    }

    /**
     * Start a thread by creating an instance of the Thread class and passing a method reference that implements the run() method of the Runnable interface. <br/>
     */
    private Thread simpleThreadV4() {
        Thread t = new Thread(this::runnableMethod, "SimpleThread-4");
        t.start();
        return t;
    }

    /**
     * This method is used as a method reference for the Runnable interface's run() method. <br/>
     * Note that the method signature of this method is the same as that of the run() method of the Runnable interface,
     * which takes no arguments and returns void.
     */
    private void runnableMethod() {
        System.out.println("runnableMethod started: " + Thread.currentThread().getName());
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
        }
        System.out.println("runnableMethod ended: " + Thread.currentThread().getName());
    }

}
