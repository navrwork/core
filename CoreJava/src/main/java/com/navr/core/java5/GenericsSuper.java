package com.navr.core.java5;

import java.util.ArrayList;
import java.util.List;

/**
 * The use of the super keyword in Java generics enables specifying a <b>lower bound</b>
 * for the type parameter. It restricts the unknown type parameter to be a <b>specific type or a supertype</b> of that type.
 * <p>
 * The &lt;? super T&gt; syntax means that the list can be of type T or any superclass of T.
 * This is useful when you need to <b>add elements</b> to a collection, as it ensures that the collection
 * can hold objects of the specified type or its superclasses.
 * </p>
 */
public class GenericsSuper {
    public static void main(String[] args) {
        readGenericsSuper();
        writeGenericsSuper();
    }

    private static void readGenericsSuper() {
        List<Object> objList = List.of(1, 2);
        printIntegers(objList); // Ok. Object type is a super class of Integer.

        List<Number> numList = List.of(11, 22);
        printIntegers(numList); // Ok. Number type is a super class of Integer.

        List<Integer> intList = List.of(111, 222);
        printIntegers(intList); // Ok. Integer type.

        List<Double> doubleList = List.of(1.0, 2.0, 3.0);
        // Compilation error: 'printIntegers(java.util.List<? super java.lang.Integer>)' in 'com.navr.learn.java5.GenericsSuper' cannot be applied to '(java.util.List<java.lang.Double>)'
//        printIntegers(doubleList); // NOT Ok. Double not a super class of Integer.
    }

    private static void printIntegers(List<? super Integer> intList) {
        for (Object o : intList) { // Element read from the list will ALWAYS be of 'Object' type. Explicit casting required for a specific type.
            if (o instanceof Integer i) {
                System.out.printf("printIntegers: i=%d%n", i);
            } else if (o instanceof Number n) {
                System.out.printf("printIntegers: n=%s%n", n);
            } else if (o != null) {
                System.out.printf("printIntegers: o=%s%n", o);
            }
        }
    }

    private static void writeGenericsSuper() {
        System.out.println("\n ## writeGenericsSuper() ##");
        List<Number> numberList = new ArrayList<>();
        numberList.add(100);
        System.out.printf("writeGenericsSuper: numberList (before): %s%n", numberList);
        addIntegers(numberList);
        System.out.printf("writeGenericsSuper: numberList (after): %s%n", numberList);
    }

    private static void addIntegers(List<? super Integer> intList) {
        intList.add(111);
        intList.add(222);
    }

    private static void checkAnimalDogPuppy() {
        List<Object> objList = new ArrayList<>();
        List<Animal> animalList = new ArrayList<>();
        List<Dog> dogList = new ArrayList<>();
        List<Puppy> puppyList = new ArrayList<>();
        addDogToList(objList); // Ok. List<Object> is supertype of Dog.
        addDogToList(animalList); // Ok. List<Animal> is supertype of Dog.
        addDogToList(dogList); // Ok. List<Dog> is same type as Dog.
        //
        // The wildcard `<? super Dog>` ensures that the list can safely accept `Dog` or its subclasses (`Puppy`).
        // However, `List<Puppy>` is a subtype of `List<Dog>` and **not a supertype of `Dog`**, so it doesn't satisfy the `? super Dog` constraint.
        // You cannot pass `List<Puppy>` where `List<? super Dog>` is expected because it violates the "supertype" requirement.
        //
//        addDogToList(puppyList); // Not Ok.
    }

    /**
     * Key Rules: <br/>
     * 1. Generics Invariance: `List&lt;Animal&gt;`, `List&lt;Dog&gt;`, and `List&lt;Puppy&gt;` are treated as distinct types,
     * even if they belong to the same inheritance hierarchy. <br/>
     * 2. Wildcard Rules: The `? super Dog` wildcard allows passing `List&lt;Dog&gt;` or any list of supertypes
     * (like `List&lt;Animal&gt;` or `List&lt;Object&gt;`), but not subtypes like `List&lt;Puppy&gt;`.
     */
    public static void addDogToList(List<? super Dog> list) {
        //
        // Compilation error: 'add(capture<? super com.navr.learn.java5.Dog>)' in 'java.util.List' cannot be applied to '(com.navr.learn.java5.Animal)'
        //
//        list.add(new Animal());

        // Adding a Dog instance
        list.add(new Dog());

        // Adding a Puppy instance (Puppy is a subclass of Dog)
        list.add(new Puppy());
    }

}


class Animal {
}

class Dog extends Animal {
}

class Puppy extends Dog {
}
