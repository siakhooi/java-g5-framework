/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.conf.RESOURCE;
import com.mysoftwarehouse.util.InputStream2String;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class BSAT_EULA extends TextForm {
    @Override
    protected void init() {
        String path = RESOURCE.DEFAULT_EULA_FILE;
        InputStream is = BSAT_EULA.class.getResourceAsStream(path);
        String text = InputStream2String.convert(is);
        super.setEditable(false);
        super.setText(text);
        super.setLineWrap(false);
        super.setRows(20);
        super.setColumns(80);
    }
}
