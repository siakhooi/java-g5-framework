/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.Z_postpone;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;

/**
 * this class is postponed to next version: Database Information
 * @author Ng Siak Hooi
 */
public class PFHH0DB extends StyledTextForm {

    @Override
    public String getInitialText() {
        return "Under Construction";
    }

    @Override
    public void hyperlinkClick(String arg0) {
        cmd.form.closeForm();
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "PFHH0DB.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
