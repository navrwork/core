package com.navr.core.java8;

import java.util.Arrays;
import java.util.List;

public class ListAdhoc {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};


        List<Integer> intListV1 = Arrays.asList(intArray);
        System.out.printf("intArray=%s, intListV1=%s%n", Arrays.toString(intArray), intListV1);

        //
        // Add a new element: Not allowed as the list is backed by a fixed-size array
        // UnsupportedOperationException
        //
//        intListV1.add(100); // not allowed
//        System.out.printf("After 'intListV1.add(100)' => intListV1=%s%n", intListV1); // java.lang.UnsupportedOperationException here

        //
        // Modify an existing element: Allowed. Underlying array is modified as well.
        // Ok
        //
        intListV1.set(0, 100); // allowed
        System.out.printf("After 'intListV1.set(0, 100)' => intListV1=%s, intArray=%s%n", intListV1, Arrays.toString(intArray));
        System.out.printf("After 'intListV1.set(0, 100)' => intListV1=%s%n", intListV1);

        List<Integer> intListV2 = List.of(1, 2, 3, 4, 5);
        System.out.printf("intListV2=%s%n", intListV2);
//        intListV2.add(100); // java.lang.UnsupportedOperationException
//        System.out.printf("After add(100) => intListV2=%s%n", intListV2);

//        intListV2.set(0, 100); // java.lang.UnsupportedOperationException
//        System.out.printf("After 'intListV2.set(0, 100)' => intListV2=%s%n", intListV2);
    }
}
