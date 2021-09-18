/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.util;

import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarTool {

    public static Calendar getDate(Calendar d) {
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
}
