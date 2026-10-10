package com.navr.core.concepts.threads.threadlocal;

/**
 * This class demonstrates the use of ThreadLocal in Java.
 * It shows how each thread can have its own independent value for a ThreadLocal variable.
 * The main thread sets and retrieves values from the ThreadLocal variable, and the output shows that each thread has its own value.
 * <p>
 * Note that the value of the thread local variable remains the same across method calls within the same thread, but it is independent across different threads.
 */
public class ThreadLocalSingleThread {
    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        String tlocalValue = threadLocal.get(); // This will return null because the ThreadLocal value is not set in the main thread
        System.out.printf("main: threadName=%s, Initial threadLocal.value: %s%n", Thread.currentThread().getName(), tlocalValue);
        methodOne();
        methodTwo();
        tlocalValue = threadLocal.get(); // This will return last set value in the main thread, which is "value-two"
        System.out.printf("main: threadName=%s, threadLocal.value after method calls: %s%n", Thread.currentThread().getName(), tlocalValue);
        threadLocal.remove(); // Clean up the ThreadLocal variable to prevent memory leaks
        System.out.printf("main: threadName=%s, Final threadLocal.value after remove(): %s%n", Thread.currentThread().getName(), threadLocal.get());
    }


    private static void methodOne() {
        threadLocal.set("value-one");
        System.out.printf("methodOne: threadName=%s, threadLocal.value updated to %s%n", Thread.currentThread().getName(), threadLocal.get());
    }

    private static void methodTwo() {
        threadLocal.set("value-two");
        System.out.printf("methodTwo: threadName=%s, threadLocal.value updated to %s%n", Thread.currentThread().getName(), threadLocal.get());
    }
}
