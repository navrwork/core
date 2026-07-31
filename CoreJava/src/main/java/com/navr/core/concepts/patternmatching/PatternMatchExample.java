package com.navr.core.concepts.patternmatching;


/**
 * patternMatch Using instanceOf available since Java 16. This type of pattern is also called as Type Patterns.
 * Pattern matching using Switch is available since Java 21.
 *
 */
public class PatternMatchExample {
    public static void main(String[] args) {
        patternMatchTraditional("Hello");
        patternMatchTraditional(42);
        patternMatchTraditional(3.14);

        patternMatchUsingInstanceOf("Hello");
        patternMatchUsingInstanceOf(42);
        patternMatchUsingInstanceOf(3.14);

        patternMatchUsingSwitch("Hello, World!");
        patternMatchUsingSwitch(42);
        patternMatchUsingSwitch(null);
        patternMatchUsingSwitch(3.14);
    }

    private static void patternMatchTraditional(Object obj) {
        if (obj instanceof String) {
            String s  = (String) obj;
            System.out.println("patternMatchTraditional: It's a string: " + s);
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.println("patternMatchTraditional: It's an integer: " + i);
        } else {
            System.out.println("patternMatchTraditional: It's not a string or an integer");
        }
    }

    private static void patternMatchUsingInstanceOf(Object obj) {
        if (obj instanceof String s) {
            System.out.println("patternMatchUsingInstanceOf: It's a string: " + s);
        } else if (obj instanceof Integer i) {
            System.out.println("patternMatchUsingInstanceOf: It's an integer: " + i);
        } else {
            System.out.println("patternMatchUsingInstanceOf: It's not a string or an integer");
        }
    }

    private static void patternMatchUsingSwitch(Object obj) {
        switch (obj) {
            case String s -> System.out.println("patternMatchUsingSwitch: It's a string: " + s);
            case Integer i -> System.out.println("patternMatchUsingSwitch: It's an integer: " + i);
            case null -> System.out.println("patternMatchUsingSwitch: It's a null: " + null);
            default -> System.out.println("patternMatchUsingSwitch: It's not a string or an integer");
        }
    }
}
