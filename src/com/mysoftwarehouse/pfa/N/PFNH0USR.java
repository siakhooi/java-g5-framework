/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.N;

import com.mysoftwarehouse.pfa.N.*;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.AA.PFAT0EULA;
import com.mysoftwarehouse.pfa.conf.GLOBAL;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFNH0USR extends PFNH_ {

    final String LABEL_GLOBAL_APP_PATH = "%APP_PATH%";

    @Override
    protected String process(String s) {
        String label = LABEL_GLOBAL_APP_PATH;
        String value = cmd.global.texts.get(GLOBAL.APP_PATH);
//        value = cmd.codec.quotedHTMLEncode(value);
        int i = s.indexOf(label);
        String t = s.substring(0, i) + value + s.substring(i + label.length());
        return t;
    }

    @Override
    public InputStream getResourceInputStream() {
        return PFNH0USR.class.getResourceAsStream(
                RESOURCE.PFNH0USR_HTML_FILE);
    }

    @Override
    public void hyperlinkClick(String arg0) {
        //UserFormInterface f = cmd.form.create(PFHH0EULA.class);
        UserFormInterface f = cmd.form.create(PFAT0EULA.class);
        cmd.form.executeInNewThread(f);
    }
}
