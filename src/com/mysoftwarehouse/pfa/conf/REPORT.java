/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.conf;

/**
 *
 * @author Ng Siak Hooi
 */
public class REPORT {
    //
    public static String REPORT_PATH =
            "/com/mysoftwarehouse/pfa/reports/";
    public static String RESOURCEBUNDLENAME = 
            "com.mysoftwarehouse.pfa.resources.lang.report";
//$P{Report.Title}
//$P{Report.Subtitle}
//$P{Report.PrintDate}
//$P{Report.PrintBy}
    public static String USER_ID = "Report.PrintBy";
    public static String PRINT_DATE = "Report.PrintDate";
    public static String REPORT_TITLE = "Report.Title";
    public static String REPORT_SUBTITLE = "Report.Subtitle";
    //
}
