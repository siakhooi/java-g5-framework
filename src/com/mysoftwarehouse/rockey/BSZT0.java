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
public class BSZT0 extends TextForm {

    @Override
    public String getFormTitle() {
        return "BSZT0.title";
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
        super.buttons.addButton("test", "BSZT0.test");
        super.buttons.addButton("clear", "BSZT0.clear");
    }

    @Override
    public void buttonClick(String name) {
        if ("clear".equals(name)) {
            super.setText("");
        } else if ("test".equals(name)) {
            try {
                boolean i = DongleControl.hasValidDongle(this);
                String b = cmd.linewrite.getString();
                cmd.linewrite.init();
                cmd.linewrite.println("Dongle Valid: " + i);
                cmd.linewrite.println(b);
                super.setText(cmd.linewrite.getString());
            } catch (Error er) {
                StringWriter sw = new StringWriter();
                er.printStackTrace(new PrintWriter(sw));
                super.setText(sw.toString());
            }
        }
    }
}
