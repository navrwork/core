package com.navr.core.java5;

import java.util.ArrayList;
import java.util.List;

/**
 * 'List&lt;? extends T&gt;' introduces a bounded wildcard (? extends T), which is
 * very powerful but has its own restrictions.
 * <ul>
 *       <li>
 *             For instance, '? extends Number' represents a list of some unknown type that extends (or is a subclass of) Number.
 *         For example, the list could be of type List&lt;Integer&gt;, List&lt;Double&gt;, List&lt;Float&gt;, etc.,
 *         but you won't know the exact type at compile time.
 *      </li>
 *      <li>
 *          You cannot add new elements to this list because the exact type of the list is unknown.
 *          The compiler enforces type safety by disallowing additions (except for null).
 *      </li>
 *      <li>
 *          Why use List&lt;? extends T&gt;? <br/>
 *          It is useful for reading elements when you know that the list contains types extending T but
 *          don't know the exact type.
 *          It's often used in methods that process elements but don't modify the list.
 *      </li>
 * </ul>
 */
public class ListOfUnknownBoundedSubType {
    public static void main(String[] args) {
        listOfUnknownTypeThatExtendsNumber();
    }

    private static void listOfUnknownTypeThatExtendsNumber() {
        List<? extends Number> numberList = new ArrayList<>(); // Ok.
        numberList = new ArrayList<Number>(); // Ok.
        numberList = new ArrayList<Integer>(); // Ok.
        numberList = new ArrayList<Double>(); // Ok.

        numberList.add(null); // Ok. Only 'null' can be added (written) to the list. No other write is allowed.

//        numberList = new ArrayList<Object>(); // NOT Ok. Incompatible types. Found: 'java.util.ArrayList<java.lang.Object>', required: 'java.util.List<? extends java.lang.Number>'

//        numberList.add(Integer.valueOf(100)); // NOT Ok. 'add(capture<? extends java.lang.Number>)' in 'java.util.List' cannot be applied to '(java.lang.Integer)'

        // Reading the list
        numberList = List.of(1, 2, 3); // Ok. List<Integer>
        Number num = numberList.get(0); // Ok. Element read will be treated as type Number.
        System.out.printf("numberList(0): %d%n", num);

        Number numObj = numberList.get(0); // Ok. List contains elements of 'Number' subtype, but we don't know what exactly it is.
        // Integer intNum = numberList.get(0); // NOT Ok. Incompatible types. Found: 'capture<? extends java.lang.Number>', required: 'java.lang.Integer'
    }

}
