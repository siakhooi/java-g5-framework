/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReleaseNoteMenuForm extends MenuForm {

    @Override
    public void build() {
        openI18nRole("Release Note", "");
        openI18nCategory("1.0.2.x", "");
        /**/ addI18nApplication("1.0.2.0", "1.0.2.0", "");
        closeCategory();
        openI18nCategory("1.0.1.x", "");
        /**/ addI18nApplication("1.0.1.4", "1.0.1.4", "");
        /**/ addI18nApplication("1.0.1.3", "1.0.1.3", "");
        /**/ addI18nApplication("1.0.1.2", "1.0.1.2", "");
        /**/ addI18nApplication("1.0.1.1", "1.0.1.1", "");
        /**/ addI18nApplication("1.0.1.0", "1.0.1.0", "");
        closeCategory();
        openI18nCategory("1.0.0.x", "");
        /**/ addI18nApplication("1.0.0.3", "1.0.0.3", "");
        /**/ addI18nApplication("1.0.0.2", "1.0.0.2", "");
        /**/ addI18nApplication("1.0.0.1", "1.0.0.1", "");
        /**/ addI18nApplication("1.0.0.0", "1.0.0.0", "");
        closeCategory();
        closeRole();
    }

    @Override
    public Class<? extends UserFormInterface> getRightForm() {
        return ReleaseNoteTextForm.class;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Release Note Menu";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public void itemSelected(String title, String description) {
        try {
            String p = "/com/gqrsoft/g5/resources/txt/ReleaseNote." + title + ".txt";
            InputStream is = ReleaseNoteMenuForm.class.getResourceAsStream(p);
            String b = new String(cmd.data.inputStream2ByteArray(is));
            cmd.local.stringValue = b;

            cmd.form.broadcastBlockingSignal(0);
        } catch (Exception ex) {
            cmd.local.stringValue = "";
            cmd.form.broadcastBlockingSignal(0);
        }
    }
}
