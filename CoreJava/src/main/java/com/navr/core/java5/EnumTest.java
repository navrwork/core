package com.navr.core.java5;

public class EnumTest {
    public static void main(String[] args) {
        for (Season s : Season.values()) {
            System.out.printf("Season=%s ", s);
        }
    }
}

enum Season {
    WINTER, SPRING, SUMMER, FALL
}

