package com.moduleone.internal;

/**
 * StringUtils provides internal utility functions for string manipulation.
 * This class is NOT exported and should only be used within moduleone.
 * Demonstrates strong encapsulation - not visible to other modules.
 */
public class StringUtils {

    private StringUtils() {
        // Utility class - private constructor
    }

    /**
     * Validates if a string is not null or empty.
     *
     * @param text the text to validate
     * @return true if valid
     */
    public static boolean isValid(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Counts characters in a string.
     *
     * @param text the input string
     * @return character count
     */
    public static int countChars(String text) {
        return isValid(text) ? text.length() : 0;
    }

    /**
     * Checks if string is numeric.
     *
     * @param text the text to check
     * @return true if numeric
     */
    public static boolean isNumeric(String text) {
        if (!isValid(text)) {
            return false;
        }
        return text.matches("\\d+");
    }
}
