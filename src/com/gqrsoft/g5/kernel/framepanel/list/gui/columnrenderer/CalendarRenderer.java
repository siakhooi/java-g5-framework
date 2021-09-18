/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarRenderer extends AbstractRenderer {

    private String value = "";

    @Override
    protected String format(AbstractRenderer comp,Field f, Object text) {
        String mask = f.outputMask;
        if (mask == null) {
            mask = EngineConfiguration.List.DEFAULT_CALENDAR_OUTPUT_FORMAT;
        } else if (mask.equals("")) {
            mask = EngineConfiguration.List.DEFAULT_CALENDAR_OUTPUT_FORMAT;
        }
        Calendar c = null;
        if (text == null) {
        } else if (text instanceof Calendar) {
            c = (Calendar) text;
        } else if (text instanceof java.util.Date) {
            java.util.Date d = (java.util.Date) text;
            c = formControl.cmd.cal.getNow();
            c.setTime(d);
        } else if (text instanceof java.sql.Date) {
            java.sql.Date d = (java.sql.Date) text;
            c = formControl.cmd.cal.getNow();
            c.setTime(d);
        } else if (text instanceof java.sql.Time) {
            java.sql.Time d = (java.sql.Time) text;
            c = formControl.cmd.cal.getNow();
            c.setTime(d);
        } else if (text instanceof java.sql.Timestamp) {
            java.sql.Timestamp d = (java.sql.Timestamp) text;
            c = formControl.cmd.cal.getNow();
            c.setTime(d);
        } else if (text instanceof Number) {
            Number d = (Number) text;
            c = formControl.cmd.cal.getNow();
            c.setTimeInMillis(d.longValue());
        }
        if (c != null) {
            value = formControl.cmd.lang.formatCalendar(c, mask);
        }
        return value;
    }
}
