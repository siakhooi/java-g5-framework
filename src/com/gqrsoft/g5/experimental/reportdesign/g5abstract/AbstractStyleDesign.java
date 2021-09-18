/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import com.gqrsoft.g5.experimental.reportdesign.design.StyleDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5public.StyleDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractStyleDesign {

    public JRDesignStyle style;

    protected abstract void defineGeneral();

    protected abstract void defineText();

    protected abstract void defineGraphics();

    protected abstract void defineBorder();

    public void init() {
        style = new JRDesignStyle();
        defineGeneral();
        defineText();
        defineGraphics();
        defineBorder();
    }

    protected final void setName(String name) {
        style.setName(name);
    }

    protected final void setIsDefault(boolean isDefault) {
        style.setDefault(isDefault);
    }

    protected final void setParentStyle(StyleDesign sd) {
        style.setParentStyle(sd.style);
    }

    protected final void addConditionalStyle(String expression, StyleDesign parentStyle) {
        JRDesignConditionalStyle s = new JRDesignConditionalStyle();
        s.setParentStyle(parentStyle.style);
        JRDesignExpression e =
                ReportFactory.createExpression(Boolean.class, expression);
        s.setConditionExpression(e);
        style.addConditionalStyle(s);
    }

    protected final void setStyleColorBackground(Color value) {
        style.setBackcolor(value);
    }

    protected final void setStyleColorForeground(Color value) {
        style.setForecolor(value);
    }

    protected final void setStyleBold(boolean value) {
        style.setBold(value);
    }

    protected final void setStyleItalic(boolean value) {
        style.setItalic(value);
    }

    protected final void setStyleUnderline(boolean value) {
        style.setUnderline(value);
    }

    protected final void setStyleStrikeThrough(boolean value) {
        style.setStrikeThrough(value);
    }

    protected final void setStyleStyledText(boolean value) {
        style.setStyledText(value);
    }

    protected final void setStyleFontName(String fontName) {
        style.setFontName(fontName);
    }

    protected final void setStyleFontSize(int size) {
        style.setFontSize(size);
    }

    protected final void setStylePdfEmbedded(boolean pdfEmbedded) {
        style.setPdfEmbedded(pdfEmbedded);
    }

    protected final void setStylePdfEncoding(String pdfEncoding) {
        style.setPdfEncoding(pdfEncoding);
    }

    protected final void setStylePdfFontName(String pdfFontName) {
        style.setPdfFontName(pdfFontName);
    }

    protected final void setStyleAlignmentHorizontal(
            StyleDesignEnum.HorizontalAlignment align) {
        style.setHorizontalAlignment(align.value);
    }

    protected final void setStyleAlignmentVertical(
            StyleDesignEnum.VerticalAlignment align) {
        style.setVerticalAlignment(align.value);
    }

    protected final void setStyleOpaque(StyleDesignEnum.StyleMode v) {
        style.setMode(v.value);
    }

    protected final void setStyleTextPattern(String pattern) {
        style.setPattern(pattern);
    }

    protected final void setStyleTextRotation(StyleDesignEnum.TextRotation r) {
        style.setRotation(r.value);
    }

    protected final void setStyleTextLineSpacing(StyleDesignEnum.LineSpacing s) {
        style.setLineSpacing(s.value);
    }

    protected final void setStyleBlankWhenNull(boolean v) {
        style.setBlankWhenNull(v);
    }

    protected final void setStyleRadius(int r) {
        style.setRadius(r);
    }

    protected final void setStyleFill(StyleDesignEnum.Fill v) {
        style.setFill(v.value);
    }

    protected final void setStyleScaleImage(StyleDesignEnum.ScaleImage s) {
        style.setScaleImage(s.value);
    }

    protected final void setStylePenColor(Color c) {
        style.getLinePen().setLineColor(c);
    }

    protected final void setStylePenLineStyle(StyleDesignEnum.PenLineStyle p) {
        style.getLinePen().setLineStyle(p.value);
    }

    protected final void setStylePenWidth(float v) {
        style.getLinePen().setLineWidth(v);
    }

    protected final void setStyleBorderLeftColor(Color c) {
        style.getLineBox().getLeftPen().setLineColor(c);
    }

    protected final void setStyleBorderLeftLineStyle(
            StyleDesignEnum.PenLineStyle c) {
        style.getLineBox().getLeftPen().setLineStyle(c.value);
    }

    protected final void setStyleBorderLeftWidth(float v) {
        style.getLineBox().getLeftPen().setLineWidth(v);
    }

    protected final void setStyleBorderLeftPadding(int v) {
        style.getLineBox().setLeftPadding(v);
    }

    protected final void setStyleBorderRightColor(Color c) {
        style.getLineBox().getRightPen().setLineColor(c);
    }

    protected final void setStyleBorderRightLineStyle(
            StyleDesignEnum.PenLineStyle c) {
        style.getLineBox().getRightPen().setLineStyle(c.value);
    }

    protected final void setStyleBorderRightWidth(float v) {
        style.getLineBox().getRightPen().setLineWidth(v);
    }

    protected final void setStyleBorderRightPadding(int v) {
        style.getLineBox().setRightPadding(v);
    }

    protected final void setStyleBorderTopColor(Color c) {
        style.getLineBox().getTopPen().setLineColor(c);
    }

    protected final void setStyleBorderTopLineStyle(StyleDesignEnum.PenLineStyle c) {
        style.getLineBox().getTopPen().setLineStyle(c.value);
    }

    protected final void setStyleBorderTopWidth(float v) {
        style.getLineBox().getTopPen().setLineWidth(v);
    }

    protected final void setStyleBorderTopPadding(int v) {
        style.getLineBox().setTopPadding(v);
    }

    protected final void setStyleBorderBottomColor(Color c) {
        style.getLineBox().getBottomPen().setLineColor(c);
    }

    protected final void setStyleBorderBottomLineStyle(
            StyleDesignEnum.PenLineStyle c) {
        style.getLineBox().getBottomPen().setLineStyle(c.value);
    }

    protected final void setStyleBorderBottomWidth(float v) {
        style.getLineBox().getBottomPen().setLineWidth(v);
    }

    protected final void setStyleBorderBottomPadding(int v) {
        style.getLineBox().setBottomPadding(v);
    }

    protected final void setStyleBorderColor(Color c) {
        setStyleBorderLeftColor(c);
        setStyleBorderRightColor(c);
        setStyleBorderTopColor(c);
        setStyleBorderBottomColor(c);
    }

    protected final void setStyleBorderLineStyle(StyleDesignEnum.PenLineStyle c) {
        setStyleBorderLeftLineStyle(c);
        setStyleBorderRightLineStyle(c);
        setStyleBorderTopLineStyle(c);
        setStyleBorderBottomLineStyle(c);
    }

    protected final void setStyleBorderWidth(float v) {
        setStyleBorderLeftWidth(v);
        setStyleBorderRightWidth(v);
        setStyleBorderTopWidth(v);
        setStyleBorderBottomWidth(v);
    }

    protected final void setStyleBorderPadding(int v) {
        setStyleBorderLeftPadding(v);
        setStyleBorderRightPadding(v);
        setStyleBorderTopPadding(v);
        setStyleBorderBottomPadding(v);
    }
}

