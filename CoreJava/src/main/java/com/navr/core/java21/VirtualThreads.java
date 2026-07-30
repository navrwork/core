package com.navr.core.java21;

import java.util.concurrent.Executors;

public class VirtualThreads {
    public static void main(String[] args)  throws InterruptedException {
        virtualThreadsUsingThreadBuilder();
        virtualThreadsUsingExecutors();
        virtualThreadsDemo();
    }

    private static void virtualThreadsUsingThreadBuilder() throws InterruptedException {
        System.out.printf("%nvirtualThreadsUsingThreadBuilder starts %n");
        Thread virtualThread = Thread.ofVirtual()
                .name("MyVirtualThread")
                .start(() -> {
                    System.out.println("Running in: " + Thread.currentThread());
                    try {
                        Thread.sleep(1000);  // Blocks efficiently
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
        virtualThread.join();  // blocks the calling thread until the virtual thread completes execution.
        System.out.printf("virtualThreadsUsingThreadBuilder ends%n");
    }

    private static void virtualThreadsDemo() throws InterruptedException {
        System.out.printf("%nvirtualThreadsDemo starts %n");
        for (int i = 0; i < 10; i++) {
            Thread virtualThread = Thread.ofVirtual()
                    .name("MyVirtualThread-"+i)
                    .start(() -> {
                        System.out.println("Running in: " + Thread.currentThread());
                        try {
                            Thread.sleep(500);  // Blocks efficiently
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
            virtualThread.join();  // blocks the calling thread until the virtual thread completes execution.
        }
        System.out.printf("virtualThreadsDemo ends%n");
    }

    private static void virtualThreadsUsingExecutors() throws InterruptedException {
        System.out.printf("%nvirtualThreadsUsingExecutors starts %n");
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Task " + taskId + " done");
                });
            }
        }
        System.out.printf("virtualThreadsUsingExecutors ends%n");
    }
}
