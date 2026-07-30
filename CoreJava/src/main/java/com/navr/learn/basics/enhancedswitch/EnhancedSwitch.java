package com.navr.learn.basics.enhancedswitch;

public class EnhancedSwitch {
    public static void main(String[] args) {
        int daysV1 = daysInMonthV1(2, 2020);
        int daysV2 = daysInMonthV2(2, 2020);
        System.out.printf("Days in February 2020 (V1): %d%n", daysV1);
        System.out.printf("Days in February 2020 (V2): %d%n", daysV2);
    }

    /**
     * Use the traditional Switch statement to return the number of days in a month.
     *
     * @param month
     * @param year
     * @return
     */
    private static int daysInMonthV1(int month, int year) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                return (year % 4 == 0) ? 29 : 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                throw new IllegalArgumentException("Invalid month");
        }
    }

    /**
     *
     * Use the enhanced switch expression to return the number of days in a month.
     * This is a more concise and readable way to write the same logic as above.
     *
     * @param month
     * @param year
     * @return
     */
    private static int daysInMonthV2(int month, int year) {
        int daysInMonth = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (year % 4 == 0) ? 29 : 28;
            default -> throw new IllegalArgumentException("Invalid month");
        };
        System.out.printf("daysInMonthV2: month=%d, year=%d, daysInMonth=%d%n", month, year, daysInMonth);
        return daysInMonth;

        //
        // The enhanced switch expression can be used directly in return statement as well
        //
//        return switch (month) {
//            case 1, 3, 5, 7, 8, 10, 12 -> 31;
//            case 4, 6, 9, 11 -> 30;
//            case 2 -> (year % 4 == 0) ? 29 : 28;
//            default -> throw new IllegalArgumentException("Invalid month");
//        };
    }
}
