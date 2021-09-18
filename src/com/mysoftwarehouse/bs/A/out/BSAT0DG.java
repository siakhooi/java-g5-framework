/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A.out;

import com.gqrsoft.g5.developer.form.PlainTextForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAT0DG extends PlainTextForm {

    @Override
    protected void init() {
        super.setEditable(false);
        super.setLineWrap(false);
        super.setRows(20);
        super.setColumns(80);
        
        //text, read from dongle
        String text="";
        super.setText(text);
    }

    @Override
    protected void save(String arg0) {
    }

    @Override
    public String getFormTitle() {
        return "BSAT0DG.title";
    }
}
