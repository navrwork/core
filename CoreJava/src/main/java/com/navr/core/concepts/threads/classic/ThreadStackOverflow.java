package com.navr.core.concepts.threads.classic;

/**
 * This class demonstrates a stack overflow error caused by excessive recursion.
 * It recursively calls a method without a base case, leading to a stack overflow.
 */
public class ThreadStackOverflow {
    private static long  counter = 0;
    public static void main(String[] args) {
        try {
            recursiveMethod(0);
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow error occurred!");
        }
    }

    public static void recursiveMethod(long l) {
        counter++; // Increment the counter to keep track of the number of recursive calls
        System.out.printf("Recursive call count: %d%n", counter);

        // This method calls itself recursively without a base case,
        // leading to a stack overflow error.
        recursiveMethod(l);
    }
}
