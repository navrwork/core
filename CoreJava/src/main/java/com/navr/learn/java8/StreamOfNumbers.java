package com.navr.learn.java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamOfNumbers {
    public static void main(String[] args) {
        findLargestNumber();
        findNthLargestNumber();
        findTopKLargestNumbers();
        filterAllEvenNumbersInList();
        findDuplicateNumbersInList();
        findFirstNumberInList();
        findNumberOfElementsInList();
        findAllNumbersStartingWith();
    }

    // Find the largest number in a list
    private static void findLargestNumber() {
        List<Integer> numList = Arrays.asList(11, 2, 31, 17, 101, 131, 203, 5);

        int largestNumberV1 = numList.stream().sorted(Comparator.reverseOrder()).findFirst().get();

        int largestNumberV2 = numList.stream().max(Comparator.naturalOrder()).get();

        int largestNumberV3 = numList.stream().min(Comparator.reverseOrder()).get();

        int largestNumberV4 = numList.stream().reduce(Integer.MIN_VALUE, Integer::max);

        System.out.printf("%n..... findLargestNumber ..... %n");
        System.out.printf("numList=%s%n", numList);
        System.out.printf("largestNumberV1=%d%n", largestNumberV1);
        System.out.printf("largestNumberV2=%d%n",  largestNumberV2);
        System.out.printf("largestNumberV3=%d%n",  largestNumberV3);
        System.out.printf("largestNumberV4=%d%n",  largestNumberV4);
    }

    private static void findNthLargestNumber() {
        List<Integer> numList = Arrays.asList(11, 2, 31, 17, 101, 131, 203, 5);
        int secondLargestNumber = numList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        int thirdLargestNumber = numList.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().get();

        System.out.printf("%n..... findNthLargestNumber ..... %n");
        System.out.printf("numList=%s%n", numList);
        System.out.printf("secondLargestNumber=%d%n", secondLargestNumber);
        System.out.printf("thirdLargestNumber=%d%n", thirdLargestNumber);
    }

    private static void findTopKLargestNumbers() {
        List<Integer> numList = Arrays.asList(11, 2, 31, 17, 101, 131, 203, 5);
        int k = 3;
        List<Integer> topNumList = numList.stream().sorted(Comparator.reverseOrder()).limit(k).toList();

        System.out.printf("%n..... findTopKLargestNumbers ..... %n");
        System.out.printf("numList=%s%n", numList);
        System.out.printf("findTopKLargestNumbers: k=%d, topNumList=%s%n%n", k, topNumList);
    }

    /**
     * Given a list of integers, find out all the even numbers
     * that exist in the list using Stream functions.
     */
    private static void filterAllEvenNumbersInList() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumList = intList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.printf("%n..... filterAllEvenNumbersInList ..... %n");
        System.out.printf("intList=%s%n", intList);
        System.out.printf("evenNumList=%s%n", evenNumList);
    }

    /**
     * Given a list of integers, find out all the numbers starting with 1 using Stream functions.
     */
    private static void findAllNumbersStartingWith() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 111);
        int firstDigit = 1;
        List<Integer> numListStartingWith = intList.stream().filter(
                n -> {
                    int fDigit = getFirstDigit(n);
                    return fDigit == firstDigit;
                }
        ).collect(Collectors.toList());
        System.out.printf("%n..... findAllNumbersStartingWith ..... %n");
        System.out.printf("firstDigit=%d, intList=%s%n", firstDigit, intList);
        System.out.printf("numListStartingWith=%s%n", numListStartingWith);
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
        System.out.printf("%n..... findDuplicateNumbersInList ..... %n");
        System.out.printf("intList=%s%n", intList);
        System.out.printf("dupIntList=%s%n", dupIntList);
    }

    /**
     * Given the list of integers, find the first element of the list using Stream functions.
     */
    private static void findFirstNumberInList() {
        List<Integer> intList = Arrays.asList(100, 200, 300, 999);
        int firstNum = intList.stream().findFirst().orElse(-1);
        System.out.printf("%n..... findFirstNumberInList ..... %n");
        System.out.printf("intList=%s%n", intList);
        System.out.printf("firstNum=%d%n", firstNum);
    }

    /**
     * Given a list of integers, find the total number of elements present in the list using Stream functions
     */
    private static void findNumberOfElementsInList() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        long elementCount = intList.stream().count();
        System.out.printf("findNumberOfElementsInList: elementCount=%d%n", elementCount);
    }

    private static int getFirstDigit(int num) {
        int numDigits = (int) Math.log10(num) + 1;
        return (int) (num / Math.pow(10, numDigits - 1));
    }

}
