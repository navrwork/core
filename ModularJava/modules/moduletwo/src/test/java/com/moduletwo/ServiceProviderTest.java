package com.moduletwo;

import com.moduletwo.api.ServiceProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ServiceProvider class.
 */
class ServiceProviderTest {

    @Test
    void testGetVersion() {
        String version = ServiceProvider.getVersion();
        assertNotNull(version);
        assertTrue(version.contains("SNAPSHOT"));
    }

    @Test
    void testGreet() {
        String greeting = ServiceProvider.greet("World");
        assertEquals("Hello from moduletwo, World!", greeting);
    }

    @Test
    void testAdd() {
        assertEquals(5, ServiceProvider.add(2, 3));
        assertEquals(0, ServiceProvider.add(5, -5));
        assertEquals(-2, ServiceProvider.add(-5, 3));
    }

    @Test
    void testGetModuleInfo() {
        String info = ServiceProvider.getModuleInfo();
        assertNotNull(info);
        assertTrue(info.contains("com.moduletwo"));
        assertTrue(info.contains("Version"));
    }
}
