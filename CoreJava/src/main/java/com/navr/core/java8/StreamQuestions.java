package com.navr.core.java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamQuestions {
    public static void main(String[] args) {
        testStreams();
//        dummy();
    }

    private static void dummy() {
        int num = 900;
        int numDigits = (int) Math.log10(num) + 1;
        int firstDigit = (int) (num / Math.pow(10, numDigits - 1));
        System.out.printf("num=%d, numDigits=%d, firstDigit=%d%n", num, numDigits, firstDigit);
    }

    private static void testStreams() {
//        filterAllEvenNumbersInList();
//        findAllNumbersStartingWith();
//        findDuplicateNumbersInList();
//        findFirstNumberInList();
//findNumberOfElementsInList();
        findMaxElement();


    }


    /**
     * Given a list of integers, find out all the even numbers
     * that exist in the list using Stream functions.
     */
    private static void filterAllEvenNumbersInList() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumList = intList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.printf("filterAllEvenNumbersInList: evenNumList=%s%n", evenNumList);
    }

    /**
     * Given a list of integers, find out all the numbers starting with 1 using Stream functions.
     */
    private static void findAllNumbersStartingWith() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 111);
        List<Integer> numListStartingWith = intList.stream().filter(
                n -> {
                    int firstDigit = getFirstDigit(n);
                    return firstDigit == 1;
                }
        ).collect(Collectors.toList());
        System.out.printf("findAllNumbersStartingWith: numListStartingWith=%s%n", numListStartingWith);
    }

    /**
     * find duplicate elements in a given integers list in java using Stream functions
     */
    private static void findDuplicateNumbersInList() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3);
        Set<Integer> set = new HashSet<>();
        List<Integer> dupIntList = intList.stream()
                .filter(n -> !set.add(n))
                .collect(Collectors.toList());
        System.out.printf("findDuplicateNumbersInList: dupIntList=%s%n", dupIntList);
    }

    /**
     * Given the list of integers, find the first element of the list using Stream functions.
     */
    private static void findFirstNumberInList() {
        List<Integer> intList = Arrays.asList(100, 200, 300, 999);
        int firstNum = intList.stream().findFirst().orElse(-1);
        System.out.printf("findFirstNumberInList: firstNum=%d%n", firstNum);
    }


    /**
     * Given a list of integers, find the total number of elements present in the list using Stream functions
     */
    private static void findNumberOfElementsInList() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        long elementCount = intList.stream().count();
        System.out.printf("findNumberOfElementsInList: elementCount=%d%n", elementCount);
    }

    private static void findMaxElement() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 10, 20, 22, 15);
        int maxElement = intList.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }).orElse(-1);

        // V2
        int maxElementV2 = intList.stream().max(Integer::compareTo).get();

        System.out.printf("findMaxElement: maxElement=%d, maxElementV2=%d%n", maxElement, maxElementV2);
    }

    private static int getFirstDigit(int num) {
        int numDigits = (int) Math.log10(num) + 1;
        return (int) (num / Math.pow(10, numDigits - 1));
    }


}
