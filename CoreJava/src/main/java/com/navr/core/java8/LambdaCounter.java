package com.navr.core.java8;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LambdaCounter {
    public static void main(String[] args) {
        lambdaCounterUsingArray();
        lambdaCounterAtomicInteger();
    }

    private static void lambdaCounterUsingArray() {
        List<String> strList = List.of("a", "b", "c");
        int[] counterArr = {0};

        strList.stream().forEach( x -> counterArr[0]++);
        System.out.printf("counterArr=%d%n", counterArr[0]);
    }

    private static void lambdaCounterAtomicInteger() {
        List<String> strList = List.of("a", "b", "c");
        AtomicInteger atomicIntegerCounter = new AtomicInteger(0);
        strList.stream().forEach(x -> atomicIntegerCounter.getAndIncrement());
        System.out.printf("atomicIntegerCounter=%d%n", atomicIntegerCounter.get());
    }
}
