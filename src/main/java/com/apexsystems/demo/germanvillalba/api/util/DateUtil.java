package com.apexsystems.demo.germanvillalba.api.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static int getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }
}
