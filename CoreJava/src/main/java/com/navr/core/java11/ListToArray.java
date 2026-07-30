package com.navr.core.java11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Method 1: Using toArray(T[] a) with a pre-sized array
        String[] array1 = list.toArray(new String[list.size()]);
        System.out.println("Array 1: " + Arrays.toString(array1));

        // Method 2: Using toArray(T[] a) with a zero-length array (more efficient)
        String[] array2 = list.toArray(new String[0]);
         System.out.println("Array 2: " + Arrays.toString(array2));

        // Method 3: Using streams (Java 8 and above)
        String[] array3 = list.stream().toArray(String[]::new);
        System.out.println("Array 3: " + Arrays.toString(array3));
    }
}