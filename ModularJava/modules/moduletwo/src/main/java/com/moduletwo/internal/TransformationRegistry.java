package com.moduletwo.internal;

/**
 * TransformationRegistry manages transformation services.
 * This class is NOT exported - demonstrates strong encapsulation.
 * Only accessible within moduletwo.
 */
public class TransformationRegistry {

    private static final String[] AVAILABLE_PROVIDERS = {
        "com.moduletwo.api.UpperCaseTransformer",
        "com.moduletwo.api.LowerCaseTransformer"
    };

    private TransformationRegistry() {
        // Utility class
    }

    /**
     * Gets all registered transformation providers.
     *
     * @return array of provider class names
     */
    public static String[] getAvailableProviders() {
        return AVAILABLE_PROVIDERS;
    }

    /**
     * Gets provider count.
     *
     * @return number of available providers
     */
    public static int getProviderCount() {
        return AVAILABLE_PROVIDERS.length;
    }
}
