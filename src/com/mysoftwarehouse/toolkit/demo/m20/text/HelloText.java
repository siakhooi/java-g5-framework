/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.text;

import com.gqrsoft.g5.developer.form.TextForm2;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloText extends TextForm2 {

    @Override
    protected void init() {
        super.setColumns(20);
        super.setRows(5);
        super.setEditable(false);
        super.setLineWrap(true);
        super.setWrapStyleWord(true);
        String s = "The quick brown fox jumps over the lazy dog.";
        super.setText(s);
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Hello Text";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
