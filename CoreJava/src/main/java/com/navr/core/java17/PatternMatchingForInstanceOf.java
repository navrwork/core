package com.navr.core.java17;


record Location (int x, int y) {}

/**
 * <pre>
 *     The pattern matching for instanceof operator eliminates the boilerplate code to type check and
 *     cast to a variable. <br/>
 *     The feature was introduced in Java 16. Since Java 19 it now also works with records.
 *
 * </pre>
 */
public class PatternMatchingForInstanceOf {
    public static void main(String[] args) {
        traditionalInstanceof("hello");
        traditionalInstanceof(123);
        traditionalInstanceof(500f);
        patternMatchingForInstanceof("hello");
        patternMatchingForInstanceof(123);
        patternMatchingForInstanceof(500f);

        // record pattern matching
        Location l = new Location(100, 200);
        patternMatchingForRecord(l);
    }

    private static void traditionalInstanceof(Object obj) {
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.printf("traditionalInstanceof: obj instanceof String. %s%n", s);
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.printf("traditionalInstanceof: obj instanceof Number. %d%n", i);
        } else {
            System.out.printf("traditionalInstanceof: obj instanceof unknown. %s%n", obj);
        }
    }

    private static void patternMatchingForInstanceof(Object obj) {
        if (obj instanceof String s) {
            System.out.printf("patternMatchingForInstanceof: obj instanceof String. %s%n", s);
        } else if (obj instanceof Integer i) {
            System.out.printf("patternMatchingForInstanceof: obj instanceof Number. %d%n", i);
        } else {
            System.out.printf("patternMatchingForInstanceof: obj instanceof unknown. %s%n", obj);
        }
    }

    private static void patternMatchingForRecord(Object o) {
        if (o instanceof Location(int x, int y)) {
            System.out.printf("patternMatchingForRecord: Location(%d, %d)%n", x, y);
        }
    }
}
