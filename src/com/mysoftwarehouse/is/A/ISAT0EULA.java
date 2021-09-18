/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAT0EULA extends ISAT_EULA {

    @Override
    public String getFormTitle() {
        return "ISAT0EULA.title";
    }

//    @Override
//    protected void initButton() {
//        super.buttons.addButton("OK", "ISAT0EULA.button.OK",
//                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), false);
//    }
//
//    @Override
//    public void buttonClick(String name) {
//        cmd.form.closeForm();
//    }
//
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAB0EULA.class;
    }
}
