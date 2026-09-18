package com.navr.core.concepts.fi;

/**
 * Demonstrates {@link Runnable} as a functional-interface method argument.
 * <p>
 * The {@code runTask} method owns the common workflow: it creates and starts
 * a thread, then waits for that thread to finish. The caller supplies the
 * task-specific behavior through the {@code Runnable} argument. Because
 * {@code Runnable} has one abstract method, {@code run()}, the behavior can
 * be provided with a lambda expression.
 */
public class RunnableArgumentDemo {

    public static void main(String[] args) throws InterruptedException {
        runTask("email task", () -> System.out.println(
                "Sending email on " + Thread.currentThread().getName()));

        runTask("report task", () -> {
            System.out.println(
                    "Generating report on " + Thread.currentThread().getName());
        });
    }

    /**
     * Runs caller-supplied behavior on a separate thread.
     * <p>
     * The method does not know whether the task sends an email, generates a
     * report, or performs another operation. It only knows that it can invoke
     * {@link Runnable#run()}.
     *
     * @param taskName name used for the worker thread
     * @param task     caller-supplied behavior to execute
     * @throws InterruptedException if the main thread is interrupted while
     *                              waiting for the worker thread
     */
    private static void runTask(String taskName, Runnable task)
            throws InterruptedException {
        Thread worker = new Thread(task, taskName);
        worker.start();
        worker.join();
    }
}
