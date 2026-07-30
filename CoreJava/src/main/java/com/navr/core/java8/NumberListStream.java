package com.navr.core.java8;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberListStream {
    public static void main(String[] args) {
        List<Integer> numList = List.of(1,3,2,4,6,8,5,7,9);
        printListLambda(numList);
//        printListMethodRef(numList);
//        printListOddNumbers(numList);
//        printAlternateNumbersV1(numList);
//        printAlternateNumbersV2(numList);
    }

    private static void printListLambda(List<Integer> numList) {
        System.out.println("printListLambda():");

        numList.forEach(x -> System.out.print(x+" ")); // lambda
        System.out.println();
        numList.forEach(System.out::print); // method reference
        numList.forEach(x -> { //
            System.out.print(x+" ");
        });
        System.out.println("\n");
    }

    private static void printNumber(Integer num) {
        System.out.print(num+" ");
    }

    private static void printListMethodRef(List<Integer> numList) {
        System.out.println("printListMethodRef():");
        numList.stream().forEach(System.out::print);
        System.out.println(" ");
        numList.stream().forEach(NumberListStream::printNumber);
    }

    private static void printListOddNumbers(List<Integer> numList) {
        System.out.println("printListOddNumbers():");
        numList.stream().filter(x -> x%2 !=0).forEach(x -> System.out.printf(x+" "));
        System.out.println("\n");
    }


    private static void printAlternateNumbersV1(List<Integer> numList) {
        System.out.println("printAlternateNumbersV1: Using 'int[] counter'");
        int[] counter = new int[1];
        numList.stream().forEach(x -> {
            if (counter[0] % 2 == 0) {
                System.out.printf(x + " ");
            }
            counter[0]++;
        });
        System.out.println(" ");
    }

    private static void printAlternateNumbersV2(List<Integer> numList) {
        System.out.println("printAlternateNumbersV2: Using 'AtomicInteger'");
        AtomicInteger counter = new AtomicInteger(0);
        numList.stream().forEach(x -> {
            if (counter.get() % 2 == 0) {
                System.out.printf(x + " ");
            }
            counter.incrementAndGet();
        });
        System.out.println(" ");
    }
}
