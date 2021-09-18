/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.TextForm;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSZT2 extends TextForm {

    @Override
    public String getFormTitle() {
        return "BSZT2.title";
    }

    public void onEscapeKeyPressed() {
    }

    @Override
    protected void init() {
        super.setColumns(80);
        super.setRows(16);
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("read", "BSZT2.read");
        super.buttons.addButton("clear", "BSZT2.clear");
    }

    @Override
    public void buttonClick(String name) {
        if ("clear".equals(name)) {
            super.setText("");
        } else if ("read".equals(name)) {
            try {
                String r = DongleControl.read(this);
                super.setText(r);
            } catch (Error er) {
                StringWriter sw = new StringWriter();
                er.printStackTrace(new PrintWriter(sw));
                super.setText(sw.toString());
            }
        }
    }
}
