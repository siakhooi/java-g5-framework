/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.mysoftwarehouse.is.conf.RESOURCE;
import com.mysoftwarehouse.util.InputStream2String;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ISAT_EULA extends TextForm2 {

    @Override
    protected void init() {
        String path = RESOURCE.DEFAULT_EULA_FILE;
        InputStream is = ISAT_EULA.class.getResourceAsStream(path);
        String text = InputStream2String.convert(is);
        super.setEditable(false);
        super.setText(text);
        super.setLineWrap(false);
        super.setRows(20);
        super.setColumns(80);
    }
}
