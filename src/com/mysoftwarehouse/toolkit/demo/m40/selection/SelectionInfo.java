/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m40.selection;

import com.gqrsoft.g5.developer.form.TextForm2;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectionInfo extends TextForm2 {

    @Override
    protected void init() {
        super.setRows(10);
        super.setColumns(80);
        super.setEditable(false);
        super.setText("");
    }

    @Override
    public void receiveSignal(int signalNumber) {
        super.setText(cmd.local.stringValue);
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }
}
