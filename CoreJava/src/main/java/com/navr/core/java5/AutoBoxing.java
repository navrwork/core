package com.navr.core.java5;

public class AutoBoxing {
    public static void main(String[] args) {
        Integer intObj = 10; // auto-boxing here
        System.out.printf("AutoBoxing: intObj=%d%n", intObj);

        int i = intObj; // auto-unboxing here
        System.out.printf("AutoBoxing: intObj=%d, i=%d %n", intObj, i);
    }
}
