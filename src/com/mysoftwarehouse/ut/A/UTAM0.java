/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.A;

import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.mysoftwarehouse.ut.B.UTBE0;
import com.mysoftwarehouse.ut.C.UTCE0;
import com.mysoftwarehouse.ut.D.UTDE0;
import com.mysoftwarehouse.ut.E.UTEE0;
import com.mysoftwarehouse.ut.F.UTFE0;
import com.mysoftwarehouse.ut.G.UTGE0;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTAM0 extends SystemTrayMenuForm {

    @Override
    public Image getTrayIconImage() {
        return cmd.appinfo.getApplicationIcon().getImage();
        //return cmd.icon.getApplicationIcon(cmd.icon.getDefaultHeight()).getImage();
    }

    private void a(String action, String n) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(action, title, description);
    }

    private void a(String action) {
        a(action, "UTAM0." + action);
    }

    private void a(Class<? extends UserFormInterface> f, String n) {
        a(f, n, true);
    }

    private void a(Class<? extends UserFormInterface> f, String n, boolean newThread) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(f, title, description, newThread);
    }

    @Override
    public void build() {
//        super.setImage(APP.getTrayIconImage());
        a(UTBE0.class, "UTBE0");
        a(UTCE0.class, "UTCE0");
        a(UTDE0.class, "UTDE0");
        a(UTEE0.class, "UTEE0");
        a(UTFE0.class, "UTFE0");
        a(UTGE0.class, "UTGE0");

        super.addSeparator();
//        a("changeLanguage");
        a(AboutApplicationForm.class, "UTAM0.AboutApplication");
//        a(AboutSystemPropertiesForm.class, "UTAM0.AboutSystem");
        super.addSeparator();
        a("closeApp");
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("changeLanguage".equals(name)) {
            cmd.lang.selectLanguage();
            String s = cmd.lang.getCurrentLanguageCode();
//            INI.writeLanguage(this, s);
//            UserFormInterface f = cmd.form.create(BSAM0.class);
//            cmd.form.executeInNewThread(f);
//            cmd.form.closeForm();
        } else if ("closeApp".equals(name)) {
            cmd.form.closeApplication(false);
        }
    }
}
