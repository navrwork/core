package com.navr.core.java8;

interface X {
    default void print() {
        System.out.println("X.print() invoked. ");
    }
}

interface Y {
    default void print() {
        System.out.println("Y.print() invoked. ");
    }
}

class XYImpl implements X, Y { // Diamond problem due to default methods in underlying interfaces
    @Override
    public void print() { // method override needed to solve the Diamond problem
        X.super.print();
        System.out.println("XYImpl.print() invoked. ");
    }
}

public class DefaultMethodAndDiamondProblem {
    public static void main(String[] args) {
        XYImpl xyObj = new XYImpl();
        xyObj.print();
    }
}