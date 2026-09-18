/**
 * com.moduleone - Core module providing service interfaces and utilities
 * 
 * JPMS Features Demonstrated:
 * 1. exports - Only exports public API (com.moduleone.api)
 * 2. opens - Allows reflection/testing on internal packages
 * 3. uses - Declares dependency on TextTransformService implementation
 * 4. requires transitive - Re-exports java.logging to dependent modules
 */
module com.moduleone {
    // Re-export transitive dependencies
    requires transitive java.logging;

    // Export only the public API - implementation details are hidden
    exports com.moduleone.api;

    // Allow reflection on internal packages for testing purposes
    opens com.moduleone.internal to com.moduleapp;

    // Declare usage of service implementations
    uses com.moduleone.api.TextTransformService;
}