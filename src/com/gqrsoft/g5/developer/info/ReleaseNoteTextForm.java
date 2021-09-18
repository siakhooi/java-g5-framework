/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.TextForm2;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReleaseNoteTextForm extends TextForm2{

    @Override
    protected void init() {
        super.setEditable(false);
        super.setColumns(80);
        super.setLineWrap(false);
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }

    @Override
    public void receiveSignal(int signalNumber) {
        super.setText(cmd.local.stringValue);
    }

}
