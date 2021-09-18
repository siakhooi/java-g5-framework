/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 * set cmd.out.colorValue
 * return from cmd.out.colorValue
 * @author Ng Siak Hooi
 */
public class SelectColorForm extends BlankForm {

    private JColorChooser colorChooser;

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectColorForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

//    @Override
//    public String getFormLevelLocaleBaseName() {
//        return cmd.parent.getFormLevelLocaleBaseName();
//    }
    @Override
    public void buildBlankForm(JPanel parent) {
        Color initValue = cmd.in.colorValue;
        if (initValue == null) {
            colorChooser = new JColorChooser();
        } else {
            colorChooser = new JColorChooser(initValue);
        }

        ActionForm_OKCancel af = new ActionForm_OKCancel();
        JPanel a1 = cmd.form.createEmbedded(af);
        parent.setLayout(new BorderLayout());
        parent.add(colorChooser, BorderLayout.CENTER);
        parent.add(a1, BorderLayout.PAGE_END);

//        String title;
//        title = "SelectColorForm.button.OK";
//        title = cmd.lang.getSystemString(title);
//        super.buttons.addI18nButton("OK", title);
//        title = "SelectColorForm.button.Cancel";
//        title = cmd.lang.getSystemString(title);
//        super.buttons.addI18nButton("Cancel", title);
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.BUTTON) {
            if (signalCode.equals(ActionForm_OKCancel.BUTTON_OK)) {
                cmd.in.colorValue = colorChooser.getColor();
                cmd.form.closeForm();
            } else if (signalCode.equals(ActionForm_OKCancel.BUTTON_CANCEL)) {
                cmd.in.colorValue = null;
                cmd.form.closeForm();
            }
        }
    }

//    @Override
//    public void buttonClick(String name) {
//        if (name.equals("OK")) {
//            cmd.in.colorValue = colorChooser.getColor();
//        } else {
//            cmd.in.colorValue = null;
//        }
//        cmd.form.closeForm();
//    }
    @Override
    public void onEscapeKeyPressed() {
        cmd.in.colorValue = null;
        cmd.form.closeForm();
    }

    @Override
    public String getFormTitle() {
        return null;
    }
}
