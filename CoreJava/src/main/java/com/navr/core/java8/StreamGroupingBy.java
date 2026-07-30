package com.navr.core.java8;

import com.navr.core.java8.common.Fruit;
import com.navr.core.java8.common.Person;
import com.navr.core.java8.common.PersonHelper;
import com.navr.core.java8.common.Product;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class StreamGroupingBy {
    public static void main(String[] args) {
        groupingBy_1();

        groupingBy_2();

        groupingBy_3();

    }

    private static void groupingBy_1() {

        List<Fruit> listOfFruits = Arrays.asList(
                new Fruit("Apple", 1),
                new Fruit("Apple", 3),
                new Fruit("Banana", 2),
                new Fruit("Banana", 2),
                new Fruit("Banana", 5),
                new Fruit("Pear", 0),
                new Fruit("Orange", 4));

        // grouping of objects by one field
        Map<String, List<Fruit>> fruitGroupByOneField = listOfFruits.stream()
                .collect(Collectors.groupingBy(Fruit::getName));
        System.out.printf("%nfruitGroupByOneField=%s%n", fruitGroupByOneField);

        // grouping of objects by multiple fields
        Map<String, Map<Integer, List<Fruit>>> fruitGroupByTwoFields = listOfFruits.stream()
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.groupingBy(Fruit::getAmount))
                );
        System.out.printf("fruitGroupByTwoFields=%s%n", fruitGroupByTwoFields);

        // grouping and reduction
        Map<String, Set<Integer>> fruitGroupByAndReduce = listOfFruits.stream()
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.mapping(Fruit::getAmount, toSet()))
                );
        System.out.printf("fruitGroupByAndReduce=%s%n", fruitGroupByAndReduce);

        // grouping and count
        Map<String, Long> fruitGroupAndCount = listOfFruits.stream()
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.counting()));
        System.out.printf("fruitGroupAndCount=%s%n", fruitGroupAndCount);

        // grouping and average
        Map<String, Double> fruitGroupAndAvg = listOfFruits.stream()
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.averagingDouble(Fruit::getAmount)));
        System.out.printf("fruitGroupAndAvg=%s%n", fruitGroupAndAvg);

        // group list of string values by their length
        // 3, ["abc", "xyz"]
        // 5, ["hello", "piano"]
        List<String> strList = Arrays.asList("abc", "hello", "xyz", "piano");
        Map<Integer, List<String>> strGroupList = strList.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.printf("strGroupList=%s%n", strGroupList);

        // filter and group
        Map<String, List<Fruit>> fruitFilterAndGroup = listOfFruits.stream()
                .filter(fruit -> fruit.getName().startsWith("A")||fruit.getName().startsWith("B"))
                .collect(Collectors.groupingBy(Fruit::getName));
        System.out.printf("fruitFilterAndGroup=%s%n", fruitFilterAndGroup);

        // grouping with filter. return count.
        Map<String, Long> fruitFilterGroupAndCount = listOfFruits.stream()
                .filter(fruit -> fruit.getAmount() > 1 && fruit.getAmount() < 5)
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.counting()));
        System.out.printf("fruitFilterGroupAndCount=%s%n", fruitFilterGroupAndCount);

        // grouping with filter. return obj list.
        Map<String, List<Fruit>> fruitFilterGroupAndObj = listOfFruits.stream()
                .filter(fruit -> fruit.getAmount() > 1 && fruit.getAmount() < 5)
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.toList()));
        System.out.printf("fruitFilterGroupAndObj=%s%n", fruitFilterGroupAndObj);

        System.out.printf("%n%n");
    }

    private static void groupingBy_2() {
        List<Person> personList = PersonHelper.getPersonList();

        // Get count of number of persons in each city
        Map<String, Long> personCountByCity = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.printf("personCountByCity=%s%n", personCountByCity);

        // Get average age of persons by city
        Map<String, Double> personAvgAgeByCity = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));
        System.out.printf("personAvgAgeByCity=%s%n", personAvgAgeByCity);

        // Age stats grouped by city
        Map<String, IntSummaryStatistics> personSummaryStatsByCity = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.summarizingInt(Person::getAge)));
        System.out.printf("personSummaryStatsByCity=%s%n", personSummaryStatsByCity);

        System.out.printf("%n%n");
    }

    /**
     * groupingBy with productId (PK) as Map key and object as value.
     */
    private static void groupingBy_3() {
        List<Product> productList = Arrays.asList(
                new Product(1001L, "Google", "Pixel 8", 90000),
                new Product(1002L, "Google", "Pixel 8 Pro", 150000),
                new Product(1003L, "Google", "Chromecast", 10000)
        );

        Map<Long, List<Product>> productMap = productList.stream()
                .collect(Collectors.groupingBy(Product::getProductId));

        System.out.printf("productMap=%s%n", productMap);

    }

}
