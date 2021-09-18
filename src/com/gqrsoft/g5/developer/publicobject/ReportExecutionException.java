/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportExecutionException extends Exception {

    public ReportExecutionException(String i18nMessage) {
        super(i18nMessage);
    }

    public ReportExecutionException(String i18nMessage, JRException ex) {
        super(i18nMessage, ex);
    }
}
