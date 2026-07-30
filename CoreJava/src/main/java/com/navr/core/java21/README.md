# Java 21 Features

## Virtual Threads
Virtual threads in Java 21, part of Project Loom (JEP 444), are lightweight, JVM-managed threads that differ fundamentally from traditional platform threads, which map 1:1 to OS threads.
They excel in blocking operations (e.g., sleep, JDBC queries, HTTP calls) by "parking" without tying up carriers, enabling millions of concurrent tasks without exhaustion.

### Traditional Threads vs Virtual Threads
|               Feature | Traditional Threads                                                                                                                                                                                                                                                                     | Virtual Threads                                                                                                                                                                                                          | 
|----------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Mapping to OS Threads | Traditional platform threads map 1:1 to kernel (OS) threads, so each Java thread monopolizes an OS thread throughout its life, including during blocking I/O like database calls or sleeps.                                                                                             | Virtual threads map many-to-one (or many-to-few) to a pool of "carrier" OS threads; the JVM scheduler "mounts" a virtual thread onto a carrier to run, then "unmounts" it during blocks, freeing the carrier for others. |
|     Thread Management | Traditional platform threads (also called native or OS threads) in Java are primarily managed by the operating system (OS), not the JVM. The JVM requests OS threads via native system calls when you create a Thread or use Executors. However, the OS handles core operations.        | Virtual threads shift management to the JVM: it schedules them in user space on a pool of carrier OS threads, avoiding OS involvement during blocks.                                                                     |
|     Resource Overhead | Platform threads require ~1MB stack memory each (contiguous allocation) and costly creation/context switches (~microseconds).                                                                                                                                                           | Virtual threads use ~1-2KB (heap-allocated stack frames), create 10x faster, and support millions without exhaustion—ideal for high-concurrency apps.                                                                    |


### How to Create a Virtual Thread
 * Using Thread Builder: The primary way uses Thread.ofVirtual() to build and start a virtual thread with a Runnable.
 * For task submission, use Executors.newVirtualThreadPerTaskExecutor() — creates a new virtual thread per task.

### Performance Advantages
Benchmarks show virtual threads boosting throughput by 60%+ and cutting latency by 28% in I/O-bound workloads like web servers, while using 36% less memory (e.g., 1.4 GB vs. 2.2 GB for 10k requests).

In CPU-bound tasks, they match platform threads since computation bottlenecks cores, not threading.

### Practical Use Case: Scalable Web Server
Consider a server handling 10,000 simultaneous requests with database calls—traditional thread pools thrash due to limited threads (~200-500 viable). Virtual threads assign one per request seamlessly.

```text
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10_000; i++) {
        final int taskId = i;
        executor.submit(() -> {
            // Simulate blocking I/O: DB query or HTTP
            Thread.sleep(100);
            System.out.println("Processed request " + taskId + " on " + Thread.currentThread());
        });
    }
}
```