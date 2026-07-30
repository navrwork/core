package com.navr.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    public static void main(String[] args) {
        patternMatcher1();
        patternMatcher2();
        patternMatcher3();
        patternMatcher4();
        replaceWithPatternMatcher("hello friends. Let's  play  now.");

        groupRegex();
        groupBackreference();
    }

    private static void patternMatcher1() {
        String regex = "abc";
        String input1 = "hello abc!";
        String input2 = "abc";
        System.out.printf("%npatternMatcher1: regex=%s, input1=%s, input2=%s%n", regex, input1, input2);
        Pattern pat = Pattern.compile(regex);
        boolean find1 = pat.matcher(input1).find(); // true
        boolean find2 = pat.matcher(input1).find(); // true
        boolean matches1 = pat.matcher(input1).matches(); // FALSE
        boolean matches2 = pat.matcher(input2).matches(); // true
        System.out.printf("find1=%b, find2=%b%n", find1, find2);
        System.out.printf("matches1=%b, matches2=%b%n", matches1, matches2);
    }

    private static void patternMatcher2() {
        String regex = "abc";
        String input = "Hi abc. Hello abc!!";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(input);
        System.out.printf("%npatternMatcher2: regex=%s, input=%s%n", regex, input);

        while (mat.find()) {
            int startIndex = mat.start();
            int endIndex = mat.end();
            String subSeq = mat.group();
            System.out.printf("startIndex=%d, endIndex=%d, subSeq=%s%n", startIndex, endIndex, subSeq);
        }
    }

    private static void patternMatcher3() {
        String regex = "a.c";
        String input = "Hi abc. Hello abc!!";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(input);
        System.out.printf("%npatternMatcher3: regex=%s, input=%s%n", regex, input);

        while (mat.find()) {
            int startIndex = mat.start();
            int endIndex = mat.end();
            String subSeq = mat.group();
            System.out.printf("startIndex=%d, endIndex=%d, subSeq=%s%n", startIndex, endIndex, subSeq);
        }
    }

    private static void patternMatcher4() {
        String regex1 = "(\\s+)";
        String regex2 = "(\\w+)";
        String input = "Hello. I'm an apple. That's a banana.";

        System.out.printf("%npatternMatcher4: regex1=%s, input=%s%n", regex1, input);

        Pattern p = Pattern.compile(regex1);
        Matcher m = p.matcher(input);
        while (m.find()) {
            int groupCount = m.groupCount();
            String grp0 = m.group(0);
            String grp1 = m.group(1);
            System.out.printf("Match found. grp0=%s, grp1=%s, groupCount=%d%n", grp0, grp1, groupCount);
        }

        p = Pattern.compile(regex2);
        m = p.matcher(input);
        while (m.find()) {
            int groupCount = m.groupCount();
            String grp0 = m.group(0);
            String grp1 = m.group(1);
            System.out.printf("Match found. grp0=%s, grp1=%s, groupCount=%d%n", grp0, grp1, groupCount);
        }

    }

    private static String replaceWithPatternMatcher(String inStr) {
        String outStr = null;
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(inStr);
        outStr = m.replaceAll("\t");
        System.out.printf("%nreplaceWithPatternMatcher: inStr=%s, outStr=%s%n", inStr, outStr);

        return outStr;
    }

    /**
     * This method fetches a number value, of any size, from the given string.
     */
    private static void groupRegex() {
        String regex = "(D*)(\\d{1,})(.*)";
        String s1 = "this is a test number: 1234567890.";
        String s2 = "the secret code is 100200. keep it safe.";
        String s3 = "1999 is my birth year";

        System.out.printf("%ngroupRegex: %n");
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s1);
        if (m.find()) {
            String group1 = m.group(1);
            String group2 = m.group(2); // number value caught here
            String group3 = m.group(3);
            System.out.printf("s1=%s, group1=%s, group2=%s, group3=%s%n", s1, group1, group2, group3);
        }

        m = p.matcher(s2);
        if (m.find()) {
            String group1 = m.group(1);
            String group2 = m.group(2); // number value caught here
            String group3 = m.group(3);
            System.out.printf("s2=%s, group1=%s, group2=%s, group3=%s%n", s2, group1, group2, group3);
        }

        m = p.matcher(s3);
        if (m.find()) {
            String group1 = m.group(1);
            String group2 = m.group(2); // number value caught here
            String group3 = m.group(3);
            System.out.printf("s3=%s, group1=%s, group2=%s, group3=%s%n", s3, group1, group2, group3);
        }
    }

    /**
     * A backreference is specified in the regular expression as a backslash (\)
     * followed by a digit indicating the number of the group to be recalled.
     *
     */
    private static void groupBackreference() {
        String regex = "(\\d\\d)\\1";
        System.out.printf("%ngroupBackreference: %n");

        String s1 = "1212";
        String s2 = "1234";
        boolean s1Matches = s1.matches(regex);
        boolean s2Matches = s2.matches(regex);
        System.out.printf("s1=%s, s1Matches=%b%n", s1, s1Matches);
        System.out.printf("s2=%s, s2Matches=%b%n", s2, s2Matches);
    }
}
