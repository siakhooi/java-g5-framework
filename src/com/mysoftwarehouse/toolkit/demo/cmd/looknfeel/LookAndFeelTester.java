/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.cmd.looknfeel;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;

/**
 *
 * @author Ng Siak Hooi
 */
public class LookAndFeelTester extends ButtonForm2 {

    @Override
    public void build() {
        super.addI18nApplication("Windows", "Windows", "Windows");
        super.addI18nApplication("Metal", "Metal", "Metal");
        super.addI18nApplication("System", "System", "System");
        super.addI18nApplication("CrossPlatform", "Cross Platform", "Cross Platform");
        super.addI18nApplication("Motif", "Motif", "Motif");
        super.addI18nApplication("WindowsClassic", "Windows Classic", "Windows Classic");
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        try {
            if ("Windows".equals(name)) {
                cmd.looknfeel.useWindows();
            } else if ("Metal".equals(name)) {
                cmd.looknfeel.useMetal();
            } else if ("System".equals(name)) {
                cmd.looknfeel.useSystem();
            } else if ("CrossPlatform".equals(name)) {
                cmd.looknfeel.useCrossPlatform();
            } else if ("Motif".equals(name)) {
                cmd.looknfeel.useMotif();
            } else if ("WindowsClassic".equals(name)) {
                cmd.looknfeel.useWindowsClassic();
            }
        } catch (Exception ex) {
            cmd.common.showI18nMessage(DialogMessageType.ERROR, "Error", ex.getLocalizedMessage());
        }
    }

    @Override
    public String getFormTitle() {
        return "LookAndFeelTester.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
