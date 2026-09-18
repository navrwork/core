package com.moduleone;

import com.moduleone.api.CoreService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CoreService class.
 */
class CoreServiceTest {

    @Test
    void testGetVersion() {
        String version = CoreService.getVersion();
        assertNotNull(version);
        assertTrue(version.contains("SNAPSHOT"));
    }

    @Test
    void testGetModuleName() {
        assertEquals("moduleone", CoreService.getModuleName());
    }

    @Test
    void testInitialize() {
        String result = CoreService.initialize();
        assertNotNull(result);
        assertTrue(result.contains("moduleone"));
        assertTrue(result.contains("initialized"));
    }

    @Test
    void testGetModuleInfo() {
        String info = CoreService.getModuleInfo();
        assertNotNull(info);
        assertTrue(info.contains("com.moduleone"));
        assertTrue(info.contains("Version"));
        assertTrue(info.contains("Active"));
    }

    @Test
    void testHealthCheck() {
        assertTrue(CoreService.healthCheck());
    }

    @Test
    void testGetDescription() {
        String description = CoreService.getDescription();
        assertNotNull(description);
        assertTrue(description.contains("Data Processing"));
    }
}
