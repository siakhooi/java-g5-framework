/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.E;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTEH0 extends StyledTextForm {

    @Override
    public String getInitialText() {
        return "A tool that traverse all subdirectories of selected Directory, <BR>" +
                "renamed files in each subdirectories to follow the sequence number <BR>" +
                "start from 1, 2, 3, ... <BR>" +
                "If the number of files is < 10, files will be renumbered to single digits, <BR>" +
                "if the number of files is < 100, files will be renumbered to 2 digits, and so on.";
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
