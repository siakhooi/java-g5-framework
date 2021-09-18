/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.clean.web;

import com.gqrsoft.g5.developer.form.PlainTextForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class WebReturnInfo extends PlainTextForm {

    @Override
    protected void init() {
        super.setRows(20);
        super.setColumns(80);
        super.setEditable(false);
        super.setText("");
    }

    @Override
    public void receiveSignal(int signalNumber) {
        super.setText(cmd.local.stringValue);
    }

    @Override
    protected void save(String newText) {

    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public boolean showButtons() {
        return false;
    }
}
