/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m30.tab;

import com.gqrsoft.g5.developer.form.TabForm;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloImage;
import com.mysoftwarehouse.toolkit.demo.m20.styledtext.HelloStyledText;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloLogo;
import com.mysoftwarehouse.toolkit.demo.m20.text.HelloText;
import com.mysoftwarehouse.toolkit.demo.m30.menu.HelloMenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloTab extends TabForm {

    @Override
    public void buildTabForm() {
        this.addI18nTab("HelloImage",
                new HelloImage(), null, "HelloImage demo");
        this.addI18nTab("HelloLogo",
                new HelloLogo(), null, "HelloLogo demo");
        this.addI18nTab("HelloMenu",
                new HelloMenu(), null, "HelloLogo demo");
        this.addI18nTab("HelloPlainText",
                new HelloText(), null, "HelloPlainText demo");
        this.addI18nTab("HelloStyledText",
                new HelloStyledText(), null, "HelloStyledText demo");
    }


    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Hello Tab";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
