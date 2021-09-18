/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.fill.calculation.BucketDefinition;

/**
 *
 * @author Ng Siak Hooi
 */
public class CrosstabEnum {

    public enum RunDirection {

        LTR(JRCrosstab.RUN_DIRECTION_LTR),
        RTL(JRCrosstab.RUN_DIRECTION_RTL);
        public byte direction = JRCrosstab.RUN_DIRECTION_LTR;

        RunDirection(byte value) {
            this.direction = value;
        }
    }

    public enum PercentageOfType {

        NONE(JRCrosstabMeasure.PERCENTAGE_TYPE_NONE),
        GRAND_TOTAL(JRCrosstabMeasure.PERCENTAGE_TYPE_GRAND_TOTAL);
        public byte percentageOfType = JRCrosstabMeasure.PERCENTAGE_TYPE_NONE;

        PercentageOfType(byte percentageOfType) {
            this.percentageOfType = percentageOfType;
        }
    }

    public enum ColumnGroupHeaderPosition {

        LEFT(JRCellContents.POSITION_X_LEFT),
        CENTER(JRCellContents.POSITION_X_CENTER),
        RIGHT(JRCellContents.POSITION_X_RIGHT),
        STRETCH(JRCellContents.POSITION_X_STRETCH);
        public byte columnGroupHeaderPosition = JRCellContents.POSITION_X_STRETCH;

        ColumnGroupHeaderPosition(byte value) {
            this.columnGroupHeaderPosition = value;
        }
    }

    public enum RowGroupHeaderPosition {

        TOP(JRCellContents.POSITION_Y_TOP),
        MIDDLE(JRCellContents.POSITION_Y_MIDDLE),
        BOTTOM(JRCellContents.POSITION_Y_BOTTOM),
        STRETCH(JRCellContents.POSITION_Y_STRETCH);
        public byte rowGroupHeaderPosition = JRCellContents.POSITION_Y_STRETCH;

        RowGroupHeaderPosition(byte value) {
            this.rowGroupHeaderPosition = value;
        }
    }

    public enum GroupTotalPosition {

        NONE(BucketDefinition.TOTAL_POSITION_NONE),
        START(BucketDefinition.TOTAL_POSITION_START),
        END(BucketDefinition.TOTAL_POSITION_END);
        public byte groupTotalPosition = BucketDefinition.TOTAL_POSITION_NONE;

        GroupTotalPosition(byte value) {
            this.groupTotalPosition = value;
        }
    }

    public enum GroupBucketOrder {

        ASCENDING(BucketDefinition.ORDER_ASCENDING),
        DESCENDING(BucketDefinition.ORDER_DESCENDING);
        public byte groupBucketOrder = BucketDefinition.ORDER_ASCENDING;

        GroupBucketOrder(byte value) {
            this.groupBucketOrder = value;
        }
    }
}
