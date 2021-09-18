/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.conf;


/**
 *
 * @author Ng Siak Hooi
 */
public class RESOURCE {

    private static String RESOURCE_PATH =
            "/com/mysoftwarehouse/is/resources/";
    public static String DEFAULT_EULA_FILE = RESOURCE_PATH + "eula.txt";
//    public static String BSAH0REG_HTML_FILE = RESOURCE_PATH + "";
////    //
    public static String ISBH0WHS_EN_US_HTML_FILE= RESOURCE_PATH + "ISBH0WHS_en_US.htm";
    public static String ISBH0WHS_MS_MY_HTML_FILE= RESOURCE_PATH + "ISBH0WHS_ms_MY.htm";
//
    public static String IMG_800_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=is1";
    public static String IMG_800_FILE_D = RESOURCE_PATH + "img800D.gif";
    public static String IMG_800_FILE_L = RESOURCE_PATH + "img800L.gif";
////    
    public static String IMG_640_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=is1";
    public static String IMG_640_FILE_D = RESOURCE_PATH + "img640D.gif";
    public static String IMG_640_FILE_L = RESOURCE_PATH + "img640L.gif";
////    
    private static String SQL_100_PATH = RESOURCE_PATH + "sql100/";
    public static String[] DATABASE_100_FILES = new String[]{
        SQL_100_PATH + "iswhs.sql",
        SQL_100_PATH + "iswhsh.sql",
        SQL_100_PATH + "isloc.sql",
        SQL_100_PATH + "isloch.sql",
        SQL_100_PATH + "isitm.sql",
        SQL_100_PATH + "isitmh.sql",
        SQL_100_PATH + "isuom.sql",
        SQL_100_PATH + "isuomh.sql",
        SQL_100_PATH + "istcd.sql",
        SQL_100_PATH + "istcdh.sql",
        SQL_100_PATH + "istxn.sql",
        SQL_100_PATH + "issys.sql"
    };
//    private static String SQL_101_PATH = RESOURCE_PATH + "sql101/";
//    public static String[] DATABASE_101_FILES = new String[]{
//        SQL_101_PATH + "bscmps.sql",
//        SQL_101_PATH + "bscmpsh.sql",
//        SQL_101_PATH + "bsinv.sql",
//        SQL_101_PATH + "bspi.sql",
//        SQL_101_PATH + "bspiv.sql",
//        SQL_101_PATH + "bspor.sql",
//        SQL_101_PATH + "bsqtt.sql",
//        SQL_101_PATH + "bsrcp.sql",
//        SQL_101_PATH + "bssys.sql"
//    };
//    //
    public static String MENU_WEBSITE_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=iswt";
    public static String MENU_FORUM_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=isfr";
    public static String MENU_WHATNEW_URL =
            "http://www.mysoftwarehouse.com/showpage.php?pg=iswn";
}
