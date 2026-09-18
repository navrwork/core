package com.moduleapp.main;

import com.moduleone.api.TextTransformService;
import java.util.ServiceLoader;
import java.util.Comparator;

/**
 * Application demonstrates JPMS service discovery using ServiceLoader.
 * This showcases the service provider interface pattern.
 */
public class Application {

    /**
     * Main entry point demonstrating service discovery.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== JPMS Service Discovery Demo ===\n");

        String testText = "Hello World";
        System.out.println("Input text: \"" + testText + "\"\n");

        // Discover and load all implementations of TextTransformService
        ServiceLoader<TextTransformService> loader = 
            ServiceLoader.load(TextTransformService.class);

        // Get all providers and sort by priority
        loader.stream()
            .map(ServiceLoader.Provider::get)
            .sorted(Comparator.comparingInt(TextTransformService::getPriority).reversed())
            .forEach(service -> {
                System.out.println("Found Service: " + service.getProviderName());
                System.out.println("  Priority: " + service.getPriority());
                System.out.println("  Transformed: \"" + service.transform(testText) + "\"");
                System.out.println();
            });
    }

    /**
     * Gets application version.
     *
     * @return version string
     */
    public static String getVersion() {
        return "1.0-SNAPSHOT";
    }
}
