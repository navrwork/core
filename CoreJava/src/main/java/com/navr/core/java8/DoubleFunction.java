package com.navr.core.java8;

@FunctionalInterface
public interface DoubleFunction {
    public abstract long findDouble(int number);

    public static void printDouble(int number) {
        System.out.printf("double of %d is %d %n", number, 2*number);
    }
}
