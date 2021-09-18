/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.text;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ActionForm_OKCancel;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.kernel.config.MenuSignal;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloEditText extends TextForm2 {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_OKCancel.class;
    }

    @Override
    protected void init() {
        super.setColumns(20);
        super.setRows(5);
        super.setEditable(true);
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
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.BUTTON) {
            cmd.common.showI18nMessage(DialogMessageType.INFORMATION, signalCode,
                    super.getText());
            cmd.form.closeForm();
        }else super.receiveSystemSignal(signalNumber, signalCode);
    }

    @Override
    public String getFormI18nTitle() {
        return "Hello Edit Text";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
