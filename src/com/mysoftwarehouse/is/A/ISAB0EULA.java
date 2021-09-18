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
public class ISAB0EULA extends ButtonForm2 {

    @Override
    public void build() {
        super.addApplication("OK",
                "ISAB0EULA.OK.label", "ISAB0EULA.OK.description");
        super.setIcon(cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()));
    }

    @Override
    public void commandActivated(String nme, String label, String description) {
        if ("OK".equals(nme)) {
            cmd.form.closeForm();
        }
    }
}
