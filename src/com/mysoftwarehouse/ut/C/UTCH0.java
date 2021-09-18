/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.C;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTCH0 extends StyledTextForm {

    @Override
    public String getInitialText() {
        return " tool that traverse all subdirectories of selected Directory, <BR>"+
                "remove all empty subdirectories." ;
    }

    @Override
    public void hyperlinkClick(String arg0) {
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }
}
