/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m30.menu;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutEngineForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.info.ReleaseNoteMenuForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloMenu extends MenuForm {

    @Override
    public void build() {
        openI18nRole("Thread", "Test Thread");
        addI18nApplication(HelloWorld.class, "Hello World (new Thread)", "", true);
        addI18nApplication(HelloWorld.class, "Hello World (not new Thread)", "", false);
        closeRole();
        openI18nRole("File", "File Menu");
        openI18nCategory("Frame", "Test Frame");
        addI18nApplication("max", "Maximize Windows", "");
        addI18nApplication("maxv", "Maximize Windows (Vertical)", "");
        addI18nApplication("maxh", "Maximize Windows (Horizontal)", "");
        addI18nApplication("maxn", "Restore Windows", "");
        addI18nApplication("maxi", "Iconize Windows", "");
        closeCategory();
        openI18nCategory("File Edit", "File Edit Menu");
        addI18nApplication("fileedit1", "File Edit 1", "File Edit 1 Menu");
        addI18nApplication("fileedit2", "File Edit 2", "File Edit 2 Menu");
        addI18nApplication("fileedit3", "File Edit 3", "File Edit 3 Menu");
        addI18nApplication("fileedit4", "File Edit 4", "File Edit 4 Menu");
        closeCategory();
        closeRole();
   }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("max".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
        } else if ("maxv".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_VERT);
        } else if ("maxh".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_HORIZ);
        } else if ("maxn".equals(name)) {
            cmd.frame.setState(FrameState.NORMAL);
        } else if ("maxi".equals(name)) {
            cmd.frame.setState(FrameState.ICONIFIED);
        } else {
            cmd.common.showI18nMessage(DialogMessageType.INFORMATION,
                    name, name);
        }
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "HelloMenu";
    }
}
