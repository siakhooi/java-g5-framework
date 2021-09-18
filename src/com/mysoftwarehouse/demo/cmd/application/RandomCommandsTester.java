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
public class RandomCommandsTester extends ButtonForm {

    @Override
    public void buildButtonForm(JPanel parent) {
        super.buttons.addI18nButton("getAlphaNumericString", "getAlphaNumericString");
        super.buttons.addI18nButton("getString", "getString");
        super.buttons.addI18nButton("getBytes", "getBytes");
        super.buttons.addI18nButton("getInt", "getInt");
        super.buttons.addI18nButton("getIntRange", "getInt(Range)");
        super.buttons.addI18nButton("getShort", "getShort");

    }

    @Override
    public void buttonClick(String name) {
        if ("getAlphaNumericString".equals(name)) {
            cmd.debug.println("getAlphaNumericString: " +
                    cmd.random.getAlphaNumericString(10));
        } else if ("getString".equals(name)) {
            cmd.debug.println("getString: " +
                    cmd.random.getString("123456", 3));
        } else if ("getBytes".equals(name)) {
            byte[] b = new byte[15];
            cmd.random.getBytes(b);
            String s = new String(cmd.codec.hexEncode(b));
            cmd.debug.println("getBytes: " + s);
        } else if ("getInt".equals(name)) {
            cmd.debug.println("getInt: " + cmd.random.getInt());
        } else if ("getIntRange".equals(name)) {
            cmd.debug.println("getInt: " + cmd.random.getInt(1, 8));
        } else if ("getShort".equals(name)) {
            cmd.debug.println("getShort: " + cmd.random.getShort());
        }
    }

    @Override
    public String getFormTitle() {
        return "RandomCommandsTester.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
