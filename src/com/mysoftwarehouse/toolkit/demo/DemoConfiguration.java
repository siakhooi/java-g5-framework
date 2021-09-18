/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class DemoConfiguration {

    public static String VENDOR_DIRECTORY = "mysoftwarehouse";
    public static String APPLICATION_DIRECTORY = "demo";
    public static String DATABASE_NAME = "demodb";
    //
    public static String DATABASE_CONNECTION_CODE = "hsql";
    public static String DATABASE_USERNAME = "sa";
    public static String DATABASE_PASSWORD = "";
    //
    public static Class<? extends UserFormInterface> MENU_FORM = DemoMainMenu.class;
}
