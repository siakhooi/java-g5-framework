/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author SHNG
 */
public class G6Calendar {

    public final static String ISO8601_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Calendar getNow() {
        return Calendar.getInstance();
    }

    public static int getMonth() {
        return getMonth(getNow());
    }

    public static int getYear() {
        return getYear(getNow());
    }

    public static int getDay() {
        return getDay(getNow());
    }
//need this due to Calendar.get(Calendar.MONTH) return 0-11

    public static int getMonth(Calendar c) {
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Calendar c) {
        return c.get(Calendar.YEAR);
    }

    public static int getDay(Calendar c) {
        return c.get(Calendar.DATE);
    }

    public static Calendar getDateOnly(Calendar d) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(d.get(Calendar.YEAR),
                d.get(Calendar.MONTH),
                d.get(Calendar.DAY_OF_MONTH));
        return c;
    }

    public static Calendar copy(Calendar d) {
        Calendar i = Calendar.getInstance();
        i.setTimeInMillis(d.getTimeInMillis());
        return i;
    }

    public static Calendar getCalendar(long i) {
        Calendar d = getNow();
        d.setTimeInMillis(i);
        return d;
    }

    public static Calendar getCalendar(java.util.Date d) {
        Calendar c1 = getNow();
        c1.setTime(d);
        return c1;
    }

    public static Calendar getCalendar(java.sql.Date d) {
        Calendar c1 = getNow();
        c1.setTime(d);
        return c1;
    }

    public static Calendar getCalendar(java.sql.Timestamp t) {
        Calendar c1 = getNow();
        c1.setTime(t);
        return c1;
    }

    public static Calendar getCalendar(java.sql.Time t) {
        Calendar c1 = getNow();
        c1.setTime(t);
        return c1;
    }

    public static Calendar getCalendar(String date, String mask) throws ParseException {
        SimpleDateFormat temp = new SimpleDateFormat(mask);
        java.util.Date d = temp.parse(date);
        Calendar c1 = getNow();
        c1.setTime(d);
        return c1;
    }

    public static String getText(Calendar d, String format) {
        SimpleDateFormat temp = new SimpleDateFormat(format);
        return temp.format(d.getTime()).trim();
    }

    public static String getText(Calendar d) {
        SimpleDateFormat temp = new SimpleDateFormat(ISO8601_DATETIME_FORMAT);
        return temp.format(d.getTime()).trim();
    }
}
