package com.moduleone.api;

/**
 * TextTransformService is a service interface demonstrating the Service Provider Interface (SPI) pattern.
 * This interface defines a contract for text transformation implementations.
 * 
 * This is an exported API - available to other modules.
 */
public interface TextTransformService {

    /**
     * Gets the name of the service provider.
     *
     * @return provider name
     */
    String getProviderName();

    /**
     * Transforms the input text.
     *
     * @param text the input text
     * @return transformed text
     */
    String transform(String text);

    /**
     * Gets the priority of this service (higher priority is preferred).
     *
     * @return priority level
     */
    int getPriority();
}
