# Java Virtual Threads

## What virtual threads are

Virtual threads are lightweight Java threads managed and scheduled by the JVM rather than being permanently backed by individual operating-system threads. They became a permanent feature in Java 21 (JEP 444).

When a virtual thread runs, it is mounted on a platform thread called a *carrier*. When it blocks on supported operations such as many forms of blocking I/O, the JVM can park the virtual thread and use the carrier to run another virtual thread. This makes virtual threads useful for applications with many concurrent tasks that spend much of their time waiting.

## Virtual threads and platform threads

| Aspect | Platform threads | Virtual threads |
| --- | --- | --- |
| OS-thread relationship | Each platform thread is backed by an OS thread. | Many virtual threads share a smaller set of carrier platform threads. |
| Scheduling | Primarily scheduled by the operating system. | Scheduled by the JVM onto carrier threads. |
| Blocking | A blocked thread continues to occupy its OS thread. | Many blocking operations let the JVM unmount a virtual thread and free its carrier. |
| Best suited for | Bounded concurrency, CPU-intensive work, and APIs requiring platform threads. | Large numbers of mostly blocking tasks, such as concurrent request handling. |
| Resource limits | OS-thread resources and native stack reservations limit practical concurrency. | Much lower per-thread overhead enables high concurrency, but heap, OS, and application resources still impose limits. |

Virtual threads are not faster threads. They make it practical to have more concurrent tasks, especially when those tasks wait for I/O. They do not increase the number of CPU cores, so CPU-bound work generally does not run faster by using more virtual threads.

## Creating a virtual thread

Use the thread builder API to start one virtual thread:

```java
Thread thread = Thread.ofVirtual()
        .name("request-handler")
        .start(() -> handleRequest());

thread.join();
```

For many independent tasks, use an executor that creates a new virtual thread per task:

```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 1_000; i++) {
        int taskId = i;
        executor.submit(() -> process(taskId));
    }
}
```

The executor is closed at the end of the `try` block and waits for submitted tasks to complete. This executor does not impose a concurrency limit. If the application or a downstream service needs one, enforce that limit explicitly, for example with a semaphore or a bounded resource pool.

## Features and behavior

- **High concurrency:** Virtual threads are cheap enough to create in large numbers, commonly one per task or request, instead of reusing a small worker pool solely to limit thread count.
- **Thread-per-task programming:** Code can use straightforward blocking control flow rather than being rewritten around callbacks or asynchronous pipelines just to support many waiting tasks.
- **Familiar `Thread` API:** Virtual threads support normal thread operations such as `join()` and interruption, and work with much of the existing Java concurrency API.
- **JVM-managed scheduling:** The JVM schedules virtual threads over carrier platform threads using a work-stealing scheduler implemented with `ForkJoinPool`. Its parallelism is typically based on the number of available processors; it is not a limit on how many virtual threads can exist. This is an implementation detail, not a requirement that application code use a `ForkJoinPool` to create virtual threads.
- **Daemon threads:** Virtual threads are always daemon threads and do not keep the JVM alive on their own. The application must wait for required work, for example by joining threads or closing an executor.
- **No pooling needed:** Virtual threads are intended to be created per task, not pooled for reuse. Use a semaphore or other explicit concurrency control to protect constrained resources rather than limiting the virtual-thread count with a pool.

## Blocking, pinning, and limitations

Most JDK blocking operations can suspend a virtual thread without tying up its carrier. In Java 21, a virtual thread can be *pinned* to its carrier while executing inside a `synchronized` block or method, or while executing certain native or foreign-function calls. Blocking while pinned can reduce scalability because the carrier cannot run another virtual thread. Keep such critical sections short and avoid blocking operations inside them when targeting Java 21.

The virtual-thread scheduler is separate from application `ForkJoinPool` instances. Application code normally creates virtual threads with `Thread.ofVirtual()` or `Executors.newVirtualThreadPerTaskExecutor()`; it should not depend on or try to manage the JVM's internal scheduler directly.

Virtual threads do not remove the need to manage resources. Large numbers of concurrent tasks can still exhaust heap memory, file descriptors, database connections, remote-service capacity, or other limits. Apply backpressure or resource-specific limits where needed. For CPU-intensive workloads, use an appropriately sized platform-thread executor rather than creating huge numbers of competing tasks.

## When to use them

Virtual threads are a good fit when there are many concurrent tasks that spend significant time waiting, such as request handlers making blocking database or network calls. Platform threads remain appropriate for bounded worker pools, CPU-bound work, and integrations that require a thread backed by an OS thread. See [Java Platform Threads](java-platform-threads.md) for more on platform-thread characteristics.

## FAQ

### Should virtual threads be pooled?

No. Although the JVM reuses carrier platform threads, virtual threads are intended to be created per task rather than pooled. Virtual threads are lightweight, and pooling them does not avoid OS-thread creation; the JVM manages and reuses the carriers.

Use `Executors.newVirtualThreadPerTaskExecutor()` to submit tasks, and close the executor when the work is complete. This executor does not limit concurrency. If a constrained resource such as a database connection pool needs protection, apply a limit to access to that resource rather than pooling virtual threads.

### Are virtual threads daemon threads by default? Why?

Yes. Virtual threads are always daemon threads; their daemon status cannot be changed. Daemon threads do not keep the JVM running, so the JVM can exit when only daemon threads remain—even if virtual-thread tasks are still active. This suits lightweight, task-oriented threads, but means the application must explicitly wait for important work to finish, for example by joining the threads or closing their executor. The key practical reason for this behavior is their lifecycle: virtual threads should not implicitly determine when the JVM shuts down.

### Do virtual threads make tasks run faster?

Not necessarily. Virtual threads can improve scalability and throughput when tasks spend much of their time waiting, because a blocked virtual thread can free its carrier for other work. They do not add CPU cores, so they generally do not speed up CPU-bound tasks.

### Are virtual threads unlimited?

No. Virtual threads have lower per-thread overhead than platform threads, but the application is still constrained by available memory, file descriptors, database connections, remote-service capacity, and other resources. Apply backpressure or explicit limits where those resources are constrained.

### Can virtual threads be interrupted?

Yes. Virtual threads support interruption, like platform threads. Use interruption for cooperative cancellation and ensure tasks respond to it, especially when they are blocked in interruptible operations.
