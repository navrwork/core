package com.navr.core.java8;

/**
 *
 * The Anonymous Inner Class code compiles and runs successfully as long as
 * the local variable in the enclosing scope is either a final variable or assigned value only once.
 *
 */
public class AnonymousClassAndLocalVar {

    public static void main(String[] args) {
        final int finalVar = 10; // final variable
        int effFinalVar = 20; // effectively final variable. value assigned only once.
        int anotherVar = 30; // not effectively final variable as value assigned more than once.
        anotherVar++;
        MyInterface myObj1 = new MyInterface() {
            @Override
            public void print() {
                System.out.println("MyInterface: override print()");

                //
                // Ok. Local variable is final.
                //
                System.out.printf("MyInterface: finalVar=%d%n", finalVar);

                //
                // Ok. Local variable is assigned value only once.
                //
                System.out.printf("MyInterface: effFinalVar=%d%n", effFinalVar);

                //
                // NOT Ok. Compilation Error. Local variable is assigned value more than once.
                //
                // java: local variables referenced from an inner class must be final or effectively final
                //
//                System.out.printf("MyInterface: effFinalVar=%d%n", anotherVar); // NOT Ok
            }
        };
        myObj1.print();
    }
}

interface MyInterface {
    default void print() {
        System.out.println("MyInterface: default print()");
    }
}