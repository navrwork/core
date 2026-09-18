/**
 * com.moduleapp - Application module consuming services
 * 
 * JPMS Features Demonstrated:
 * 1. requires - Depends on both moduleone (service interface) and moduletwo (implementation)
 * 2. uses - Declares usage of TextTransformService via ServiceLoader
 * 3. No exports - This is the final application, not a library
 */
module com.moduleapp {
    // Require the service interface module
    requires com.moduleone;
    
    // Require the service implementation module
    requires com.moduletwo;

    // Declare usage of service implementations (ServiceLoader pattern)
    uses com.moduleone.api.TextTransformService;
}
