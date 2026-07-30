package com.navr.core.java8;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
    public static void main(String[] args) {
        reduce();
    }

    /**
     * Reduction stream operations allow us to produce one single result from a
     * sequence of elements, by repeatedly applying a combining operation to the
     * elements in the sequence.
     */
    private static void reduce() {
        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5, 6);
        int numbersListSum = numbersList.stream()
                .reduce(0, Integer::sum);
        System.out.printf("reduce: numbersListSum=%d%n", numbersListSum); // 21

        List<String> lettersList = Arrays.asList("a", "b", "c", "d", "e");
        String lettersListConcat = lettersList.stream()
                .reduce("", String::concat);
        System.out.printf("reduce: lettersListConcat=%s%n", lettersListConcat); // "abcde"

        String lettersListUpperCase = lettersList.stream()
                .reduce("", (partialStr, element) -> partialStr + element.toUpperCase());
        System.out.printf("reduce: lettersListUpperCase=%s%n", lettersListUpperCase); // "ABCDE"
    }
}
