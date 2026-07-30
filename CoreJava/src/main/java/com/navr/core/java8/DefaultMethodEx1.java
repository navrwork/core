package com.navr.core.java8;

interface A {
    void perform();
    default void print() {
        System.out.println("A.print() default method invoked. ");
    }
}

class AImplV1 implements A {

    @Override
    public void perform() {
        System.out.println("AImplV1.perform() invoked. ");
    }
}

class AImplV2 implements A {
    @Override
    public void perform() {
        System.out.println("AImplV2.perform() invoked. ");
    }

    @Override
    public void print() {
        A.super.print();
        System.out.println("AImplV2.print() invoked.");
    }
}

public class DefaultMethodEx1 {
    public static void main(String[] args) {
        A objV1 = new AImplV1();
        objV1.perform();
        objV1.print();

        A objV2 = new AImplV2();
        objV2.perform();
        objV2.print();
    }
}