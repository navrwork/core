package com.navr.core.concepts.threads.modern;

public class ModernThread {

    public static void main(String[] args) {
        demoThreadOfPlatform();
        demoLambdaThread();
        demoThreadOfPlatformAndLambda();
        demoThreadMethodReferenceThread();
    }

    /**
     * Thread.ofPlatform() is a new API introduced, in Java 21, that allows developers to create platform threads with a more modern and flexible approach.
     * The api follows a builder pattern, allowing developers to set various properties of the thread before starting it.
     */
    private static void demoThreadOfPlatform() {
        Thread.ofPlatform()// a builder for creating platform threads
                .name("ModernThread-", 1) // Thread Builder API allows naming threads with a prefix and a unique number.
                .daemon(false) // sets the thread as a non-daemon thread, meaning it will prevent the JVM from exiting until it completes.
                .priority(Thread.NORM_PRIORITY) // sets the thread's priority to normal, which is the default priority level for threads.
                .unstarted(() -> { // takes a Runnable and returns a Thread that is not yet started.
                    System.out.println("Modern thread started: " + Thread.currentThread().getName()); // thread name is 'ModernThread1' for the first thread.
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Modern thread interrupted: " + Thread.currentThread().getName());
                    }
                    System.out.println("Modern thread ended: " + Thread.currentThread().getName());
                })
                .start(); // starts the thread, which will execute the provided Runnable.
    }

    /**
     * Demonstrates creating a thread using a lambda expression.
     * This approach is more concise and leverages Java's functional programming capabilities.
     */
    private static void demoLambdaThread() {
        Thread thread = new Thread(() -> {
            System.out.println("Lambda thread started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Lambda thread interrupted: " + Thread.currentThread().getName());
            }
            System.out.println("Lambda thread ended: " + Thread.currentThread().getName());
        });
        thread.setName("LambdaThread");
        thread.setDaemon(false);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private static void demoThreadOfPlatformAndLambda() {
        Thread.ofPlatform()
                .name("ModernLambdaThread-", 1)
                .daemon(false)
                .priority(Thread.NORM_PRIORITY)
                .start(() -> {
                    System.out.println("Modern Lambda thread started: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Modern Lambda thread interrupted: " + Thread.currentThread().getName());
                    }
                    System.out.println("Modern Lambda thread ended: " + Thread.currentThread().getName());
                });
    }

    /**
     * Demonstrates creating a thread using a method reference.
     * <p>
     * Note:
     * <li>Thread.Builder.start(...) accepts a Runnable, and a method reference whose target method
     * takes no arguments and returns void can serve as that Runnable.</li>
     * <li>when the method reference is used where Java expects a functional interface, Java checks
     * whether the referenced method is compatible with that interface’s single abstract method.
     * You don’t have to declare that the method implements the interface.</li>
     */
    private static void demoThreadMethodReferenceThread() {
        Thread.ofPlatform()
                .name("ModernMethodReferenceThread-", 1)
                .start(ModernThread::doWork);
    }

    /**
     * This method is referenced in the demoThreadMethodReferenceThread() method.
     * It demonstrates the use of method references in Java, which provide a more concise way to refer to methods without executing them.
     */
    private static void doWork() {
        System.out.println("Method reference thread started: " + Thread.currentThread().getName());
    }
}
