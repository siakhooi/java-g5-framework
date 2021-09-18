/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.conf;

/**
 *
 * @author Ng Siak Hooi
 */
public class RESOURCE {

    private static String RESOURCE_PATH =
            "/com/mysoftwarehouse/bs/resources/";
    public static String DEFAULT_EULA_FILE = RESOURCE_PATH + "eula.txt";
    public static String BSAH0REG_HTML_FILE = RESOURCE_PATH + "";
//    //
    public static String BSBH0CMP_EN_US_HTML_FILE= RESOURCE_PATH + "BSBH0CMP_en_US.htm";
    public static String BSBH0CMP_MS_MY_HTML_FILE= RESOURCE_PATH + "BSBH0CMP_ms_MY.htm";

    public static String IMG_800_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=bs1";
    public static String IMG_800_FILE_D = RESOURCE_PATH + "img800D.gif";
    public static String IMG_800_FILE_L = RESOURCE_PATH + "img800L.gif";
//    
    public static String IMG_640_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=bs1";
    public static String IMG_640_FILE_D = RESOURCE_PATH + "img640D.gif";
    public static String IMG_640_FILE_L = RESOURCE_PATH + "img640L.gif";
//    
    private static String SQL_100_PATH = RESOURCE_PATH + "sql100/";
    public static String[] DATABASE_100_FILES = new String[]{
        SQL_100_PATH + "bscmp.sql",
        SQL_100_PATH + "bscmph.sql",
        SQL_100_PATH + "bscus.sql",
        SQL_100_PATH + "bscush.sql",
        SQL_100_PATH + "bsinv.sql",
        SQL_100_PATH + "bspi.sql",
        SQL_100_PATH + "bspih.sql",
        SQL_100_PATH + "bspiv.sql",
        SQL_100_PATH + "bspor.sql",
        SQL_100_PATH + "bspsi.sql",
        SQL_100_PATH + "bspy.sql",
        SQL_100_PATH + "bsqtt.sql",
        SQL_100_PATH + "bsrcp.sql",
        SQL_100_PATH + "bssa.sql",
        SQL_100_PATH + "bssi.sql",
        SQL_100_PATH + "bssih.sql",
        SQL_100_PATH + "bsst.sql",
        SQL_100_PATH + "bssup.sql",
        SQL_100_PATH + "bssuph.sql",
        SQL_100_PATH + "bssy.sql",
        SQL_100_PATH + "bssys.sql"
    };
    private static String SQL_101_PATH = RESOURCE_PATH + "sql101/";
    public static String[] DATABASE_101_FILES = new String[]{
        SQL_101_PATH + "bscmps.sql",
        SQL_101_PATH + "bscmpsh.sql",
        SQL_101_PATH + "bsinv.sql",
        SQL_101_PATH + "bspi.sql",
        SQL_101_PATH + "bspiv.sql",
        SQL_101_PATH + "bspor.sql",
        SQL_101_PATH + "bsqtt.sql",
        SQL_101_PATH + "bsrcp.sql",
        SQL_101_PATH + "bssys.sql"
    };
    //
    public static String MENU_WEBSITE_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=bswt";
    public static String MENU_FORUM_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=bsfr";
    public static String MENU_WHATNEW_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=bswn";
}
