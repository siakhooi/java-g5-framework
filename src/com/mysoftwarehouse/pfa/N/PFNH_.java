/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.N;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PFNH_ extends StyledTextForm {

    public abstract InputStream getResourceInputStream();

    protected String process(String s) {
        return s;
    }

    @Override
    public String getInitialText() {
        String s = "";
        try {
            InputStream in = getResourceInputStream();
            byte[] data = cmd.data.inputStream2ByteArray(in);
            s = new String(data);
        } catch (IOException ex) {
            cmd.log.severe("PFNH_.error", ex);
            s = cmd.lang.getString("PFNH_.error");
        }
        return process(s);
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
    public void onEscapeKeyPressed() {
    }

    @Override
    public void hyperlinkClick(String href) {
    }

    @Override
    protected boolean showStatus() {
        return false;
    }
}
