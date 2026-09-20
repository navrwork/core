package com.navr.core.concepts.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates creating a thread by implementing the Runnable interface.
 * It simulates work by sleeping for a specified number of seconds.
 */
public class SimpleRunnableThread implements Runnable {
    private int seconds;

    public SimpleRunnableThread(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        System.out.println("Runnable Thread started: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Runnable Thread interrupted: " + Thread.currentThread().getName());
        }
        System.out.println("Runnable Thread ended: " + Thread.currentThread().getName());
    }
}
