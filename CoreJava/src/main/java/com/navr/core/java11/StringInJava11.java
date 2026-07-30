package com.navr.core.java11;

import java.util.Arrays;
import java.util.List;

public class StringInJava11 {

    public static void main(String[] args) {
        newStringMethods();
    }

    private static void newStringMethods() {
        String s1 = " abcd ";
        System.out.printf("s1: '%s'%n", s1);
        System.out.printf("s1.strip(): '%s'%n", s1.strip());
        System.out.printf("s1.stripLeading(): '%s'%n", s1.stripLeading());
        System.out.printf("s1.stripTrailing(): '%s'%n", s1.stripTrailing());

        List<String> strList = Arrays.asList("aaa", "bbb");
        System.out.printf("srtList=%s%n", strList);
        StringBuffer sb = new StringBuffer("");
//        strList.stream().forEach(s -> { sb.append(s); } );
        strList.stream().forEach(sb::append);
        System.out.printf("sb=%s%n", sb);
    }
}
