/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.design.JRDesignBreak;

/**
 *
 * @author Ng Siak Hooi
 */
public class ElementDesignEnum {

    public enum PositionType {

        FIX_RELATIVE_TO_BOTTOM(JRElement.POSITION_TYPE_FIX_RELATIVE_TO_BOTTOM),
        FIX_RELATIVE_TO_TOP(JRElement.POSITION_TYPE_FIX_RELATIVE_TO_TOP),
        FLOAT(JRElement.POSITION_TYPE_FLOAT);
        public byte value = JRElement.POSITION_TYPE_FIX_RELATIVE_TO_TOP;

        PositionType(byte value) {
            this.value = value;
        }
    }

    public enum StretchType {

        NO_STRETCH(JRElement.STRETCH_TYPE_NO_STRETCH),
        RELATIVE_TO_BAND_HEIGHT(JRElement.STRETCH_TYPE_RELATIVE_TO_BAND_HEIGHT),
        RELATIVE_TO_TALLEST_OBJECT(JRElement.STRETCH_TYPE_RELATIVE_TO_TALLEST_OBJECT);
        public byte value = JRElement.STRETCH_TYPE_NO_STRETCH;

        StretchType(byte value) {
            this.value = value;
        }
    }

    public enum BreakType {

        PAGE(JRDesignBreak.TYPE_PAGE),
        COLUMN(JRDesignBreak.TYPE_COLUMN);
        public byte value = JRDesignBreak.TYPE_COLUMN;

        BreakType(byte value) {
            this.value = value;
        }
    }

    public enum EvaluationTime {

        NOW(JRExpression.EVALUATION_TIME_NOW),
        REPORT(JRExpression.EVALUATION_TIME_REPORT),
        PAGE(JRExpression.EVALUATION_TIME_PAGE),
        COLUMN(JRExpression.EVALUATION_TIME_COLUMN),
        GROUP(JRExpression.EVALUATION_TIME_GROUP),
        BAND(JRExpression.EVALUATION_TIME_BAND),
        AUTO(JRExpression.EVALUATION_TIME_AUTO);
        public byte value = JRExpression.EVALUATION_TIME_NOW;

        EvaluationTime(byte value) {
            this.value = value;
        }
    }

    public enum LineDirection {

        TOP_DOWN(JRLine.DIRECTION_TOP_DOWN),
        BOTTOM_UP(JRLine.DIRECTION_BOTTOM_UP);
        public byte value = JRLine.DIRECTION_TOP_DOWN;

        LineDirection(byte value) {
            this.value = value;
        }
    }

    public enum ImageOnErrorType {

        BLANK(JRImage.ON_ERROR_TYPE_BLANK),
        ERROR(JRImage.ON_ERROR_TYPE_ERROR),
        ICON(JRImage.ON_ERROR_TYPE_ICON);
        public byte value = JRImage.ON_ERROR_TYPE_BLANK;

        ImageOnErrorType(byte imageOnErrorType) {
            this.value = imageOnErrorType;
        }
    }
}
