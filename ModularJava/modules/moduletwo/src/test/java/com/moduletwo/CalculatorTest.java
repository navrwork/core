package com.moduletwo;

import com.moduletwo.api.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class.
 */
class CalculatorTest {

    @Test
    void testMultiply() {
        assertEquals(6, Calculator.multiply(2, 3));
        assertEquals(0, Calculator.multiply(5, 0));
        assertEquals(-12, Calculator.multiply(-3, 4));
    }

    @Test
    void testSubtract() {
        assertEquals(1, Calculator.subtract(5, 4));
        assertEquals(-1, Calculator.subtract(4, 5));
        assertEquals(0, Calculator.subtract(10, 10));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, Calculator.divide(10, 5));
        assertEquals(0.5, Calculator.divide(1, 2));
        assertThrows(ArithmeticException.class, () -> Calculator.divide(10, 0));
    }

    @Test
    void testFactorial() {
        assertEquals(1, Calculator.factorial(0));
        assertEquals(1, Calculator.factorial(1));
        assertEquals(120, Calculator.factorial(5));
        assertEquals(3628800, Calculator.factorial(10));
        assertThrows(IllegalArgumentException.class, () -> Calculator.factorial(-1));
    }
}
