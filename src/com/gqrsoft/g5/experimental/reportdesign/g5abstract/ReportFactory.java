/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportFactory {

    public static JRDesignExpression createExpression(
            Class clazz, String exp) {
        JRDesignExpression a = new JRDesignExpression();
        a.setText(exp);
        a.setValueClass(clazz);
        return a;
    }

    public static double mm2px(double mm) {
        if (mm == 0) {
            return 0.0;
        }
        return mm / 209.903 * 595;
    }

    public static double cm2px(double cm) {
        if (cm == 0) {
            return 0.0;
        }
        return cm / 20.9903 * 595;
    }

    public static double in2px(double in) {
        if (in == 0) {
            return 0.0;
        }
        return in / 8.264 * 595;
    }

    public static String getBarcodeExpression(
            int type, String textExpression, boolean showText,
            boolean checkSum, String applicationIdentifier,
            int width, int height) {
        if (width == 0) {
            width = 1;
        } else {
            width = Math.abs(width);
        }
        if (height == 0) {
            height = 1;
        } else {
            height = Math.abs(height);
        }

//        private String  = "null";

        StringBuffer text = new StringBuffer();
        text.append("it.businesslogic.ireport.barcode.BcImage.getBarcodeImage(");
        text.append(type);
        text.append(",");
        text.append(textExpression);
        text.append(",");
        text.append(showText);
        text.append(",");
        text.append(checkSum);
        text.append(",");
        text.append(applicationIdentifier);
        text.append(",");
        text.append(width);
        text.append(",");
        text.append(height);
        text.append(")");

        return text.toString();
    }
}
