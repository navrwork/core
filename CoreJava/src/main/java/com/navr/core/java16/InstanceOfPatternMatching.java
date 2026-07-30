package com.navr.core.java16;

public class InstanceOfPatternMatching {
    public static void main(String[] args) {
        myMethod("abc");
    }

    private static void myMethod(Object obj) {
        // traditional way
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.printf("instanceof traditional: s=%s%n", s);
        }

        // new way
        if (obj instanceof String s) {
            System.out.printf("instanceof pattern matching: s=%s%n", s);
        }
    }

}
