package com.navr.learn.basics.string;

public class StringDemo {

    public static void main(String[] args) {
        string1();
    }

    private static void string1() {
        equalsOperator();
    }

    /**
     * Equality operator is represented by '==' operator'.
     * Reference equality occurs when two references point to the same object in the memory.
     * <br/>
     * When we use the equality operator with primitive types, we’re just comparing their values.
     * As for object types in Java, the equality operator performs a referential
     * equality comparison only, ignoring the object values.
     */
    private static void equalsOperator() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        String s4 = new String("hello").intern();

        System.out.printf("String s1=\"%s\"%nString s2=\"%s\"%nString s3=new String(\"%s\")%nString s4=new String(\"%s\").intern() %n%n", s1, s2, s3, s4);
        System.out.printf("s1==s2->%b, s1==s3->%b, s1==s4->%b%n", s1 == s2, s1 == s3, s1 == s4);
        System.out.printf("s2==s3->%b, s2==s4->%b%n", s2 == s3, s2 == s4);
        System.out.printf("s3==s4->%b%n", s3 == s4);

        //
    }
}
