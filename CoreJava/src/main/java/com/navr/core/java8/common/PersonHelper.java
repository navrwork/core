package com.navr.core.java8.common;

import java.util.Arrays;
import java.util.List;

public class PersonHelper {

    public static List<Person> getPersonList() {
        Person p1 = new Person("Mahatma", "Gandhi", "New Delhi", 120);
        Person p2 = new Person("Rajiv", "Gandhi", "New Delhi", 90);
        Person p3 = new Person("Rahul", "Gandhi", "New Delhi", 50);
        Person p4 = new Person("Shahrukh", "Khan", "Mumbai", 55);
        Person p5 = new Person("Aamir", "Khan", "Mumbai", 56);
        Person p6 = new Person("Salman", "Khan", "Mumbai", 54);
        Person p7 = new Person("SSS", "Shetty", "Hyderabad", 54);
        Person p8 = new Person("RRR", "Reddy", "Hyderabad", 54);

        return Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8);
    }
}
