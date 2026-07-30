package com.navr.core.java5;

import java.util.ArrayList;
import java.util.List;

/**
 * List&lt;Object&gt; is quite different from List&lt;?&gt;. <br/>
 * Key Characteristics of List&lt;Object&gt;: <br/>
 * <ul>
 *     <li>
 *         You can add elements of any type: Since Object is the parent class of all Java classes,
 *         you can add elements of any type (like String, Integer, or custom objects) to the list.
 *     </li>
 *     <li>
 *         Read elements: When you retrieve elements from the list, they will also be of type Object.
 *         You'll need to cast them if you want to use them as their specific type.
 *     </li>
 *     <li>
 *         No type-safety guarantees: Using List&lt;Object&gt; removes the compile-time type-checking provided
 *         by generics. This means you can accidentally add elements of incompatible types,
 *         leading to runtime errors when casting.
 *     </li>
 *     <li>
 *         Use it when you intentionally want to store a mix of different types in the list and you're
 *         okay with handling type casting.
 *     </li>
 * </ul>
 */
public class ListOfObject {
    public static void main(String[] args) {
        listOfObject();
    }

    private static void listOfObject() {
        List<Object> listOfObject = new ArrayList<>();
        listOfObject.add(new Object());
        listOfObject.add("hello"); // Ok. String instance.
        listOfObject.add(100); // Ok. Integer instance.
        listOfObject.add(111.1f); // Ok Float instance.
        listOfObject.add(true); // Ok. Boolean instance.

        // Read from the list
        Object element1 = listOfObject.get(1); // retrieved element will be of type Object
        String elementStr = (String)element1;
        System.out.printf("elementStr.toUpperCase: %s%n", elementStr.toUpperCase());

        Object element2 = listOfObject.get(2); // refers to Integer type
        // You can cast to any other Object type
        // No compile time error
        // However, ClassCastException will be thrown at runtime for incompatible types
//        String element2Str = (String) element2; // Not Ok. java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
//        System.out.printf("element2Str: %s%n", element2Str);
    }
}
