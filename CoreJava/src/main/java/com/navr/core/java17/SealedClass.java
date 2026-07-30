package com.navr.core.java17;


sealed class Shape permits Circle, Square, Triangle {
    void draw() {
        System.out.println("Shape: Inside draw()");
    }
}

final class Circle extends Shape {
    // final subclass
}

sealed class Triangle extends Shape permits RightAngledTriangle {
    // sealed subclass
}

final class RightAngledTriangle extends Triangle {

}

non-sealed class Square extends Shape {
    // non-sealed subclass: open for extension, like a normal class
}

class MySquare extends Square {
    // Another class that extends non-sealed subclass, without any restriction
}

//
// Compilation error: 'SomeRandomClass' is not allowed in the sealed hierarchy
//
//class SomeRandomClass extends Shape {
//
//}

/**
 * <pre>
 * When defining subclasses for a sealed class in Java, each subclass must explicitly declare how it will continue the sealing initiated by its superclass. This is done using one of three modifiers: final, sealed, or non-sealed.
 * final:
 * This modifier prevents any further extension of the subclass. It effectively terminates the inheritance chain at this point.
 * sealed:
 * This modifier allows for controlled extension of the subclass, but only by explicitly permitted subclasses declared within its own permits clause. This maintains the restricted inheritance model of sealed classes.
 * non-sealed:
 * This modifier removes the sealing restriction from the subclass, allowing it to be extended by any other class, even those not known at compile time. This effectively reverts to traditional open inheritance for this branch of the hierarchy.
 *
 * </pre>
 */
public class SealedClass {
    public static void main(String[] args) {

    }
}
