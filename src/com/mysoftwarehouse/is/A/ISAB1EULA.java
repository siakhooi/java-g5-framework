/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.ButtonForm2;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAB1EULA extends ButtonForm2 {

    @Override
    public void build() {
        super.addApplication("Accept",
                "ISAB1EULA.Accept.label", "ISAB1EULA.Accept.description");
        super.setIcon(cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()));
        super.addApplication("Reject",
                "ISAB1EULA.Reject.label", "ISAB1EULA.Reject.description");
        super.setIcon(cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()));
    }

    @Override
    public void commandActivated(String nme, String label, String description) {
        if ("Accept".equals(nme)) {
            cmd.in.booleanValue = true;
            cmd.form.closeForm();
        } else if ("Reject".equals(nme)) {
            cmd.form.closeApplication(true);
        }
    }
}
