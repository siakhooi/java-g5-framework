/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PFAT_EULA extends TextForm2 {

    @Override
    protected void init() {
        String path = RESOURCE.DEFAULT_EULA_FILE;
        InputStream is = PFAT_EULA.class.getResourceAsStream(path);
        String text = cmd.data.inputStream2String(is);
        super.setEditable(false);
        super.setText(text);
        super.setLineWrap(false);
        super.setRows(20);
        super.setColumns(80);
    }
}
