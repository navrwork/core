package com.navr.core.java11;

/**
 * <pre>
 *
 * A single-file program is one where the program fits in a single source file.
 * Starting in Java 11, we can use the following command to execute a single-file program:
 * <i>$ java HelloWorld.java</i>
 * <i>$ java Addition.java 1 2 3</i>
 *
 * </pre>
 */
public class SingleFileProgram {

    public static void main(String[] args) {
        System.out.println("SingleFileProgram --> Hello, World!");
        String s = "hello";
        System.out.println(s.toUpperCase());
    }
}
