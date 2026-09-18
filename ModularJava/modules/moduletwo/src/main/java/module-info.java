/**
 * com.moduletwo - Service implementation module
 * 
 * JPMS Features Demonstrated:
 * 1. requires - Depends on moduleone for service interface
 * 2. exports - Exports service implementations
 * 3. provides - Implements TextTransformService (service provider pattern)
 * 4. opens - Allows reflection on internal packages for testing
 * 5. qualified exports - Restricts visibility to specific consumers
 */
module com.moduletwo {
    // Require transitive from moduleone (re-exports java.logging)
    requires transitive com.moduleone;

    // Export service implementations
    exports com.moduletwo.api;

    // Allow reflection on internal packages (qualified export concept)
    opens com.moduletwo.internal to com.moduleapp;

    // Provide implementations of TextTransformService (Service Provider Interface)
    provides com.moduleone.api.TextTransformService
        with com.moduletwo.api.UpperCaseTransformer,
             com.moduletwo.api.LowerCaseTransformer;
}
