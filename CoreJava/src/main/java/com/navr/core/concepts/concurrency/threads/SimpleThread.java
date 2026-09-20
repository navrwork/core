package com.navr.core.concepts.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates creating a thread by extending the Thread class.
 * It simulates work by sleeping for a specified number of seconds.
 */
public class SimpleThread extends Thread {

    private int seconds;

    public SimpleThread(String name, int seconds) {
        super(name);
        this.seconds = seconds;
    }

    @Override
    public void run() {
        System.out.println("Thread started: " + getName());
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + getName());
        }
        System.out.println("Thread ended: " + getName());
    }
}
