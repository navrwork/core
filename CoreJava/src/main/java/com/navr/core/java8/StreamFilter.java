package com.navr.core.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamFilter {

    public static void main(String[] args) {
        filterString();
        filterInt();
        filterMap();
        filterChain();
        filterWithCustomPredicate();
        filterNotNull();
        parallelStream();
    }

    public static void filterString() {
        List<String> strList = List.of("Apple", "Amazon", "Adobe", "Microsoft", "Lavasoft");

        List<String> filteredList = strList.stream()
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("\r\nfilterString ... startsWith(\"A\")");
        filteredList.forEach(System.out::println);
    }

    public static void filterInt() {
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> filteredList = intList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("\r\nfilterInt ... even numbers ");
        filteredList.forEach(System.out::println);
    }

    public static void filterMap() {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("United States", 1);
        myMap.put("Egypt", 20);
        myMap.put("South Africa", 27);
        myMap.put("France", 33);
        myMap.put("Germany", 49);
        myMap.put("Brazil", 55);
        myMap.put("Japan", 81);
        myMap.put("China", 86);
        myMap.put("India", 91);

        Map<String, Integer> filteredMap = myMap.entrySet().stream()
                .filter(e -> e.getValue() > 50)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("\r\nfilterMap ... countries with code gt 50");
        filteredMap.forEach((k, v) -> System.out.printf("%s, %d%n", k, v));
    }

    public static void filterChain() {
        List<String> strList = List.of("Apple", "Amazon", "Adobe", "Microsoft", "Lavasoft");

        List<String> filteredList = strList.stream()
                .filter(s -> s.startsWith("A"))
                .filter(s -> s.endsWith("e"))
                .collect(Collectors.toList());

        System.out.println("\r\nfilterChain ... startsWith(\"A\") and endsWith(\"e\")");
        filteredList.forEach(System.out::println);
    }

    public static void filterWithCustomPredicate() {
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> isEvenNumber = i -> i % 2 == 0;
        Predicate<Integer> isGt = i -> i > 5;
        List<Integer> filteredList = intList.stream()
                .filter(isEvenNumber.and(isGt))
                .collect(Collectors.toList());

        System.out.println("\r\nfilterWithCustomPredicate ... even numbers gt 5");
        filteredList.forEach(System.out::println);
    }

    public static void filterNotNull() {
        List<String> strList = Arrays.asList("Apple", "Amazon", null, "Microsoft", null);

        List<String> filteredList = strList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println("\r\nfilterNotNull ... ");
        filteredList.forEach(System.out::println);
    }

    public static void parallelStream() {
        List<Integer> filteredList = IntStream.rangeClosed(1, 25)
                .boxed()
                .parallel()
                .filter(i -> i % 5 == 0)
                .collect(Collectors.toList());

        System.out.println("\r\nparallelStream .. multiples of 5");
        System.out.println(filteredList);

    }
}
