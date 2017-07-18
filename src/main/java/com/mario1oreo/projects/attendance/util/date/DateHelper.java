package com.mario1oreo.projects.attendance.util.date;

import java.time.format.DateTimeFormatter;

/**
 * Created by mario1oreo on 2017/6/27 0:06.
 */
public class DateHelper {

    private DateHelper() {
    }

    public static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");



}
