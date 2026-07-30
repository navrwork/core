package com.navr.core.java21;

/**
 * <pre>
 * Pattern Matching feature allows a selector expression to be tested against
 * several patterns in addition to the test against traditionally supported constants.
 *
 * Note:
 * java: patterns in switch statements are a preview feature and are disabled by default (in Java 17).
 *   (use --enable-preview to enable patterns in switch statements)
 * * Use '--enable-preview --source 21' as Java compiler additional arguments.
 *
 * </pre>
 */
public class PatternMatchingForSwitch {
    public static void main(String[] args) {
        String c = "rEd";
        traditionalSwitchCase(c);
        getDoubleUsingSwitch(1.2345f);
    }

    private static void traditionalSwitchCase(String var) {
        //
        // In the earlier versions of Java, the selector expression had to be
        // a number, a string, or a constant.
        //
        String s1 = "REd"; // Not a Constant expression
        final String s2 = "rEd"; // Constant expression (final keyword used)
        switch (var) {
            case "red":
                System.out.println("traditionalSwitchCase: red");
                break;
            case "RED":
                System.out.println("traditionalSwitchCase: RED");
                break;
//            case s1: // Not allowed. "Constant expression required" compiler error.
//                System.out.printf("traditionalSwitchCase: s1=%s%n", s1);
//                break;
            case s2: // Allowed as the expression represents a final / constant var
                System.out.printf("traditionalSwitchCase: constant expr / final var --> %s%n", s2);
                break;
            default:
                System.out.printf("traditionalSwitchCase: default. var=%s%n", var);
        }
    }

    private static double getDoubleUsingSwitch(Object o) {
        double d = switch (o) {
            case Integer i -> i.doubleValue();
            case Float f -> f.doubleValue();
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
        System.out.printf("getDoubleUsingSwitch: d=%f%n", d);
        return d;
    }

    /**
     * Pattern Matching for Switch feature allows type patterns and null handling in switches for concise logic.
     * It eliminates casting and instanceof checks, improving readability over traditional switches.
     *
     * @param obj
     * @return
     */
   private static String formatter(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case null -> "Null value";
            default -> "Unknown: " + obj.getClass();
        };
    }

}
