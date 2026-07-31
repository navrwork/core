package com.navr.core.concepts.patternmatching.record;

public class RecordPatternMatch {
    public static void main(String[] args) {
        System.out.println(patternMatchAndRetrieveName(new Cat("Tom", "Black")));
        System.out.println(patternMatchAndRetrieveName(new Dog("Rex", "Brown")));
        System.out.println(patternMatchAndRetrieveName(null));

        System.out.println(guardedPatternMatchAndRetrieveName(new Cat("Tom", "Black")));
        System.out.println(guardedPatternMatchAndRetrieveName(new Dog("Rex", "Brown")));
        System.out.println(guardedPatternMatchAndRetrieveName(null));
        System.out.println(guardedPatternMatchAndRetrieveName(new Cat(null, "Black")));
    }

    private static String patternMatchAndRetrieveName(Animal animal) {
        return switch (animal) {
            case Cat(var name, var color) -> name;
            // .. OR ..
            // case Cat cat -> cat.name();
            case Dog(var name, var color) -> name;
            // .. OR ..
            // case Dog dog -> dog.name();
            case null -> "$";
        };
    }

    /**
     * guardedPatternMatch: Apply checks on the binding variables, not just on the type.
     * Available since Java 21.
     *
     * @param animal
     * @return
     */
    private static String guardedPatternMatchAndRetrieveName(Animal animal) {
        return switch (animal) {
            case Cat(var name, var color) when name == null -> "#";
            case Cat(var name, var color) -> name;
            case Dog(var name, var color) when name == null -> "#";
            case Dog(var name, var color) -> name;
            case null -> "$";
        };
    }
}