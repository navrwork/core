package com.navr.core.java8;

public class DoubleFunctionEx {
    public static void main(String[] args) {
        testDoubleFunction();
    }

    private static void testDoubleFunction() {
        // anonymous inner class approach
        DoubleFunction doubleFunction1 = new DoubleFunction() {
            @Override
            public long findDouble(int number) {
                return (long) 2 * number;
            }
        };
        long double1 = doubleFunction1.findDouble(10);
        System.out.println("double1=" + double1);

        // lambda expression approach
        DoubleFunction doubleFunction2 = x -> (long) 2 * x;
        long double2 = doubleFunction2.findDouble(50);
        System.out.println("double1=" + double2);

    }
}
