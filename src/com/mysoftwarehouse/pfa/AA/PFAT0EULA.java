/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAT0EULA extends PFAT_EULA {

    @Override
    public String getFormTitle() {
        return "PFAT0EULA.title";
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return PFAB0EULA.class;
    }

//    @Override
//    protected void initButton() {
//        super.buttons.addButton("OK", "PFAT0EULA.button.OK",
//                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), false);
//    }
//
//    @Override
//    public void buttonClick(String name) {
//        cmd.form.closeForm();
//    }
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
