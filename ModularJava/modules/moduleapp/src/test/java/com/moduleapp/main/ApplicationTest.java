package com.moduleapp.main;

import com.moduleone.api.TextTransformService;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Application demonstrating service discovery.
 */
class ApplicationTest {

    @Test
    void testServiceDiscovery() {
        ServiceLoader<TextTransformService> loader = 
            ServiceLoader.load(TextTransformService.class);
        
        List<TextTransformService> services = loader.stream()
            .map(ServiceLoader.Provider::get)
            .toList();
        
        assertNotNull(services);
        assertTrue(services.size() > 0, "Should discover at least one service");
    }

    @Test
    void testServiceImplementations() {
        ServiceLoader<TextTransformService> loader = 
            ServiceLoader.load(TextTransformService.class);
        
        loader.stream()
            .map(ServiceLoader.Provider::get)
            .forEach(service -> {
                assertNotNull(service.getProviderName());
                assertNotNull(service.transform("test"));
                assertTrue(service.getPriority() >= 0);
            });
    }

    @Test
    void testApplicationVersion() {
        String version = Application.getVersion();
        assertNotNull(version);
        assertTrue(version.contains("SNAPSHOT"));
    }
}
