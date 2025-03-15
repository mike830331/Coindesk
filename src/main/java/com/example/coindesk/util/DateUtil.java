package com.example.coindesk.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String formatToCustomTime(String updatedISO) {
        return ZonedDateTime.parse(updatedISO, DateTimeFormatter.ISO_ZONED_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}