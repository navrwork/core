package com.navr.core.java8;

import com.navr.core.java8.common.Person;
import com.navr.core.java8.common.PersonHelper;
import com.navr.core.java8.common.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {
    public static void main(String[] args) {
        mapExample();
        streamMap();
        listToMap();
    }

    private static void mapExample() {
        int[] num = {1, 2, 3};
        int squareSum = Arrays.stream(num)
                .map(n -> n * n)
                .sum();
        System.out.printf("%nmapExample squareSum=%d%n", squareSum);

    }

    private static void streamMap() {
        List<Person> personList = PersonHelper.getPersonList();

        // map and collect
        List<String> lastNamesList = personList.stream()
                .map(Person::getLastName)
                .collect(Collectors.toList());
        System.out.printf("%nlastNamesList=%s%n", lastNamesList);

        // map and collect
        Set<String> citySet = personList.stream()
                .map(Person::getCity)
                .collect(Collectors.toSet());
        System.out.printf("citySet=%s%n", citySet);

        // filter, map and collect
        // return a list of first names that starts with a specific char
        List<String> listOfFirstNamesThatStartsWith = personList.stream()
                .map(Person::getFirstName)
                .filter(firstName -> {
                    return firstName.startsWith("S");
                })
                .collect(Collectors.toList());
        System.out.printf("listOfFirstNamesThatStartsWith=%s%n", listOfFirstNamesThatStartsWith);

        // map/transform and collect
        // combine firstName and lastName and return a list
        List<String> fullNameList = personList.stream()
                .map(person -> {
                    return person.getFirstName() + " " + person.getLastName();
                })
                .collect(Collectors.toList());
        System.out.printf("fullNameList=%s%n", fullNameList);

        // map/transform and collect
        // fetch first few chars from each element and return a list
        Stream<String> s = Stream.of("apple", "banana", "orange");
        List<String> listOfFirstFewChars = s
                .map(s1 -> s1.substring(0, 2))
                .collect(Collectors.toList());
        System.out.printf("listOfFirstFewChars=%s%n", listOfFirstFewChars);

    }

    /**
     * Reference: https://www.baeldung.com/java-collectors-tomap
     */
    private static void listToMap() {
        List<Product> productList = Arrays.asList(
                new Product(1001L, "Google", "Pixel 8", 90000),
                new Product(1002L, "Google", "Pixel 8 Pro", 150000),
                new Product(1003L, "Apple", "iPhone 16", 150000),
                new Product(1004L, "Apple", "iMac", 250000)
        );

        // list to Map using unique key
        Map<Long, Product> idToProductMap = productList.stream()
                .collect(Collectors.toMap(
                                Product::getProductId,
                                Function.identity()
                        )
                );
        System.out.printf("%nCollectors.toMap: idToProductMap=%s%n", idToProductMap);

        Map<Long, String> idToBrandNameMap = productList.stream()
                .collect(Collectors.toMap(
                                Product::getProductId,
                                Product::getBrandName
                        )
                );
        System.out.printf("Collectors.toMap: idToBrandNameMap=%s%n", idToBrandNameMap);

        // list to map with duplicate key throws IllegalStateException
        Map<String, Product> brandNameToProductMapV1 = null;
        try {
            brandNameToProductMapV1 = productList.stream()
                    .collect(Collectors.toMap(
                                    Product::getBrandName,
                                    Function.identity()
                            )
                    ); // IllegalStateException thrown here
            System.out.printf("Collectors.toMap: brandNameToProductMapV1=%s%n", brandNameToProductMapV1);
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Collectors.toMap: brandNameToProductMapV1 failed. IllegalStateException ex=" + illegalStateException.getMessage());
        }

        // Handle duplicate key issue using a merge function
        Map<String, Product> brandNameToProductMapV2 = productList.stream()
                .collect(Collectors.toMap(
                        Product::getBrandName,
                        Function.identity(),
                        (existing, replacement) -> existing)
                );
        System.out.printf("Collectors.toMap: brandNameToProductMapV2=%s%n", brandNameToProductMapV2);
    }

}
