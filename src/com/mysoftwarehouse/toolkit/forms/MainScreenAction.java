/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.forms;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class MainScreenAction extends ButtonForm2 {

    private String className[];
    private String title[];

    @Override
    public void build() {
        className = new String[ToolkitConfiguration.TOTAL_MENU_ITEM];
        title = new String[ToolkitConfiguration.TOTAL_MENU_ITEM];
        for (int i = 0; i < ToolkitConfiguration.TOTAL_MENU_ITEM; i++) {
            String opt = "f" + i;
            if (cmd.argv.hasOption(opt)) {
                className[i] = cmd.argv.getOptionValue(opt, "");
            }
            opt = "t" + i;
            if (cmd.argv.hasOption(opt)) {
                title[i] = cmd.argv.getOptionValue(opt, "");
            }
        }
        Icon icon = cmd.icon.getApplicationIcon(
                cmd.icon.getDefaultHeight());
        for (int i = 0; i < ToolkitConfiguration.TOTAL_MENU_ITEM; i++) {
            if (title[i] == null || className[i] == null) {
                continue;
            }
            super.addApplication(cmd.data.int2String(i), title[i], title[i]);
            super.setIcon(icon);
        }
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.BUTTON) {
            for (int i = 0; i < ToolkitConfiguration.TOTAL_MENU_ITEM; i++) {
                String n = cmd.data.int2String(i);
                if (n.equals(signalCode)) {
                    int n1 = Integer.parseInt(n);
                    String clz = className[n1];
                    execute(clz);
                    return;
                }
            }
        }
    }
    private void execute(String clz) {
        try {
            @SuppressWarnings("unchecked")
            Class<UserFormInterface> formClz = (Class<UserFormInterface>) Class.forName(clz);
            UserFormInterface form = cmd.form.create(formClz);
            cmd.form.executeInNewThread(form);
        } catch ( ClassNotFoundException ex) {
            String msgTitle = "MainScreen.execute.error.title";
            String msg = "MainScreen.execute.error.msg.{0}";
            msgTitle = cmd.lang.getString(msgTitle);
            msg = cmd.lang.getString(msg, clz);
            cmd.common.showMessage(DialogMessageType.ERROR, msgTitle, msg);
            cmd.log.severe("MainScreen.error.loadClass", ex);
        }
    }
    
}
