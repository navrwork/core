package com.navr.core.java10;

import java.util.ArrayList;

public class LocalVar {
    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>(); // without local var
        list1.add("AA");
        list1.add("BB");
        System.out.printf("LocalVar: list1=%s%n", list1);

        var list2 = new ArrayList<String>(); // local var type inference
        list2.add("YY");
        list2.add("ZZ");
        System.out.printf("LocalVar: list2=%s%n", list2);
    }
}
