package com.navr.core.concepts.threads.classic;

/**
 * Interruption isn’t limited to sleep() or wait(). It’s a cooperative signal:
 *
 * <li>
 * Some blocking operations respond by throwing InterruptedException, including sleep(), wait(), join(), and many methods in BlockingQueue and Lock.
 * </li><li>
 * Other interruptible APIs respond in their own way—for example, interrupting a thread blocked on an interruptible NIO channel can close the channel and cause ClosedByInterruptException.
 * </li><li>
 * Ordinary code that isn’t in an interruptible operation won’t stop automatically. It should periodically check Thread.currentThread().isInterrupted() and exit or otherwise respond.
 * </li>
 * <br/>
 * So interrupt() requests that a thread stop or change what it’s doing; it does not forcibly terminate the thread.
 * </li>
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("MainThread started. tName: " + Thread.currentThread().getName());
        ThreadInterrupt ti = new ThreadInterrupt();
        Thread taskThread = ti.startTaskThread();
        Thread.sleep(2000); // main thread: Sleep for 2 seconds before interrupting the task thread
        taskThread.interrupt(); // main thread: Interrupt the task thread. Results in InterruptedException in the task thread if it's sleeping or waiting
        taskThread.join();
        System.out.println("MainThread ENDED. tName: " + Thread.currentThread().getName());
    }

    private void interruptTaskThread(Thread taskThread) {
        System.out.println("MainThread: Before interrupting the task thread...");
        taskThread.interrupt(); // Interrupt the task thread
    }

    private Thread startTaskThread() {
        Thread taskThread = new Thread(this::task, "TaskThread");
        taskThread.start();
        return taskThread;
    }

    private void task() {
        try {
            System.out.println("taskThread: Task started by " + Thread.currentThread().getName());
            Thread.sleep(10000); // Simulate work by sleeping for 10 seconds
        } catch (InterruptedException e) {
            System.out.println("taskThread: Task interrupted. tName: " + Thread.currentThread().getName());
            e.printStackTrace();
        }
        System.out.println("taskThread: Task ENDED. tName: " + Thread.currentThread().getName());
    }
}
