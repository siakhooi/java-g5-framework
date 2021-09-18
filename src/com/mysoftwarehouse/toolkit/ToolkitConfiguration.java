/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit;

/**
 *
 * @author Ng Siak Hooi
 */
public class ToolkitConfiguration {

    public static String TOOLKIT_TITLE="G5 Toolkit version 1.0.0.0";
    public static String getLogoURL() {
        return "/com/mysoftwarehouse/toolkit/resources/images/logo.jpg";
    }
    public static String VENDOR_DIRECTORY = "mysoftwarehouse";
    public static String APPLICATION_DIRECTORY = "toolkit";
    public static String DATABASE_NAME = "toolkitdb";
    //
    public static String DATABASE_CONNECTION_CODE = "hsql";
    public static String DATABASE_USERNAME = "sa";
    public static String DATABASE_PASSWORD = "";
    public static int TOTAL_MENU_ITEM = 5;
    public static String COMMAND_LINE_SYNTAX = "com.mysoftwarehouse.toolkit.Main";
    //
    public static int HELP_COLUMNS=80;
    public static int HELP_ROWS=12;
    //
    public static String APPLICATION_LOG_FILE="toolkit.log";
    public static String SYSTEM_LOG_FILE="system.log";
   
}
