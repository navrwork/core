package com.navr.core.java5;

public class EnhancedForLoop {
    public static void main(String[] args) {
        enhancedForLoop1();
    }

    private static void enhancedForLoop1() {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        for (int number : numbers) {
            System.out.printf("number=%d ", number);
        }
    }
}
