/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.B;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTBH0 extends StyledTextForm {

    @Override
    public String getInitialText() {
        return "A tool that traverse all subdirectories of selected Directory, <BR>" +
                "rename all files at d1/d2/d3/d4/file to d1.d2.d3.d4.file, <BR>"+"" +
                "remove out all the intermediate directories.";
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
