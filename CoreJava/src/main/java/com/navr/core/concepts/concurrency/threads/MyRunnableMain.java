package com.navr.core.concepts.concurrency.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class demonstrates the creation and execution of threads using both the Runnable interface and ExecutorService.
 * It includes methods to start a thread with a Runnable task and wait for its completion, as well as to submit a task to an ExecutorService.
 */
public class MyRunnableMain {
    public static void main(String[] args) {
        try {
            runnableWithJoin();
        } catch (RuntimeException e) {
            System.out.printf("MyRunnableMain: Caught RuntimeException during runnableWithJoin(): %s%n", e.getMessage());
        }

        try {
            runnableWithExecutorService();
        } catch (RuntimeException e) {
            System.out.printf("MyRunnableMain: Caught RuntimeException during runnableWithExecutorService(): %s%n", e.getMessage());
        }
        System.out.printf("MyRunnableMain: Main thread ENDED. tName: %s%n", Thread.currentThread().getName());
    }

    /**
     * This method demonstrates the creation and execution of a thread using a Runnable task.
     * It starts the thread and waits for its completion with a timeout of 5 seconds.
     */
    private static void runnableWithJoin() {
        MyRunnableTask myRunnableTask = new MyRunnableTask();
        Thread thread = new Thread(myRunnableTask);
        System.out.printf("runnableWithJoin: Starting thread with name: %s%n", thread.getName());
        thread.start(); // Start the thread
        try {
            thread.join(TimeUnit.SECONDS.toMillis(5)); // Wait for the thread to finish with a timeout of 5 seconds
        } catch (InterruptedException e) {
            System.out.printf("runnableWithJoin: Thread with name: %s was interrupted.%n", thread.getName());
            Thread.currentThread().interrupt(); // Restore the interrupted status for code higher up the call stack to handle it appropriately
        }
        if (thread.isAlive()) {
            System.out.printf("runnableWithJoin: Thread with name: %s is still running.%n", thread.getName());

            //
            // thread.stop() method call is deprecated and unsafe, so we should avoid using it.
            // Instead, we can interrupt the thread or use a flag to signal it to stop gracefully.
            //
            // Interrupt the thread to signal it to stop: This is a cooperative approach, meaning the thread
            // should check for interruption and exit gracefully.
            //
            // In this case, MyRunnableTask should check for interruption and exit gracefully.
            //
            thread.interrupt();

            if (thread.isAlive()) {
                System.out.printf("runnableWithJoin: Thread with name: %s is still alive after interrupt.%n", thread.getName());
            } else {
                System.out.printf("runnableWithJoin: Thread with name: %s has stopped after interrupt.%n", thread.getName());
            }

            System.out.printf("runnableWithJoin: Thread with name: %s has been interrupted.%n", thread.getName());
        } else {
            System.out.printf("runnableWithJoin: Thread with name: %s has finished.%n", thread.getName());
        }

        System.out.printf("runnableWithJoin: Method ENDED. tName: %s%n%n", Thread.currentThread().getName());
    }

    /**
     * This method demonstrates the use of ExecutorService to manage thread execution.
     * It submits a Runnable task to the executor service and waits for its completion with a timeout.
     */
    private static void runnableWithExecutorService() {
        MyRunnableTask myRunnableTask = new MyRunnableTask();
        java.util.concurrent.ExecutorService executorService = java.util.concurrent.Executors.newSingleThreadExecutor();
        System.out.printf("runnableWithExecutorService: Submitting task to executor service.%n");
        Future<?> future = executorService.submit(myRunnableTask);
        try {
            future.get(5, TimeUnit.SECONDS); // Wait for the task to complete
        } catch (ExecutionException e) {
            System.out.printf("runnableWithExecutorService: Task threw an exception: %s%n", e.getCause().getMessage());
        } catch (TimeoutException e) {
            System.out.printf("runnableWithExecutorService: Task timed out.%n");
            future.cancel(true); // Cancel the task if it times out
        } catch (InterruptedException e) {
            System.out.printf("runnableWithExecutorService: Main thread was interrupted.%n");
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new RuntimeException(e);
        } finally {
            if (future.isDone()) {
                System.out.printf("runnableWithExecutorService: Task has completed.%n");
            } else {
                System.out.printf("runnableWithExecutorService: Task is still running.%n");
            }
            System.out.println("runnableWithExecutorService: Shutting down executor service...");
            executorService.shutdown(); // Shutdown the executor service
        }

        System.out.printf("runnableWithExecutorService: Method ENDED. tName: %s%n%n", Thread.currentThread().getName());
    }
}
