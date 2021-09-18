/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportTemplateControl {

    public final static ReportTemplateControl ALL =
            new ReportTemplateControl(
            true, true, true, true, true, true);
    public boolean replaceBackground;
    public boolean replaceTitle;
    public boolean replacePageHeader;
    public boolean replacePageFooter;
    public boolean replaceLastPageFooter;
    public boolean replaceNoData;

    public ReportTemplateControl(
            boolean replaceBackground,
            boolean replaceTitle,
            boolean replacePageHeader,
            boolean replacePageFooter,
            boolean replaceLastPageFooter,
            boolean replaceNoData) {
        this.replaceBackground = replaceBackground;
        this.replaceTitle = replaceTitle;
        this.replacePageHeader = replacePageHeader;
        this.replacePageFooter = replacePageFooter;
        this.replaceLastPageFooter = replaceLastPageFooter;
        this.replaceNoData = replaceNoData;
    }
}
