package com.navr.core.java8;

import java.util.List;

public class LambdaAndLocalVar {

    public static void main(String[] args) {
        final int finalVar = 10; // final variable
        int effFinalVar = 20; // effectively final variable. value assigned only once.
        int anotherVar = 30; // not effectively final variable as value assigned more than once.
        anotherVar++;

        int counter = 0;
        List<Integer> intList = List.of(1,2,3);
        intList.forEach(x -> System.out.printf(x+" ")); // Ok

        intList.forEach(x -> System.out.printf(counter+" ")); // Ok. Effectively final.

        // NOT Ok. Compilation error.
        // Variable not effectively final as value is assigned more than once.
//        intList.forEach(x -> System.out.printf(counter++));





    }


}
