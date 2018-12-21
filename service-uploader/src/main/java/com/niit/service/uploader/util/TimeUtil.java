package com.niit.service.uploader.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {
    public static String year() {
        return LocalDate.now().getYear() + "";
    }

    public static String month() {
        return LocalDate.now().getMonthValue() + "";
    }

    public static String day() {
        return LocalDate.now().getDayOfMonth() + "";
    }

    public static String hour() {
        return LocalDateTime.now().getHour() + "";
    }
}
