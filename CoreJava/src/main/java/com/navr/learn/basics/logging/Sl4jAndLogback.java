package com.navr.learn.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sl4jAndLogback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Sl4jAndLogback.class);
        logger.info(String.format(
                "Inside LogbackTest class, threadName=%s", Thread.currentThread().getName())
        );
    }
}
