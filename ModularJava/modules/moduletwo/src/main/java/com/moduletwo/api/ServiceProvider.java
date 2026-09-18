package com.moduletwo.api;

/**
 * ServiceProvider exposes the public API for moduletwo.
 * This class demonstrates a typical service provider pattern.
 */
public class ServiceProvider {

    /**
     * Returns the version of moduletwo.
     *
     * @return version string
     */
    public static String getVersion() {
        return "1.0-SNAPSHOT";
    }

    /**
     * Provides a greeting message.
     *
     * @param name the name to greet
     * @return a greeting message
     */
    public static String greet(String name) {
        return "Hello from moduletwo, " + name + "!";
    }

    /**
     * Performs a simple calculation.
     *
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Gets module information.
     *
     * @return module description
     */
    public static String getModuleInfo() {
        return "Module: com.moduletwo | Version: " + getVersion();
    }
}
