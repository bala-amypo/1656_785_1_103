package com.example.demo.util;
import java.time.Duration;
import java.time.LocalTime;
public class TimeUtils {
    public static long minutesBetween(LocalTime a, LocalTime b){ return Duration.between(a,b).toMinutes(); }
}
