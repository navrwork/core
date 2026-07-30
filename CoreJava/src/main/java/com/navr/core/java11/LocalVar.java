package com.navr.core.java11;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 * <b>!! Note !!</b><br/>
 * Local Variable Type Inference feature was part of <b><u>Java 10</u></b> release.
 * <br/> <br/>
 *
 * <b><u>Feature details:</b></u>
 * <ul>
 * <li>Local Variable Type Inference is one of the most evident change to language available from Java 10 onwards. </li>
 * <li>It allows to define a variable using var and without specifying the type of it. </li>
 * <li>The compiler infers the type of the variable using the value provided. </li>
 * <li>Note that this feature is available only for local variables with the initializer. </li>
 * <li>It cannot be used for member variables, method parameters, return types, etc. – the initializer is required as without which compiler won’t be able to infer the type.</li>
 * <li>This enhancement helps in reducing the boilerplate code</li>
 * </ul>
 * <br/>
 *
 * <b><u>Points to note:</u></b>
 * <ul>
 * <li>No type inference in case of member variable, method parameters, return values.</li>
 * <li>Local variable should be initialized at time of declaration otherwise compiler will not be able to infer and will throw error.</li>
 * <li>Local variable inference is available inside initialization block of loop statements.</li>
 * <li>No runtime overhead. As compiler infers the type based on value provided, there is no performance loss.</li>
 * <li>No dynamic type change. Once type of local variable is inferred it cannot be changed.</li>
 * <li>Complex boilerplate code can be reduced using local variable type inference.</li>
 * <li>Note that var is not a keyword – this ensures backward compatibility for programs using var say, as a function or variable name. var is a reserved type name, just like int.</li>
 * </ul>
 * <br/>
 *
 * <b><u>Reference:</u></b><br/>
 * <ul>
 * <li>https://www.baeldung.com/java-10-local-variable-type-inference </li>
 * <li>http://openjdk.java.net/projects/amber/guides/lvti-style-guide</li>
 * </ul>
 */
public class LocalVar {
    public static void main(String[] args) {
        localVar();
    }

    private static void localVar() {

        // Local var and primitives
        //
        // GOOD
        var ready = true; // boolean
        var ch = '\ufffd'; // char
        var sum = 0L; // long
        var label = "wombat"; // String
        var f = 1.0f; // float
        var d = 2.0; // double

        //
        // Care should be taken when the initializer is a numeric value, especially an integer literal.
        // With an explicit type on the left-hand side, the numeric value may be silently widened or narrowed
        // to types other than int. With var, the value will be inferred as an int, which may be unintended.
        //
        // DANGEROUS: all infer as int
        var flags = 0; // Original: byte flags
        var mask = 0x7fff; // Original: short mask
        var base = 17; // Original: long base


        String s1 = "Hello World!"; // traditional way of declaring local variable
        // new way of declaring local variable
        // the compiler infers the type of name variable as String by inspecting the value provided.
        var helloWorldStr = "Hello World !!";
        var idToNameMap = new HashMap<Integer, String>();

        //
        // Incorrect use of 'var'
        //
//        var x = null; // error: cannot use 'var' on variable without initializer
//        var emptyList = null; // error: variable initializer is 'null'
//        var arr1 = { 1, 2, 3 }; // error: array initializer needs an explicit target-type
        var intArray = new int[]{1, 2, 3}; // Ok here. explicit target-type added.

        //
        // lambda expression: needs an explicit target-type
        //
//        var p1 = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type
        var strLenCheck = (Predicate<String>) (String s) -> s.length() > 3; // Ok. explicit target-type present.
        System.out.printf("p2.test('hello')=%s%n", strLenCheck.test("hello")); // returns 'true'
        System.out.printf("p2.test('he')=%s%n", strLenCheck.test("he")); // returns 'false'
    }
}
