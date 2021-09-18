/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.conf;

/**
 *
 * @author Ng Siak Hooi
 */
public class APP {

    public static String CONFIG_FILE = "ut.ini";
//    
    public static String VENDOR_DIRECTORY = "mysoftwarehouse";
    public static String APPLICATION_DIRECTORY = "ut";
    public static String APPLICATION_LOG_FILE = "ut.log";
    public static String SYSTEM_LOG_FILE = "core.log";
//
    public static String ICON_PATH = "/com/mysoftwarehouse/ut/resources/icon.png";

//    public static Image getTrayIconImage() {
//        return createImageIcon(ICON_PATH).getImage();
//    }
//
//    private static ImageIcon createImageIcon(String jarPath) {
//        java.net.URL imgURL =
//                APP.class.getResource(jarPath);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + jarPath);
//            return null;
//        }
//    }
}
