/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportDesignEnum {

    public enum Orientation {

        PORTRAIT(JasperDesign.ORIENTATION_PORTRAIT),
        LANDSCAPE(JasperDesign.ORIENTATION_LANDSCAPE);
        public byte value = JasperDesign.ORIENTATION_PORTRAIT;

        Orientation(byte value) {
            this.value = value;
        }
    }

    public enum PaperType {

        A0(2380, 3368),
        A1(1684, 2380),
        A2(1190, 1684),
        A3(842, 1190),
        A4(595, 842),
        A5(421, 595),
        A6(297, 421),
        B0(2836, 4008),
        B1(2004, 2836),
        B2(1418, 2004),
        B3(1002, 1418),
        B4(709, 1002),
        B5(501, 709),
        LETTER(612, 792),
        LEDGER(792, 1224);
        public int width,  height;

        PaperType(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public enum WhenNoDataType {

        ALL_SECTIONS_NO_DETAIL(
        JasperDesign.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL),
        BLANK_PAGE(
        JasperDesign.WHEN_NO_DATA_TYPE_BLANK_PAGE),
        NO_DATA_SECTION(
        JasperDesign.WHEN_NO_DATA_TYPE_NO_DATA_SECTION),
        NO_PAGES(
        JasperDesign.WHEN_NO_DATA_TYPE_NO_PAGES);
        public byte value = JasperDesign.WHEN_NO_DATA_TYPE_NO_DATA_SECTION;

        WhenNoDataType(byte value) {
            this.value = value;
        }
    }

    public enum ColumnPrintOrder {

        VERTICAL(JasperDesign.PRINT_ORDER_VERTICAL),
        HORIZONTAL(JasperDesign.PRINT_ORDER_HORIZONTAL);
        public byte value = JasperDesign.PRINT_ORDER_VERTICAL;

        ColumnPrintOrder(byte value) {
            this.value = value;
        }
    }

    public enum SortOrder {

        ASCENDING(JRDesignSortField.SORT_ORDER_ASCENDING),
        DESCENDING(JRDesignSortField.SORT_ORDER_DESCENDING);
        public byte value = JRDesignSortField.SORT_ORDER_ASCENDING;

        SortOrder(byte value) {
            this.value = value;
        }
    }

    public enum CalculationType {

        NOTHING(JRVariable.CALCULATION_NOTHING),
        COUNT(JRVariable.CALCULATION_COUNT),
        SUM(JRVariable.CALCULATION_SUM),
        AVERAGE(JRVariable.CALCULATION_AVERAGE),
        LOWEST(JRVariable.CALCULATION_LOWEST),
        HIGHEST(JRVariable.CALCULATION_HIGHEST),
        STDDEV(JRVariable.CALCULATION_STANDARD_DEVIATION),
        VARIANCE(JRVariable.CALCULATION_VARIANCE),
        SYSTEM(JRVariable.CALCULATION_SYSTEM),
        FIRST(JRVariable.CALCULATION_FIRST),
        DISTINCT_COUNT(JRVariable.CALCULATION_DISTINCT_COUNT);
        public byte value = JRVariable.CALCULATION_NOTHING;

        CalculationType(byte value) {
            this.value = value;
        }
    }

    public enum ResetType {

        REPORT(JRVariable.RESET_TYPE_REPORT),
        PAGE(JRVariable.RESET_TYPE_PAGE),
        COLUMN(JRVariable.RESET_TYPE_COLUMN),
        GROUP(JRVariable.RESET_TYPE_GROUP),
        NONE(JRVariable.RESET_TYPE_NONE);
        public byte value = JRVariable.RESET_TYPE_REPORT;

        ResetType(byte value) {
            this.value = value;
        }
    }
}
