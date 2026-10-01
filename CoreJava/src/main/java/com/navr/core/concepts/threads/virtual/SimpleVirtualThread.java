package com.navr.core.concepts.threads.virtual;

import java.util.ArrayList;

/**
 * This class demonstrates the use of virtual threads in Java.
 * It creates multiple virtual threads that execute a task concurrently.
 * Each virtual thread sleeps for few seconds to simulate work.
 */
public class SimpleVirtualThread {

    public static void main(String[] args) {
        System.out.printf("Main thread started: %s%n", Thread.currentThread());
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread thread = startVirtualThread();
            threads.add(thread);
            System.out.printf("Main thread: Started virtual thread: %s%n", thread);
        }

        // Join all threads to ensure they complete before the main thread ends
        for (Thread thread : threads) {
            try {
                thread.join(); // Main thread waits for the virtual thread to finish
            } catch (InterruptedException e) {
                System.out.printf("Main thread: Thread interrupted: %s%n", thread.getName());
                Thread.currentThread().interrupt(); // Restore the interrupted status
                return;
            }
        }
        System.out.printf("Main thread ended: %s%n", Thread.currentThread());
    }

    /**
     * Note that this method's signature is same as that of the abstract method run() of the Runnable interface.
     * This is because we are using a lambda expression to create a Runnable instance that will be executed by the virtual thread.
     */
    private static void doTask() {
        System.out.printf("doTask: Thread started: %s%n", Thread.currentThread());
        try {
            Thread.sleep(30000); // Sleep for 30 seconds to simulate work
        } catch (InterruptedException e) {
            System.out.printf("doTask: Thread interrupted: %s%n", Thread.currentThread());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return;
        }
        System.out.printf("doTask: Thread ended: %s%n", Thread.currentThread());
    }

    private static Thread startVirtualThread() {
        Thread thread = Thread.ofVirtual().start(() -> doTask()); // Pass method reference to the virtual thread
        return thread;
    }

}
