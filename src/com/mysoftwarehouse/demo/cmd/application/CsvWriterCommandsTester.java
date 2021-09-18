/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.cmd.application;

import com.gqrsoft.g5.developer.form.ButtonForm;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class CsvWriterCommandsTester extends ButtonForm {

    @Override
    public void buildButtonForm(JPanel parent) {
        super.buttons.addI18nButton("csv", "Test CSV");
    }

    @Override
    public void buttonClick(String name) {
        String[][] s = {
            {"A1", "B1", "C1", "D1"},
            {"A2", "B2", "C2", "D2"},
            {"\\A3", "B\"3", "C\'3", "D3"},
            {"A4", "B4", "C4", "D4"},
            {"A5", "B5", "C5", "D5"}};
        char[] csv;

        cmd.csvwriter.clear();
        for (String[] b : s) {
            cmd.csvwriter.write(b);
        }
        csv = cmd.csvwriter.save();
        cmd.debug.println(new String(csv));

        cmd.csvwriter.setSeparator('-');
        //cmd.csvwriter.setEscapeChar('=');
        //cmd.csvwriter.setQuoteChar('^');
        cmd.csvwriter.setLineEnd(cmd.sysprop.crlf()+"-"+cmd.sysprop.crlf());
        cmd.csvwriter.noEscapeChar();
        cmd.csvwriter.noQuoteChar();
        cmd.csvwriter.clear();

        for (String[] b : s) {
            cmd.csvwriter.write(b);
        }
        csv = cmd.csvwriter.save();
        cmd.debug.println(new String(csv));
    }

    @Override
    public String getFormTitle() {
        return "CsvWriterCommandsTester.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
