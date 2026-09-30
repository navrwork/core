package com.navr.core.concepts.clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates shallow copy behavior in Java.
 *
 * <p>A shallow copy creates a new top-level object, but the inner objects
 * referenced by it are still shared with the original object.</p>
 *
 * <p>Inference:
 * <li>Shallow copy shares references to mutable objects, so modifications to those objects affect both the original and the copy.</li>
 * <li>However, replacing the object reference is a different use case. It doesn’t replace the other outer object’s reference</li>
 * <li>The lists themselves are separate, so adding or removing entries in one does not change the other. But the object inside the list is shared.</li>
 * <li>If both lists contain a reference to the same mutable Address, modifying that mutable object through either list is visible through both</li>
 * </p>
 */
public class ShallowCopyExample {

    /**
     * Entry point for the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        demoShallowCopyWithMutableObjects();
        demoShallowCopyWithImmutableObjects();
        demoListWithMutableObjects();
        demoListWithImmutableObjects();
    }

    /**
     * Demonstrates the behavior of shallow copies with mutable objects.
     * <p>
     * <li>
     * If both objects contain a reference to the same mutable object, modifying that object through either object is visible through both.
     * </li>
     * <li>
     * The outer objects themselves are separate, so replacing the inner object in one does not change the other. But the Address object inside is shared.
     * </li>
     *
     */
    private static void demoShallowCopyWithMutableObjects() {
        System.out.printf("========== demoShallowCopyWithMutableObjects ==========%n");
        System.out.printf("Mutable objects (Address) - Shallow copy behavior demonstration.%n");
        Address address = new Address("Paris");
        Person originalPerson = new Person("Alice", address);

        // Create a shallow copy of the original person
        Person shallowCopyPerson = new Person(originalPerson.name, originalPerson.address);

        System.out.printf("Original person: name: %s, city: %s%n", originalPerson.name, originalPerson.address.city);
        System.out.printf("Shallow copy person: name: %s, city: %s -> Shallow copy same as original%n", shallowCopyPerson.name, shallowCopyPerson.address.city);

        // Modify the city of the address in the shallow copy
        shallowCopyPerson.address.city = "London";

        System.out.printf("--- After modifying shallow copy's address city: ---%n");
        System.out.printf("Shallow copy person: name: %s, city: %s -> Manually updated shallow copy data.%n", shallowCopyPerson.name, shallowCopyPerson.address.city);
        System.out.printf("Original person: name: %s, city: %s -> Change gets reflected in the original as the address object is shared and mutable.%n", originalPerson.name, originalPerson.address.city);
        System.out.printf("=========================================================%n%n");
    }

    /**
     * Demonstrates the behavior of shallow copies with immutable objects.
     * <p>
     * <li>
     * If both objects contain a reference to the same immutable object, modifying that object through either object is not possible, as immutable objects cannot be changed.
     * </li>
     * <li>
     * The outer objects themselves are separate, so replacing the inner object in one does not change the other. But the String objects inside are immutable.
     * </li>
     */
    private static void demoShallowCopyWithImmutableObjects() {
        System.out.printf("========== demoShallowCopyWithImmutableObjects ==========%n");
        System.out.printf("Immutable objects (Strings) - Shallow copy behavior demonstration.%n");
        String originalString = "Hello";
        String shallowCopyString = originalString; // Shallow copy: both references point to the same immutable String object

        System.out.printf("Original string: %s%n", originalString);
        System.out.printf("Shallow copy string: %s%n", shallowCopyString);

        // Modify the original string by assigning a new String
        originalString = "NewHello"; // Does not affect the shallow copy, as Strings are immutable

        System.out.printf("After modifying the original string:%n");
        System.out.printf("Original string: %s -> Manually updated original data.%n", originalString);
        System.out.printf("Shallow copy string: %s -> Change NOT reflected in the shallow copy.%n", shallowCopyString);
        System.out.printf("=========================================================%n%n");
    }

    /**
     * Demonstrates the behavior of shallow copies with a list of mutable objects.
     * <p>
     * <li>
     * If both lists contain a reference to the same mutable object, modifying that object through either list is visible through both.
     * </li>
     * <li>
     * The lists themselves are separate, so adding or removing entries in one does not change the other. But the Address object inside is shared.
     * </li>
     */
    private static void demoListWithMutableObjects() {
        System.out.printf("========== demoListWithMutableObjects ==========%n");
        System.out.printf("List of mutable objects (Address) - Shallow copy behavior demonstration.%n");
        List<Address> originalList = new ArrayList<>();
        originalList.add(new Address("New York"));
        originalList.add(new Address("Los Angeles"));

        List<Address> shallowCopyList = new ArrayList<>(originalList); // Shallow copy

        System.out.println("--- Original list before modification ---");
        for (Address addr : originalList) {
            System.out.println(addr.city);
        }

        // Modify the city of the first address in the shallow copy
        String newCity = "San Francisco";
        shallowCopyList.getFirst().city = newCity;
        System.out.printf("Modified shallow copy's first address city to: %s %n", newCity);

        System.out.println("--- Original list after modifying shallow copy ---");
        for (Address addr : originalList) {
            System.out.println(addr.city); // This will show new city name for the first address
        }
        System.out.printf("=========================================================%n%n");
    }

    /**
     * Demonstrates the behavior of shallow copies with a list of immutable objects.
     * <p>
     * <li>
     * If both lists contain a reference to the same immutable object, modifying that object through either list is not possible, as immutable objects cannot be changed.
     * </li>
     * <li>
     * The lists themselves are separate, so adding or removing entries in one does not change the other. But the String objects inside are immutable.
     * </li>
     */
    private static void demoListWithImmutableObjects() {
        System.out.printf("========== demoListWithImmutableObjects ==========%n");
        System.out.printf("List of immutable objects (Strings) - Shallow copy behavior demonstration.%n");
        List<String> originalList = new ArrayList<>();
        originalList.add("Java");
        originalList.add("Python");

        List<String> shallowCopyList = new ArrayList<>(originalList); // Shallow copy

        System.out.println("--- Original list before modification ---");
        for (String lang : originalList) {
            System.out.println(lang);
        }

        // Modify the first element in the shallow copy
        String newLanguage = "C++";
        shallowCopyList.set(0, newLanguage); // This replaces the reference in the shallow copy, but does not affect the original list
        System.out.println("Modified shallow copy's first element to: " + newLanguage);

        System.out.println("--- Original list after modifying shallow copy ---");
        for (String lang : originalList) {
            System.out.println(lang); // This will still show "Java" for the first element
        }

        System.out.println("--- Shallow copy list after modification ---");
        for (String lang : shallowCopyList) {
            System.out.println(lang); // This will show "C++" for the first element
        }
        System.out.printf("=========================================================%n%n");
    }

    /**
     * Represents a person's address.
     */
    static class Address {
        String city;

        /**
         * Creates an Address object.
         *
         * @param city the city name
         */
        Address(String city) {
            this.city = city;
        }
    }

    /**
     * Represents a person.
     */
    static class Person {
        String name;
        Address address;

        /**
         * Creates a Person object.
         *
         * @param name    the person's name
         * @param address the person's address
         */
        Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }
    }
}