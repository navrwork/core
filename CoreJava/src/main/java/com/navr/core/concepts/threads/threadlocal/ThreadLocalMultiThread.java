package com.navr.core.concepts.threads.threadlocal;

/**
 * This class demonstrates the use of ThreadLocal in a multi-threaded environment.
 * It shows how each thread can have its own independent value for a ThreadLocal variable.
 * The main thread creates two threads, each of which sets and retrieves its own value from the ThreadLocal variable.
 * The output shows that each thread has its own value, and the main thread's value remains unchanged.
 */
public class ThreadLocalMultiThread {

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.printf("main: threadName=%s, Initial threadLocal.value: %s%n", Thread.currentThread().getName(), threadLocal.get());
        Thread t1 = new Thread(ThreadLocalMultiThread::runnableMethod, "Thread-1");
        Thread t2 = new Thread(ThreadLocalMultiThread::runnableMethod, "Thread-2");
        // Start both threads
        t1.start();
        t2.start();
        // Wait for both threads to finish
        t1.join();
        t2.join();
        // main thread's ThreadLocal value remains unchanged
        System.out.printf("main: threadName=%s, Final threadLocal.value: %s%n", Thread.currentThread().getName(), threadLocal.get());
    }

    /**
     * This method is executed by each thread.
     * It sets a value in the ThreadLocal variable specific to the current thread and prints it.
     * Each thread will have its own independent value for the ThreadLocal variable, demonstrating that ThreadLocal provides thread-local storage.
     */
    private static void runnableMethod() {
        threadLocal.set(Thread.currentThread().getName()); // ThreadLocal value gets updated for the current thread ONLY
        System.out.printf("runnableMethod: threadName=%s, threadLocal.value= %s%n", Thread.currentThread().getName(), threadLocal.get());
    }
}
