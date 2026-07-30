package com.navr.learn.printf;

import java.util.Scanner;

public class PrintFormatMain {

    public static void main(String[] args) {
        format1();

    }

    /**
     * Problem statement:
     * Input: Every line of input will contain a String followed by an integer.
     * Output: The first column contains the String and is left justified using exactly 15 characters.
     * The second column contains the integer, expressed in exactly  digits;
     * if the original input has less than three digits, you must pad your output's
     * leading digits with zeroes.
     *
     * Input:
     * java
     * 100
     *
     * Output:
     * java           100
     *
     */
    private static void format1() {
        Scanner sc=new Scanner(System.in);
        System.out.println("================================");
        for(int i=0;i<3;i++) {
            String s1=sc.next();
            int x=sc.nextInt();

            System.out.printf("%-15s%03d%n", s1, x);
        }
        System.out.println("================================");
    }
}
