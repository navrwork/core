package com.navr.core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {
        intStreamExample();
        intStreamWithSkip();
        intStreamArithmetic();
        mapToInt();

    }

    private static void intStreamExample() {
        System.out.println("\r\nintStreamExample ... range=1..6");
        IntStream
                .range(1, 6)
                .forEach(System.out::println);
    }

    private static void intStreamWithSkip() {
        System.out.println("\r\nintStreamWithSkip ... range=1..10, skip=4");
        IntStream
                .range(1, 10)
                .skip(4)
                .forEach(i -> System.out.println(i));
    }

    private static void intStreamArithmetic() {
        System.out.println("\r\nintStreamArithmetic ... range=1..10");
        int sum = IntStream
                .range(1, 10)
                .sum();

        double avg = IntStream
                .range(1, 10)
                .average()
                .orElse(-1);

        System.out.printf("sum=%d, avg=%f%n", sum, avg);

    }

    private static void mapToInt() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        long numListSum = numList.stream().mapToInt(Integer::intValue).sum();

        long numListCount = numList.stream().mapToInt(Integer::intValue).count();

        long numListMax = numList.stream().mapToInt(Integer::intValue).max().orElse(0);

        double numListAverage = numList.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        System.out.printf("\r\nmapToInt: numListSum=%d, numListCount=%d, numListMax=%d, numListAverage=%f%n",
                numListSum, numListCount, numListMax, numListAverage);

//        long allSum2 = numList.stream().

        Integer sumNum = 0;
        long evenSum = numList.stream().filter((n) -> n % 2 == 0).mapToInt(Integer::intValue).sum();

        long oddSum = numList.stream().filter(n -> n % 2 != 0).mapToInt(Integer::intValue).sum();

        System.out.printf("%nevenSum=%d, oddSum=%d", evenSum, oddSum);
    }
}
