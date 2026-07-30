package com.navr.core.concepts.time;

import java.time.*;

public class JavaTimeDemo {
    public static void main(String[] args) {
        demoInstant();
        demoLocalTime();
        demoLocalDate();
        demoLocalDateTime();
        demoZonedDateTime();
    }

    private static void demoInstant() {
        System.out.printf("=== Instant Demo ===%n");
        Instant now = Instant.now();
        System.out.printf("Current Instant: %s%n", now);
        Instant later = now.plusSeconds(3600); // Add 1 hour
        System.out.printf("Instant after 1 hour: %s%n", later);
    }

    private static void demoLocalTime() {
        System.out.printf("=== LocalTime Demo ===%n");
        LocalTime now = LocalTime.now();
        System.out.printf("Current LocalTime: %s%n", now);
        LocalTime later = now.plusHours(1);
        System.out.printf("LocalTime after 1 hour: %s%n", later);
    }

    private static void demoLocalDate() {
        System.out.printf("=== LocalDate Demo ===%n");
        LocalDate now = LocalDate.now();
        System.out.printf("Current LocalDate: %s%n", now);
        LocalDate later = now.plusDays(30);
        System.out.printf("LocalDate after 30 days: %s%n", later);
    }

    private static void demoLocalDateTime() {
        System.out.printf("=== LocalDateTime Demo ===%n");
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Current LocalDateTime: %s%n", now);
        LocalDateTime later = now.plusHours(5);
        System.out.printf("LocalDateTime after 5 hours: %s%n", later);
    }

    private static void demoZonedDateTime() {
        System.out.printf("=== ZonedDateTime Demo (GMT -> IST) ===%n");
        ZoneId gmt = ZoneId.of("GMT");
        ZoneId ist = ZoneId.of("Asia/Kolkata");

        ZonedDateTime istNow = ZonedDateTime.now(ist);
        System.out.printf("Current ZonedDateTime (IST): %s%n", istNow);

        ZonedDateTime gmtNow = istNow.withZoneSameInstant(gmt);
        System.out.printf("Corresponding ZonedDateTime (GMT): %s%n", gmtNow);

        ZonedDateTime gmtLater = gmtNow.plusHours(6);
        System.out.printf("ZonedDateTime (GMT) after 6 hours: %s%n", gmtLater);
        System.out.printf("Corresponding IST after 6 hours: %s%n", gmtLater.withZoneSameInstant(ist));
    }

}
