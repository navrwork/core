package com.navr.core.java14;

public class SwitchExpr {
    public static void main(String[] args) {
        DAY_OF_WEEK dow = DAY_OF_WEEK.SUNDAY;

        String result = switch (dow) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "weekday";
            default -> "weekend";
        };
        System.out.printf("SwitchExpr: dow=%s, result=%s %n", dow, result);
    }
}

enum DAY_OF_WEEK {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
