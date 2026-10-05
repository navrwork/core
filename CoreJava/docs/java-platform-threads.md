# Java Platform Threads

## What platform threads are

A platform thread is a Java thread backed by an operating-system thread. The JVM uses the operating system to schedule it, and it remains tied to that OS thread while running or blocked. Platform threads are the traditional Java threads created with `Thread` and used by most executors.

Platform threads are useful for running tasks concurrently, especially when the number of concurrent tasks is bounded. Because each platform thread consumes operating-system resources, creating an unbounded number of them can exhaust memory or other system limits.

## Practical limits and costs

- **Maximum count:** There is no portable, fixed maximum number of platform threads per Java process. The practical limit depends on the JVM, operating system, available memory and address space, process or container thread limits, and thread stack size. If the JVM cannot create another native thread, thread creation can fail with an `OutOfMemoryError` such as `unable to create native thread`.
- **Memory per thread:** Each platform thread needs an OS thread and a native stack in addition to its Java `Thread` object and other runtime bookkeeping. See [Thread and memory usage](#thread-and-memory-usage) for the distinction between stack and heap memory.
- **Creation and scheduling overhead:** Creating and starting a platform thread requires JVM and operating-system work, so it costs more than scheduling a task on an already-running thread. There is no guaranteed creation or startup time: it varies with the JVM, OS, machine load, and resource pressure. Large numbers of threads also increase scheduling and context-switching overhead, and blocked threads continue to occupy their OS threads. For these reasons, applications generally bound platform-thread concurrency with an appropriately sized executor rather than creating a thread for every task without a limit.
- **Tomcat example:** With the usual default HTTP connector settings, Tomcat's request-processing pool has a `maxThreads` value of 200 and a `minSpareThreads` value of 10. It creates worker threads as needed rather than starting all 200 immediately. This is only the request-processing pool; JVM, Tomcat utility, application, and other connector threads add to the process total. Defaults can vary by Tomcat version and configuration.

## Thread and memory usage

A platform thread uses memory in both the native-memory area and the Java heap:

- **Stack (native memory):** Each Java thread has its own stack for method-call frames, including local variables and intermediate results. The default stack size depends on the JVM and platform; some HotSpot configurations use a value near 1 MiB, but that is not a Java guarantee. A configured stack size is not necessarily all committed as physical memory immediately. See [Configuring thread stack size (`-Xss`)](#configuring-thread-stack-size-xss).
- **Heap:** The Java `Thread` instance and related JVM bookkeeping consume heap. Objects created by a thread, including objects referenced by its local variables, are also heap objects. A reference stored in a stack frame does not move the referenced object onto the stack.
- **Additional per-thread data:** `ThreadLocal` values are heap objects associated with individual threads. They can increase heap use substantially if many threads store large values or if pooled threads retain values after tasks complete; see [ThreadLocal](#threadlocal).

Consequently, thread count can be constrained by native stack/address-space use, OS limits, and heap use—not just one memory pool. Even if the basic Java object for a thread is relatively small, millions of platform threads are generally impractical because each also needs an OS thread and stack. The exact ceiling varies by runtime, system configuration, and application.

## Stack frames

Each active method call has a frame on the executing thread's stack. A deeper call chain keeps more frames active at once: when a method calls another method, the caller's frame remains while the called method uses its own frame. The called method's frame is released when it returns. A `StackOverflowError` occurs when a thread needs more stack space than is available, often due to deep or unbounded recursion.

A frame contains the method's execution state, including:

- The call's execution state—such as local variables and temporary values. The frame does not contain a separate copy of the method's code.
- **Local-variable array:** Method parameters and local variables, including `this` for an instance method. Variables that refer to objects hold references; the objects themselves are generally allocated on the heap.
- **Operand stack:** Temporary values used while executing the method, such as values for calculations and method arguments.
- **Runtime constant-pool reference:** Information used by the JVM to resolve constants and symbolic references for the method's class.

JVM implementations may also use additional bookkeeping, and frame size varies by method and JVM. Therefore, call depth is a useful guide to stack usage, but it does not determine it precisely.

## Configuring thread stack size (`-Xss`)

The JVM's `-Xss` option sets the stack size for each Java thread, including the `main` thread.

- **Example:** `-Xss256k` requests a 256 KiB stack per thread.
- **Effect on recursion:** A higher value generally lets a recursive call go deeper before that thread throws `StackOverflowError`; it does not make recursion faster, and the actual depth depends on the method and JVM. A lower value may cause stack overflow sooner.
- **Memory trade-off:** A lower value may reduce per-thread stack reservation. Increasing the value increases per-thread stack reservation and can reduce how many platform threads the process can support. A requested stack size is not necessarily committed as physical memory all at once.
- **Minimum size:** The JVM enforces a minimum stack size. If a value is too small, startup can fail with a message such as `The Java thread stack size specified is too small. Specify at least 88k`. The reported minimum is specific to that JVM and platform; in this example, `-Xss2k` is below the supported minimum, while `-Xss88k` is the reported lower bound.
- **Platform differences:** Accepted sizes and actual behavior depend on the JVM and platform.

## ThreadLocal

`ThreadLocal<T>` provides per-thread state: each thread that uses a given `ThreadLocal` instance gets its own separate value. Even when multiple threads use the same `ThreadLocal` object, each thread sees only its own value. `ThreadLocal` is not a mechanism for sharing data between threads; it avoids sharing that particular value by giving each accessing thread its own value.

Practical uses include:

- Letting methods running on the same thread access per-thread context without passing it through every method call. For example, request handling can store a request ID that logging methods deeper in that thread's call chain can retrieve; each thread has its own value, so concurrent requests do not overwrite each other's context.
- Holding per-thread objects, such as date formatters in older code, or transaction-related context.

`ThreadLocal` is best suited to temporary thread-specific context, not general application state. When using thread pools, remove the value when the task ends so it does not remain attached to a reused worker thread.

The thread's stack is not a substitute for this kind of state. Stack variables belong to individual method-call frames and are only in scope while those calls are active; other methods cannot directly retrieve a caller's local variables, and the variables disappear when the frame returns. `ThreadLocal` makes a value available to code running on that thread across method calls, for as long as the association is kept.

The `ThreadLocal` object itself can be shared, but its values are not shared between threads. When a thread first calls `get()` or `set()`, the JVM lazily creates that thread's `ThreadLocalMap` if needed. On the first `get()`, the value is initialized by `initialValue()` (or the supplier passed to `withInitial`); `set()` stores the supplied value. The map uses a weak reference to the `ThreadLocal` key and a strong reference to the thread's value. The map entries and values occupy heap memory. If a key is garbage-collected, its value may remain until the thread-local map expunges the stale entry or the thread terminates, so weak keys alone do not guarantee prompt reclamation. A thread that never accesses a particular `ThreadLocal` does not get an entry or value for it.

With thread pools, worker threads are reused, so a value can remain associated with a worker after a task ends. Call `remove()` when the per-task value is no longer needed, especially for pooled threads, to avoid retaining data or leaking one task's context into another. For example:

```java
private static final ThreadLocal<String> requestId = new ThreadLocal<>();

void handleRequest(String id) {
    requestId.set(id);
    try {
        processRequest();
    } finally {
        requestId.remove();
    }
}
```

Use `ThreadLocal` deliberately: it can make dependencies less visible, and one value per thread can add up when there are many threads. This is particularly relevant to virtual threads, where each virtual thread that accesses a `ThreadLocal` may retain its own value.

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
