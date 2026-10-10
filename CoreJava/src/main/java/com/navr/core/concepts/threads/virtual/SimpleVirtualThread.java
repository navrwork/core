package com.navr.core.concepts.threads.virtual;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * This class demonstrates the use of virtual threads in Java:
 * <li>Using the Thread.ofVirtual() builder to create virtual threads</li>
 * <li>Using a ThreadFactory to create virtual threads</li>
 * <li>Using an ExecutorService to manage virtual threads</li>
 */
public class SimpleVirtualThread {

    public static void main(String[] args) {
        virtualThreadDemoUsingBuilder();
        virtualThreadDemoUsingThreadFactory();
        virtualThreadDemoUsingExecutorService();
        virtualThreadDemoUsingExecutorServiceAndFactory();
    }

    /**
     * Note that this method's signature is same as that of the abstract method run() of the Runnable interface.
     * This is because we are using a lambda expression to create a Runnable instance that will be executed by the virtual thread.
     */
    private static void doTask() {
        System.out.printf("doTask: Thread started: %s%n", Thread.currentThread());
        try {
            Thread.sleep(5000); // Sleep for 5 seconds to simulate work
        } catch (InterruptedException e) {
            System.out.printf("doTask: Thread interrupted: %s%n", Thread.currentThread());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return;
        }
        System.out.printf("doTask: Thread ended: %s%n", Thread.currentThread());
    }

    private static void virtualThreadDemoUsingBuilder() {
        System.out.printf("virtualThreadDemoUsingBuilder: Main thread started: %s%n", Thread.currentThread());
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread thread = startVirtualThreadViaBuilder();
            threads.add(thread);
            System.out.printf("virtualThreadDemoUsingBuilder: Main thread: Started virtual thread: %s%n", thread);
        }

        // Join all threads to ensure they complete before the main thread ends
        for (Thread thread : threads) {
            try {
                thread.join(); // Main thread waits for the virtual thread to finish
            } catch (InterruptedException e) {
                System.out.printf("virtualThreadDemoUsingBuilder: Main thread: Thread interrupted: %s%n", thread.getName());
                Thread.currentThread().interrupt(); // Restore the interrupted status
                return;
            }
        }
        System.out.printf("virtualThreadDemoUsingBuilder: Main thread ended: %s%n", Thread.currentThread());
    }

    /**
     * Use Virtual thread builder to create a virtual thread and start it.
     * Note that the builder object is NOT thread-safe, so it should not be shared between threads. Each thread should create its own builder instance.
     *
     * @return
     */
    private static Thread startVirtualThreadViaBuilder() {
        Thread thread = Thread.ofVirtual().start(() -> doTask()); // Pass method reference to the virtual thread
        return thread;
    }

    private static void virtualThreadDemoUsingThreadFactory() {
        System.out.printf("virtualThreadDemoUsingThreadFactory: Main thread started: %s%n", Thread.currentThread());
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Thread thread = startVirtualThreadViaThreadFactory();
            threads.add(thread);
            System.out.printf("virtualThreadDemoUsingThreadFactory: Main thread: Started virtual thread: %s%n", thread);
        }

        // Join all threads to ensure they complete before the main thread ends
        for (Thread thread : threads) {
            try {
                thread.join(); // Main thread waits for the virtual thread to finish
            } catch (InterruptedException e) {
                System.out.printf("virtualThreadDemoUsingThreadFactory: Main thread: Thread interrupted: %s%n", thread.getName());
                Thread.currentThread().interrupt(); // Restore the interrupted status
                return;
            }
        }
        System.out.printf("virtualThreadDemoUsingThreadFactory: Main thread ended: %s%n", Thread.currentThread());
    }

    /**
     * Use Virtual thread factory to create a virtual thread and start it.
     * Note that the factory object is thread-safe, so it can be shared between threads.
     *
     * @return
     */
    private static Thread startVirtualThreadViaThreadFactory() {
        ThreadFactory threadFactory = Thread.ofVirtual().factory(); // Create a virtual thread factory
        Thread thread = threadFactory.newThread(() -> doTask()); // Pass method reference to the virtual thread
        return thread;
    }

    /**
     * Use ExecutorService to create a virtual thread and start it.
     * <li>The executor service is thread-safe, so it can be shared between threads.</li>
     * <li>Since we're using try-with-resources, the executor service will be automatically closed at the end of the block, which will also terminate all virtual threads that were started by it.</li>
     * <li>This is important to prevent resource leaks and ensure that all threads are properly terminated.</li>
     * <li>There's no need for joining the threads here, as the executor service will wait for all tasks to complete before shutting down.</li>
     * <li>However, if you want to wait for all tasks to complete before proceeding, you can use the awaitTermination() method of the executor service after the try-with-resources block.</li>
     */
    private static void virtualThreadDemoUsingExecutorService() {
        System.out.printf("createVirtualThreadUsingExecutorService: Main thread started: %s%n", Thread.currentThread());
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                executorService.submit(() -> doTask());
            }
        } catch (Exception e) {
            System.out.printf("createVirtualThreadUsingExecutorService: Main thread: Exception occurred: %s%n", e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return;
        }
        // The executor service will be automatically closed here, which will also terminate all virtual threads that were started by it.
        // Also, all tasks submitted to the executor service will be completed before the executor service is closed, so there's no need for joining the threads here.

        System.out.printf("createVirtualThreadUsingExecutorService: Main thread ended: %s%n", Thread.currentThread());
    }

    /**
     * Same as virtualThreadDemoUsingExecutorService(), but using a ThreadFactory to create the virtual threads.
     * The factory specifies the name of the virtual threads, which can be useful for debugging and logging purposes.
     */
    private static void virtualThreadDemoUsingExecutorServiceAndFactory() {
        System.out.printf("virtualThreadDemoUsingExecutorServiceAndFactory: Main thread started: %s%n", Thread.currentThread());
        ThreadFactory threadFactory = Thread.ofVirtual().name("MyVirtualThread-", 0).factory(); // Create a virtual thread factory
        var futureList = new ArrayList<Future<?>>();
        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory)) {
            for (int i = 0; i < 5; i++) {
                Future<?> future = executorService.submit(() -> doTask());
                futureList.add(future);
            }
            // Wait for all tasks to complete
            for (Future<?> future : futureList) {
                try {
                    future.get(); // Wait for the task to complete
                } catch (InterruptedException | ExecutionException e) {
                    System.out.printf("virtualThreadDemoUsingExecutorServiceAndFactory: Main thread: Exception occurred while waiting for task completion: %s%n", e.getMessage());
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                    return;
                }
            }
        } catch (Exception e) {
            System.out.printf("virtualThreadDemoUsingExecutorServiceAndFactory: Main thread: Exception occurred: %s%n", e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return;
        }

        System.out.printf("virtualThreadDemoUsingExecutorServiceAndFactory: Main thread ended: %s%n", Thread.currentThread());
    }

}
