package com.navr.core.java21;

record Location (int x, int y) {}

public class RecordPatterns {
    public static void main(String[] args) {
        Location l1 = new Location(10, 20);
        recordPatterns(l1);
    }

    private static void recordPatterns(Object o) {
        if (o instanceof Location(int x, int y)) {
            System.out.printf("recordPatterns: Location(%d, %d)", x, y);
        }
    }

}
