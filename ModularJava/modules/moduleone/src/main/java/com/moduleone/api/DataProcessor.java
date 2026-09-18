package com.moduleone.api;

/**
 * DataProcessor provides data processing utilities and operations.
 * This class is part of the public API of moduleone.
 */
public class DataProcessor {

    /**
     * Reverses a string.
     *
     * @param input the string to reverse
     * @return the reversed string
     */
    public static String reverseString(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Counts the number of vowels in a string.
     *
     * @param text the input string
     * @return the count of vowels
     */
    public static int countVowels(String text) {
        if (text == null) {
            return 0;
        }
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : text.toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if a string is a palindrome.
     *
     * @param text the input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }
        String cleaned = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    /**
     * Converts string to uppercase.
     *
     * @param input the input string
     * @return uppercase version
     */
    public static String toUpperCase(String input) {
        return input != null ? input.toUpperCase() : null;
    }

    /**
     * Converts string to lowercase.
     *
     * @param input the input string
     * @return lowercase version
     */
    public static String toLowerCase(String input) {
        return input != null ? input.toLowerCase() : null;
    }
}
