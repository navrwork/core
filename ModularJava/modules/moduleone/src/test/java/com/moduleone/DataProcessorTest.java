package com.moduleone;

import com.moduleone.api.DataProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DataProcessor class.
 */
class DataProcessorTest {

    @Test
    void testReverseString() {
        assertEquals("olleh", DataProcessor.reverseString("hello"));
        assertEquals("a", DataProcessor.reverseString("a"));
        assertEquals("", DataProcessor.reverseString(""));
        assertNull(DataProcessor.reverseString(null));
    }

    @Test
    void testCountVowels() {
        assertEquals(2, DataProcessor.countVowels("hello"));
        assertEquals(5, DataProcessor.countVowels("aeiou"));
        assertEquals(0, DataProcessor.countVowels("bcdfg"));
        assertEquals(0, DataProcessor.countVowels(null));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(DataProcessor.isPalindrome("racecar"));
        assertTrue(DataProcessor.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(DataProcessor.isPalindrome("hello"));
        assertFalse(DataProcessor.isPalindrome(null));
    }

    @Test
    void testToUpperCase() {
        assertEquals("HELLO", DataProcessor.toUpperCase("hello"));
        assertEquals("HELLO WORLD", DataProcessor.toUpperCase("hello world"));
        assertNull(DataProcessor.toUpperCase(null));
    }

    @Test
    void testToLowerCase() {
        assertEquals("hello", DataProcessor.toLowerCase("HELLO"));
        assertEquals("hello world", DataProcessor.toLowerCase("HELLO WORLD"));
        assertNull(DataProcessor.toLowerCase(null));
    }
}
