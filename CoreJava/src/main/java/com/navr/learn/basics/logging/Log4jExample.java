package com.navr.learn.logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4jExample {

    private static final Logger logger = LogManager.getLogger(Log4jExample.class);

    public static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }
}