/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRGraphicElement;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRTextElement;

/**
 *
 * @author Ng Siak Hooi
 */
public class StyleDesignEnum {

    public enum HorizontalAlignment {

        LEFT(JRAlignment.HORIZONTAL_ALIGN_LEFT),
        CENTER(JRAlignment.HORIZONTAL_ALIGN_CENTER),
        RIGHT(JRAlignment.HORIZONTAL_ALIGN_RIGHT),
        JUSTIFIED(JRAlignment.HORIZONTAL_ALIGN_JUSTIFIED);
        public byte value = JRAlignment.HORIZONTAL_ALIGN_LEFT;

        HorizontalAlignment(byte alignmentType) {
            this.value = alignmentType;
        }
    }

    public enum VerticalAlignment {

        TOP(JRAlignment.VERTICAL_ALIGN_TOP),
        MIDDLE(JRAlignment.VERTICAL_ALIGN_MIDDLE),
        BOTTOM(JRAlignment.VERTICAL_ALIGN_BOTTOM),
        JUSTIFIED(JRAlignment.VERTICAL_ALIGN_JUSTIFIED);
        public byte value = JRAlignment.VERTICAL_ALIGN_TOP;

        VerticalAlignment(byte alignmentType) {
            this.value = alignmentType;
        }
    }

    public enum StyleMode {

        OPAQUE(JRElement.MODE_OPAQUE),
        TRANSPARENT(JRElement.MODE_TRANSPARENT);
        public byte value = JRElement.MODE_TRANSPARENT;

        StyleMode(byte value) {
            this.value = value;
        }
    }

    public enum TextRotation {

        NONE(JRTextElement.ROTATION_NONE),
        LEFT(JRTextElement.ROTATION_LEFT),
        RIGHT(JRTextElement.ROTATION_RIGHT),
        UPSIDE_DOWN(JRTextElement.ROTATION_UPSIDE_DOWN);
        public byte value = JRTextElement.ROTATION_NONE;

        TextRotation(byte value) {
            this.value = value;
        }
    }

    public enum LineSpacing {

        SINGLE(JRTextElement.LINE_SPACING_SINGLE),
        ONEHALF(JRTextElement.LINE_SPACING_1_1_2),
        DOUBLE(JRTextElement.LINE_SPACING_DOUBLE);
        public byte value = JRTextElement.LINE_SPACING_SINGLE;

        LineSpacing(byte value) {
            this.value = value;
        }
    }

    public enum Fill {

        SOLID(new Byte(JRGraphicElement.FILL_SOLID)),
        NONE(null);
        public Byte value = null;

        Fill(Byte value) {
            this.value = value;
        }
    }

    public enum ScaleImage {

        CLIP(JRImage.SCALE_IMAGE_CLIP),
        FILL_FRAME(JRImage.SCALE_IMAGE_FILL_FRAME),
        RETAIN_SHAPE(JRImage.SCALE_IMAGE_RETAIN_SHAPE);
        public byte value = JRImage.SCALE_IMAGE_CLIP;

        ScaleImage(byte value) {
            this.value = value;
        }
    }

    public enum PenLineStyle {

        DASHED(JRPen.LINE_STYLE_DASHED),
        DOTTED(JRPen.LINE_STYLE_DOTTED),
        DOUBLE(JRPen.LINE_STYLE_DOUBLE),
        SOLID(JRPen.LINE_STYLE_SOLID);
        public byte value = JRPen.LINE_STYLE_SOLID;

        PenLineStyle(byte value) {
            this.value = value;
        }
    }
}
