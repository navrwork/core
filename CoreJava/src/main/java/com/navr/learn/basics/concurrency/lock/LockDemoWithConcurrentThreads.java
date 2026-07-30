package com.navr.learn.basics.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemoWithConcurrentThreads {

    // Fair lock for the simple demo; final to avoid reassignment.
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {
        concurrentLockDemo();
        System.out.println();
        concurrentLockDemoWithFairness();
    }

    private static void concurrentLockDemo() {
        System.out.println("=== concurrentLockDemo ===");
        Callable<Boolean> callable1 = () -> {
            lock.lock();
            try {
                int random = new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName() + " acquired lock, Sleeping for " + random + " ms");
                TimeUnit.MILLISECONDS.sleep(random);
                System.out.println(Thread.currentThread().getName() + " finished sleeping");
                return true;
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing lock");
                lock.unlock();
            }
        };
        Callable<Boolean> callable2 = () -> {
            lock.lock();
            try {
                int random = new Random().nextInt(5000);
                System.out.println(Thread.currentThread().getName() + " acquired lock, Sleeping for " + random + " ms");
                TimeUnit.MILLISECONDS.sleep(random);
                System.out.println(Thread.currentThread().getName() + " finished sleeping");
                return true;
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing lock");
                lock.unlock();
            }
        };
        Callable<Boolean> callable3 = () -> {
            lock.lock();
            try {
                int random = new Random().nextInt(10000);
                System.out.println(Thread.currentThread().getName() + " acquired lock, Sleeping for " + random + " ms");
                TimeUnit.MILLISECONDS.sleep(random);
                System.out.println(Thread.currentThread().getName() + " finished sleeping");
                return true;
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing lock");
                lock.unlock();
            }
        };

        List<Callable<Boolean>> callables = new ArrayList<>();
        callables.add(callable1);
        callables.add(callable2);
        callables.add(callable3);

        ExecutorService exSvc = Executors.newFixedThreadPool(3);
        try {
            exSvc.invokeAll(callables);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            exSvc.shutdown();
        }
        try {
            if (!exSvc.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Timed out waiting for tasks to finish");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println("concurrentLockDemo completed");
    }

    /**
     * Demonstrates fairness: compares submission order to actual lock acquisition order.
     */
    private static void concurrentLockDemoWithFairness() throws Exception {
        System.out.println("=== concurrentLockDemoWithFairness ===");
        final ReentrantLock fairLock = new ReentrantLock(true);
        final int threads = 10;
        ExecutorService ex = Executors.newFixedThreadPool(threads);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threads);
        ConcurrentLinkedQueue<Integer> acquisitionOrder = new ConcurrentLinkedQueue<>();
        AtomicInteger submitCounter = new AtomicInteger(0);

        for (int i = 0; i < threads; i++) {
            final int id = i;
            submitCounter.incrementAndGet();
            ex.submit(() -> {
                try {
                    startLatch.await();
                    fairLock.lock();
                    try {
                        acquisitionOrder.add(id);
                        // hold lock a bit
                        TimeUnit.MILLISECONDS.sleep(5);
                    } finally {
                        fairLock.unlock();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
                return null;
            });
            // small gap to control submission ordering
            TimeUnit.MILLISECONDS.sleep(1);
        }

        System.out.println("Submitted threads count: " + submitCounter.get());
        startLatch.countDown();
        doneLatch.await(10, TimeUnit.SECONDS);
        ex.shutdownNow();

        System.out.println("Acquisition order: " + acquisitionOrder);
        System.out.println("Expected (submission) order: 0.." + (threads - 1));
        System.out.println("concurrentLockDemoWithFairness completed");
    }
}
