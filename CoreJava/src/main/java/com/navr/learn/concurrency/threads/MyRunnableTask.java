package com.navr.learn.concurrency.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnableTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(MyRunnableTask.class);

    @Override
    public void run() {
        logger.info(String.format(
                "Inside run() of MyRunnableTask class, threadName=%s", Thread.currentThread().getName())
        );
    }
}
