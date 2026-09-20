package com.navr.core.concepts.concurrency.threads;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyRunnableTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(MyRunnableTask.class);

    /**
     * The run method is executed when the thread is started.
     * It logs the thread name and simulates work by sleeping for 10 seconds.
     */
    @Override
    public void run() {
        logger.info(String.format(
                "Inside run() of MyRunnableTask class, threadName=%s", Thread.currentThread().getName())
        );
        try {
            TimeUnit.SECONDS.sleep(10); // Sleep for 10 seconds to simulate work
        } catch (InterruptedException e) {
            System.out.printf("MyRunnableTask: Thread interrupted. threadName=%s%n", Thread.currentThread().getName());
            Thread.currentThread().interrupt(); // Restore the interrupted status to allow higher-level interrupt handling
            return;
        }
    }
}
