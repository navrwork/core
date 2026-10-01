package com.navr.core.concepts.threads.classic;

public class MaxThreads {
    private static void doTask() {
        System.out.printf("doTask: Thread started: %s%n", Thread.currentThread().getName());
        try {
            Thread.sleep(30000); // Sleep for 30 seconds to simulate work
        } catch (InterruptedException e) {
            System.out.printf("doTask: Thread interrupted: %s%n", Thread.currentThread().getName());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return;
        }
        System.out.printf("doTask: Thread ended: %s%n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50000; i++) {
            new Thread(() -> doTask()).start();
        }
    }
}
