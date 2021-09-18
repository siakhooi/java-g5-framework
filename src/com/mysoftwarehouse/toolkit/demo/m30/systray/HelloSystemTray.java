/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m30.systray;

import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloImage;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloLogo;
import com.mysoftwarehouse.toolkit.demo.m20.styledtext.HelloStyledText;
import com.mysoftwarehouse.toolkit.demo.m30.menu.HelloMenu;
import com.mysoftwarehouse.toolkit.demo.m20.text.HelloText;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloSystemTray extends SystemTrayMenuForm {

    @Override
    public String getDefaultTooltips() {
        return "HelloSystemTray.description";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cmd.form.executeInNewThread(new HelloWorld());
    }

    @Override
    public void build() {
        /**/ openI18nCategory("Blank Form", "");
        /**//**/ addI18nApplication(HelloWorld.class, "Hello World!", "Every Software Start with Hello World! (use BlankForm)");
        /**/ closeCategory();
        /**/ openI18nCategory("Image Form", "");
        /**//**/ addI18nApplication(HelloImage.class, "Hello Image", "ImageForm Demo");
        addSeparator();
        /**//**/ addI18nApplication(HelloLogo.class, "Hello Logo", "Hello Logo");
        /**/ closeCategory();
        /**/ openI18nCategory("Plain Text Form", "");
        /**//**/ addI18nApplication(HelloText.class, "Hello Plain Text", "Plain Text Form Demo");
        /**/ closeCategory();
        /**/ openI18nCategory("Styled Text Form", "");
        /**//**/ addI18nApplication(HelloStyledText.class, "Hello Styled Text Demo (HTML)", "show HTML documents");
        /**/ closeCategory();
        /**/ openI18nCategory("Menu Form", "");
        /**//**/ addI18nApplication(HelloMenu.class, "Hello Menu", "Menu Bar");
        /**/ closeCategory();

        addSeparator();
        addI18nApplication("Close", "Close", "");
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("Close".equals(name)) {
            cmd.form.setLastWindowsCheck(true);
            super.remove();
            
        }
    }
}
