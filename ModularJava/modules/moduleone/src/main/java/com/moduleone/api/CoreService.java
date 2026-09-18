package com.moduleone.api;

/**
 * CoreService exposes the core functionality of moduleone.
 * This class demonstrates a service-oriented API design.
 */
public class CoreService {

    /**
     * Returns the version of moduleone.
     *
     * @return version string
     */
    public static String getVersion() {
        return "1.0-SNAPSHOT";
    }

    /**
     * Returns module name.
     *
     * @return the module name
     */
    public static String getModuleName() {
        return "moduleone";
    }

    /**
     * Initializes the service.
     *
     * @return initialization status message
     */
    public static String initialize() {
        return "Service initialized: " + getModuleName() + " v" + getVersion();
    }

    /**
     * Gets detailed module information.
     *
     * @return module information string
     */
    public static String getModuleInfo() {
        return "Module: com.moduleone | Version: " + getVersion() + " | Status: Active";
    }

    /**
     * Performs a health check.
     *
     * @return true if healthy, false otherwise
     */
    public static boolean healthCheck() {
        return true;
    }

    /**
     * Returns the module description.
     *
     * @return description
     */
    public static String getDescription() {
        return "Data Processing Module for ModularJava";
    }
}
