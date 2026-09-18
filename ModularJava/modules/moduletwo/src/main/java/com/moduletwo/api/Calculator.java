package com.moduletwo.api;

/**
 * Calculator provides basic arithmetic operations.
 * This class is part of the public API of moduletwo.
 */
public class Calculator {

    /**
     * Multiplies two numbers.
     *
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Subtracts two numbers.
     *
     * @param a first number
     * @param b second number to subtract
     * @return a minus b
     */
    public static int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Divides two numbers.
     *
     * @param a dividend
     * @param b divisor
     * @return a divided by b
     * @throws ArithmeticException if b is zero
     */
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }

    /**
     * Calculates factorial of a number.
     *
     * @param n the number
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial of negative number is not defined");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
