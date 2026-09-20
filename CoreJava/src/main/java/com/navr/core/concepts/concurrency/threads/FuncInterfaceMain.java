package com.navr.core.concepts.concurrency.threads;

/**
 * Demonstrates passing lambda expressions as implementations of a functional
 * interface method.
 * <p>
 * {@link Runnable} is a functional interface because it has one abstract method,
 * {@link Runnable#run()}. A lambda expression supplies the implementation of
 * that method and can be passed as an argument to another method.
 */
public class FuncInterfaceMain {

    /**
     * Starts a runnable task and demonstrates passing different lambda
     * implementations of {@link Runnable#run()} as functional interface
     * arguments.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        FuncInterfaceMain main = new FuncInterfaceMain();
        main.invokeRunnable();
    }

    /**
     * Supplies lambda expressions that implement {@link Runnable#run()} and
     * passes each resulting {@link Runnable} argument to
     * {@link #executeRunnableBehavior(Runnable)}.
     */
    private void invokeRunnable() {
        runnableToPrintHelloWorld();
        runnableToPrintEvenNumbers();
        runnableToPrintOddNumbers();
    }

    /**
     * Passes a lambda implementation of {@link Runnable#run()} to the executor
     * to print a greeting.
     */
    private void runnableToPrintHelloWorld() {
        executeRunnableBehavior(
                () -> {
                    System.out.printf("runnableToPrintHelloWorld: Runnable started: %s%n", Thread.currentThread().getName());
                    System.out.println("runnableToPrintHelloWorld: Hello, World!");
                    System.out.printf("runnableToPrintHelloWorld: Runnable ended: %s%n", Thread.currentThread().getName());
                });
    }

    /**
     * Passes a lambda implementation of {@link Runnable#run()} to the executor
     * to print even numbers.
     */
    private void runnableToPrintEvenNumbers() {
        executeRunnableBehavior(
                () -> {
                    System.out.printf("runnableToPrintEvenNumbers: Runnable started: %s%n", Thread.currentThread().getName());
                    for (int i = 1; i <= 10; i++) {
                        if (i % 2 == 0) {
                            System.out.println("Even number: " + i);
                        }
                    }
                    System.out.printf("runnableToPrintEvenNumbers: Runnable ended: %s%n", Thread.currentThread().getName());
                });
    }

    /**
     * Passes a lambda implementation of {@link Runnable#run()} to the executor
     * to print odd numbers.
     */
    private void runnableToPrintOddNumbers() {
        executeRunnableBehavior(
                () -> {
                    System.out.printf("runnableToPrintOddNumbers: Runnable started: %s%n", Thread.currentThread().getName());
                    for (int i = 1; i <= 10; i++) {
                        if (i % 2 != 0) {
                            System.out.println("runnableToPrintOddNumbers: Odd number: " + i);
                        }
                    }
                    System.out.printf("runnableToPrintOddNumbers: Runnable ended: %s%n", Thread.currentThread().getName());
                });
    }

    /**
     * Executes the {@link Runnable#run()} implementation supplied through a
     * functional interface argument.
     * <p>
     * Passing the implementation as an argument allows callers to provide
     * different actions without changing this method.
     *
     * @param runnable the functional interface argument whose
     *                 {@link Runnable#run()} implementation is executed
     */
    private void executeRunnableBehavior(Runnable runnable) {
        System.out.printf("executeRunnableBehavior: Executing Runnable behavior on thread: %s%n", Thread.currentThread().getName());
        runnable.run();
        System.out.printf("executeRunnableBehavior: Finished executing Runnable behavior on thread: %s%n%n", Thread.currentThread().getName());
    }
}
