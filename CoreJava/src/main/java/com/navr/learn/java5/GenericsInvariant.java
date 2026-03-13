package com.navr.learn.java5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In Java, generic types are invariant, meaning that a List&lt;Subtype&gt; is not considered a subtype or
 * a supertype of List&lt;Supertype&gt;. This principle applies directly to method arguments.
 * <br/>
 * This invariance ensures type safety at compile time, preventing potential ClassCastException errors that
 * could occur at runtime if, for example, a Double were added to a List&lt;Number&gt; that was actually a List&lt;Integer&gt;.
 */
public class GenericsInvariant {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Number> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        doSomething(list2); // Ok.
        // doSomething(list1); // Not Ok. Generic types are invariant.

        saySomething(new ArrayList<>()); // Ok.
        saySomething(new ArrayList<Object>()); // Ok.
        saySomething(new ArrayList()); // Ok but with 'Raw use of parameterized class' Warning.
        // saySomething(new ArrayList<String>()); // Not Ok. Generic types are invariant.

    }

    private static void doSomething(List<Number> list) {
        System.out.printf("Doing something with %d elements\n", list.size());
    }

    private static void saySomething(List<Object> list) {
        System.out.printf("Saying something with %d elements\n", list.size());
    }

}
