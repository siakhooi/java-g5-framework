/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuStyledComment extends StyledTextForm {

    @Override
    public String getInitialText() {
        return "Please click menu!";
    }

    @Override
    public void receiveSignal(int signalNumber) {
        super.setText("aiyoyo: " + signalNumber);
    }

    @Override
    public void hyperlinkClick(String href) {
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.Plain;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }
}
