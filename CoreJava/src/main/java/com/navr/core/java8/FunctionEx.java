package com.navr.core.java8;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionEx {
    public static void main(String[] args) {
        functionTest();
        biFunctionTest();
        biFunctionAndThen();
        predicateTest();
        biPredicateTest();
        consumerTest();
        biConsumerTest();
    }

    private static void functionTest() {
        Function<Integer, Integer> funcTwoTimes = integer -> {
            return 2 * integer;
        }; // statement lambda
        int n = 10;
        int result = funcTwoTimes.apply(n);
        System.out.printf("functionTest funcTwoTimes: n=%d, result=%d%n", n, result);

        Function<Integer, Integer> funcTenTimes = integer -> 10 * integer; // expression lambda
        result = funcTenTimes.apply(n);
        System.out.printf("functionTest funcTenTimes: n=%d, result=%d%n", n, result);
    }

    private static void biFunctionTest() {
        BiFunction<Integer, Integer, Long> funcProduct = (n1, n2) -> {
            return (long) n1 * n2;
        };
        int n1 = 10;
        int n2 = 50;
        long result = funcProduct.apply(n1, n2);
        System.out.printf("biFunctionTest funcProduct: n1=%d, n2=%d, result=%d%n", n1, n2, result);
    }

    private static void biFunctionAndThen() {
        int number1 = 10, number2 = 20;

        BiFunction<Integer, Integer, Long> funcAddNumbers = (n1, n2) -> (long) n1 + n2;
        Function<Long, Long> funcTwoTimes = n -> 2 * n;
        Function<Long, String> funcToHexString = Long::toHexString;

        String result = funcAddNumbers.andThen(funcTwoTimes).andThen(funcToHexString).apply(number1, number2);
        System.out.printf("biFunctionAndThen: number1=%d, number2=%d, result=%s%n", number1, number2, result);
    }

    private static void predicateTest() {
        Predicate<Integer> funcIsOddNum = (n) -> n % 2 != 0;
        int num = 99;
        boolean result = funcIsOddNum.test(num);
        System.out.printf("predicateTest funcIsOddNum: num=%d, result=%s%n", num, result);

        num = 10;
        result = funcIsOddNum.test(num);
        System.out.printf("predicateTest funcIsOddNum: num=%d, result=%s%n", num, result);
    }

    private static void biPredicateTest() {
        BiPredicate<Integer, Integer> funcIsFactorOf = (n1, n2) -> n2 % n1 == 0;
        int n1 = 2, n2 = 8;
        boolean result = funcIsFactorOf.test(n1, n2);
        System.out.printf("biPredicateTest: n1=%d, n2=%d, result=%s%n", n1, n2, result);

        n1 = 3;
        result = funcIsFactorOf.test(n1, n2);
        System.out.printf("biPredicateTest: n1=%d, n2=%d, result=%s%n", n1, n2, result);
    }

    private static void consumerTest() {
        Consumer<String> funcPrintUppercase = (s) -> System.out.println(s + " -> " + s.toUpperCase());
        String s = "hello";
        System.out.printf("consumerTest: s=%s%n", s);
        funcPrintUppercase.accept(s);
    }

    private static void biConsumerTest() {
        BiConsumer<String, String> funcPrintFullName = (s1, s2) -> System.out.printf("FullName: %s %s%n", s1, s2);
        String fName = "mickey";
        String lName = "mouse";

        System.out.printf("biConsumerTest: fName=%s, lName=%s%n", fName, lName);

        funcPrintFullName.accept(fName, lName);

        Function<String, String> funcToUppercase = String::toUpperCase;
        funcPrintFullName.accept(funcToUppercase.apply(fName), funcToUppercase.apply(lName));
    }
}
