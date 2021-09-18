/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.styledtext;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloStyledText extends StyledTextForm {

    @Override
    public String getInitialText() {
        String s = "";
        s += "<HTML><BODY>";
        for (int i = 0; i < 5; i++) {
            s += "<A HREF=\"" + i + "\">TESTING" + i + "</A><BR>";
        }
        s += "</BODY></HTML>";
        return s;
    }

    @Override
    public void hyperlinkClick(String href) {
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION,
                href, "You click: " + href);
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Hello Styled Text";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
