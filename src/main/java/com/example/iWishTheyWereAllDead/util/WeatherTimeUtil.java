package com.example.iWishTheyWereAllDead.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherTimeUtil {

    public static String getBaseDate() {
        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();

        // 30분 이전이면 1시간 전으로 조정
        if (minute < 30) {
            now = now.minusHours(1);
        }

        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String getBaseTime() {
        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();

        if (minute < 30) {
            now = now.minusHours(1);
            return String.format("%02d%02d", now.getHour(), 30);
        } else {
            return String.format("%02d%02d", now.getHour(), 0);
        }
    }
}
