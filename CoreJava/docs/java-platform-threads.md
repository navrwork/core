# Java Platform Threads

## What platform threads are

A platform thread is a Java thread backed by an operating-system thread. The JVM uses the operating system to schedule it, and it remains tied to that OS thread while running or blocked. Platform threads are the traditional Java threads created with `Thread` and used by most executors.

Platform threads are useful for running tasks concurrently, especially when the number of concurrent tasks is bounded. Because each platform thread consumes operating-system resources, creating an unbounded number of them can exhaust memory or other system limits.

## Practical limits and costs

- **Maximum count:** There is no portable, fixed maximum number of platform threads per Java process. The practical limit depends on the JVM, operating system, available memory and address space, process or container thread limits, and thread stack size. If the JVM cannot create another native thread, thread creation can fail with an `OutOfMemoryError` such as `unable to create native thread`.
- **Memory per thread:** Each platform thread needs an OS thread and a native stack in addition to its Java `Thread` object and other runtime bookkeeping. The default stack size is JVM- and platform-dependent; for example, some HotSpot configurations use a stack near 1 MiB, but this is not a Java guarantee and does not mean every thread immediately commits that much physical memory. The `-Xss` option can adjust the Java thread stack size, subject to JVM and platform constraints. A smaller stack can reduce address-space use, but risks `StackOverflowError` for code with deep call stacks.
- **Creation and scheduling overhead:** Creating and starting a platform thread requires JVM and operating-system work, so it costs more than scheduling a task on an already-running thread. There is no guaranteed creation or startup time: it varies with the JVM, OS, machine load, and resource pressure. Large numbers of threads also increase scheduling and context-switching overhead, and blocked threads continue to occupy their OS threads. For these reasons, applications generally bound platform-thread concurrency with an appropriately sized executor rather than creating a thread for every task without a limit.
- **Tomcat example:** With the usual default HTTP connector settings, Tomcat's request-processing pool has a `maxThreads` value of 200 and a `minSpareThreads` value of 10. It creates worker threads as needed rather than starting all 200 immediately. This is only the request-processing pool; JVM, Tomcat utility, application, and other connector threads add to the process total. Defaults can vary by Tomcat version and configuration.

## Creating and starting a thread

Pass a `Runnable` to a `Thread`, then call `start()` to execute it concurrently. Calling `run()` directly only invokes the method on the current thread.

```java
Runnable task = () -> System.out.println("Running on " + Thread.currentThread().getName());

Thread thread = new Thread(task, "worker-1");
thread.start();
thread.join(); // Wait for this thread to finish
```

`join()` can throw `InterruptedException`, so production code should handle interruption according to its cancellation policy. For example, a method that cannot handle it directly can restore the interrupt status and propagate or return:

```java
try {
    thread.join();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return;
}
```

## Prefer executors for groups of tasks

For application work, an executor usually provides better lifecycle and resource management than creating a new thread for every task. A fixed thread pool bounds the number of concurrent platform threads and queues additional work.

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
try {
    Future<?> result = executor.submit(
            () -> System.out.println("Running on " + Thread.currentThread().getName()));
    result.get(); // Wait for the task and observe any failure
} finally {
    executor.shutdown();
}
```

Choose the pool size and queueing policy for the workload. A fixed pool limits active threads, but an unbounded queue can still grow under sustained overload. Shut down executors when they are no longer needed.

## Sharing data safely

Threads may access the same objects at the same time. Without synchronization, concurrent updates can be lost, and one thread may not observe another thread's changes. Use synchronization, locks, or concurrency utilities such as atomic variables and concurrent collections to coordinate shared state.

```java
private final AtomicInteger completed = new AtomicInteger();

void markCompleted() {
    completed.incrementAndGet();
}
```

Prefer immutable data or task-local state when practical. Avoid assuming that `sleep()` or the order in which threads are started provides synchronization.

## Interruption and cancellation

Interruption is Java's cooperative cancellation mechanism. A thread can request cancellation with `interrupt()`. Blocking methods such as `sleep()` and `join()` respond by throwing `InterruptedException`; code should not silently discard that signal. Long-running tasks should periodically check `Thread.currentThread().isInterrupted()` or use interruptible operations so they can stop promptly.

## Platform threads and virtual threads

Platform threads are backed by OS threads and are a good fit for bounded concurrency, CPU-intensive work, and cases where an API or native library requires a platform thread. Virtual threads, available as a finalized feature since Java 21, are lightweight JVM-managed threads intended for large numbers of mostly blocking tasks. They do not make CPU-bound work run faster. Choose between them based on workload and resource constraints; see [Java 21 Features](../src/main/java/com/navr/core/java21/README.md) for the virtual-thread overview.

## Summary

Platform threads provide Java's traditional model for concurrent execution. Start them with `Thread` for simple cases, use executors to manage collections of tasks, and use proper synchronization and interruption handling whenever threads share state or need cancellation.
