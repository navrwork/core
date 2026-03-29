package com.navr.learn.java10;

import java.util.List;

/**
 * List.of() (introduced in Java 9) creates an immutable list. You cannot add, remove, or modify elements after creation.
 * <br/><br/>
 * List.of() vs Arrays.asList():
 * <ul>
 *     <li>List.of() (introduced in Java 9) creates an immutable list.
 *         Arrays.asList() creates a fixed-size list backed by the original array.
 *         You cannot add or remove elements (throws UnsupportedOperationException), but you can modify existing elements if they are mutable objects.</li>
 *     <li>List.of() does not allow null elements (throws NullPointerException if any element is null). Arrays.asList() allows null elements.</li>
 *     <li>Both are type-safe, but List.of() is more modern and preferred for immutable collections.</li>
 *     <li>List.of() is optimized for immutability and may have slight performance benefits in read-heavy scenarios.</li>
 * </ul>
 */
public class ListOf {
    public static void main(String[] args) {
        listOfStrings();
        listOfNull();
    }

    /**
     * ou cannot add, remove, or modify elements after creation. Attempting to do so throws UnsupportedOperationException.
     */
    private static void listOfStrings() {
        var listOfStr = List.of("a", "b", "c"); // Immutable object
//        list.add("a"); // UnsupportedOperationException - Cannot add new elements to the list
//        list.add("b"); // UnsupportedOperationException
//        listOfStr.set(0, "x"); // UnsupportedOperationException - Cannot modify any element in the list
        System.out.printf("listOfStrings: listOfStr=%s%n", listOfStr);
    }

    /**
     * List.of() does not allow null elements (throws NullPointerException if any element is null).
     */
    private static void listOfNull() {
        try {
            var listOfStr = List.of("a", "b", null); // NullPointerException here
            System.out.printf("listOfNull: listOfStr=%s%n", listOfStr);
        } catch (NullPointerException e) {
            System.out.println("listOfNull: NullPointerException occurred because List.of() does not allow null elements.");
        }

    }
}
