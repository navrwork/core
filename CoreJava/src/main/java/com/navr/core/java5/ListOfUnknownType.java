package com.navr.core.java5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfUnknownType {
    public static void main(String[] args) {
        listOfUnknownType();
    }

    /**
     * List&lt;?&gt; is a generic list where the '?' represents an unknown type (wildcard).
     * <ul>
     * <li>
     *     List&lt;?&gt; means a list of some specific but unknown type (say, List&lt;String&gt; or List&lt;Integer&gt;).
     *     This type is fixed, but you don't know what it is.
     * </li>
     * <li>
     *     You cannot add any element to the list, except for null. This is because the wildcard '?'
     *     essentially means "unknown type," so the compiler cannot determine the type safety of the
     *     elements being added.
     * </li>
     * <li>
     *     The wildcard indicates that the list is read-only in terms of adding new elements.
     * </li>
     * <li>
     *     While you can read elements from the list (and they'll be treated as Object), you can't add
     *     new elements since the exact type of the list is unknown. This protects type safety.
     *     So while the list itself is not truly "read-only," the wildcard enforces restrictions
     *     on adding elements to maintain type safety.
     * </li>
     * <li>
     *     You can modify existing elements in the underlying list if you have access to it
     *     and it allows modifications
     * </li>
     * <li>
     *     Note that List&lt;?&gt; is same as List&lt;? extends Object&gt;
     * </li>
     * </ul>
     *
     * <br/>
     * Note: <br/>
     * Although every class in Java is indeed a subclass of Object, List&lt;?&gt; and List&lt;Object&gt; are not
     * the same because of how Java generics work and how type safety is enforced. <br/>
     * List&lt;?&gt; means a list of some specific but unknown type. This type is fixed, but you don't know
     * what it is.
     *
     *
     *
     */
    private static void listOfUnknownType() {
        List<?> listOfUnknown = new ArrayList<>();
//        listOfUnknown.add("abc"); // Compilation error
//        listOfUnknown.add(123); // Compilation error
//        listOfUnknown.add(new Object()); // Compilation error
        listOfUnknown.add(null); // Ok

        Object o = listOfUnknown.get(0); // Read is Ok, but will be of type Object

        List<String> strList = List.of("a", "b", "c");
        List<Integer> intList = List.of(1, 2, 3);

        List<?> listOfUnknown2 = strList; // Ok.
        System.out.println("\nList<?> and List<String> ...");
        listOfUnknown2.forEach(System.out::println);

        List<?> listOfUnknown3 = intList; // Ok.
        System.out.println("\nList<?> and List<Integer> ...");
        listOfUnknown3.forEach(System.out::println);

        List<Integer> integerList = Arrays.asList(100, 200, 300);
        List<?> listOfUnknown4 = integerList; // Ok.
        System.out.println("\nList<?> and List<Integer> backed by array ...");
        listOfUnknown4.forEach(System.out::println);
        integerList.set(1, 202); // Ok to modify underlying list
        listOfUnknown4.forEach(System.out::println);
    }
}
