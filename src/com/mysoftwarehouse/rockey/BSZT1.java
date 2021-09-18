/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.TextForm;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSZT1 extends TextForm {

    @Override
    public String getFormTitle() {
        return "BSZT1.title";
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
        super.buttons.addButton("write", "BSZT1.write");
        super.buttons.addButton("clear", "BSZT1.clear");
    }

    @Override
    public void buttonClick(String name) {
        if ("clear".equals(name)) {
            super.setText("");
        } else if ("write".equals(name)) {
            try {
                cmd.form.broadcastSignal(ZSignal.SIGNAL_SEND_PWD, true);
                short ap1 = cmd.local.map.integers.get(ZSignal.GLOBAL_AP1).shortValue();
                short ap2 = cmd.local.map.integers.get(ZSignal.GLOBAL_AP2).shortValue();

                String r = DongleControl.write(this, ap1, ap2);
                super.setText(r);
            } catch (Exception ex) {
                cmd.log.sysSevere("BSZT1.error", ex);
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                super.setText(sw.toString());
            } catch (Error er) {
                cmd.log.sysSevere("BSZT1.error", er);
                StringWriter sw = new StringWriter();
                er.printStackTrace(new PrintWriter(sw));
                super.setText(sw.toString());
            }
        }
    }
}
