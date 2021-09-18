/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarCommands extends AbstractCommandComponent {

    public Calendar getNow() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow;
    }
//need this due to Calendar.get(Calendar.MONTH) return 0-11
    public int getMonth(Calendar c) {
        return c.get(Calendar.MONTH) + 1;
    }

    public int getMonth() {
        return getMonth(getNow());
    }

    public int getYear(Calendar c) {
        return c.get(Calendar.YEAR);
    }

    public int getYear() {
        return getYear(getNow());
    }

    public Calendar getDateOnly(Calendar d) {
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

    public String getISO8601FormattedNow() {
        return super.getFormControl().cmd.lang.formatCalendar(
                getNow(), getISO8601DateTimeFormat());
    }

    public String getISO8601DateTimeFormat() {
        return EngineConfiguration.ISO8601_DATETIME_FORMAT;
    }

//    public String formatCalendar(Calendar d, String mask) {
//        SimpleDateFormat temp = new SimpleDateFormat(mask);
//        return temp.format(d.getTime()).trim();
//    }
    public Calendar long2Calendar(long i) {
        Calendar d = getNow();
        d.setTimeInMillis(i);
        return d;
    }

    public Calendar getCalendar(String date, String mask) {
        try {
            SimpleDateFormat temp = new SimpleDateFormat(mask);
            java.util.Date d = temp.parse(date);
            Calendar c1 = getNow();
            c1.setTime(d);
            return c1;
        } catch (java.text.ParseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Calendar Date2Calendar(java.util.Date d) {
        Calendar c1 = getNow();
        c1.setTime(d);
        return c1;
    }

    public Calendar Date2Calendar(java.sql.Date d) {
        Calendar c1 = getNow();
        c1.setTime(d);
        return c1;
    }

    public Calendar Timestamp2Calendar(java.sql.Timestamp t) {
        Calendar c1 = getNow();
        c1.setTime(t);
        return c1;
    }

    public Calendar Time2Calendar(java.sql.Time t) {
        Calendar c1 = getNow();
        c1.setTime(t);
        return c1;
    }
}
