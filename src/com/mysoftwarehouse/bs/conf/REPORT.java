/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.conf;

/**
 *
 * @author Ng Siak Hooi
 */
public class REPORT {
    //
    public static String REPORT_PATH =
            "/com/mysoftwarehouse/bs/reports/";
    public static String RESOURCEBUNDLENAME =
            "com.mysoftwarehouse.bs.resources.lang.report";
    //
    public static String SHOW_DEMO = "SHOW_DEMO"; //Boolean type
    public static String CURCDE = "CMP.CurCde";
    public static String CMPS_SIGN1 = "CMPS.Sign1";
    public static String CMPS_SIGN2 = "CMPS.Sign2";
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
