/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum.BuiltInApplicationLanguage;
import com.mysoftwarehouse.bs.A.BSAT0EULA;
import com.mysoftwarehouse.bs.conf.GLOBAL;
import com.mysoftwarehouse.bs.conf.INI;
import com.mysoftwarehouse.bs.conf.RESOURCE;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBH0CMP extends StyledTextForm {

    final String LABEL_GLOBAL_APP_PATH = "%APP_PATH%";

    public InputStream getResourceInputStream() {
        String lg = cmd.lang.getCurrentLanguageCode();
        if (BuiltInApplicationLanguage.MS_MY.code.equals(lg)) {
            return BSBH0CMP.class.getResourceAsStream(
                    RESOURCE.BSBH0CMP_MS_MY_HTML_FILE);
        } else {
            return BSBH0CMP.class.getResourceAsStream(
                    RESOURCE.BSBH0CMP_EN_US_HTML_FILE);
        }
    }

    @Override
    public String getInitialText() {
        String s = "";
        try {
            InputStream in = getResourceInputStream();
            byte[] data = cmd.data.inputStream2ByteArray(in);
            s = new String(data);
        } catch (IOException ex) {
            cmd.log.severe("BSBH0CMP.error", ex);
            s = cmd.lang.getString("BSBH0CMP.error");
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
    protected boolean showStatus() {
        return false;
    }

    protected String process(String s) {
        String label = LABEL_GLOBAL_APP_PATH;
        String value = cmd.global.texts.get(GLOBAL.APP_PATH);
//        value = cmd.codec.quotedHTMLEncode(value);
        int i = s.indexOf(label);
        String t = s.substring(0, i) + value + s.substring(i + label.length());
        return t;
    }

    @Override
    public void hyperlinkClick(String name) {
        if ("eula".equals(name)) {
            UserFormInterface f = cmd.form.create(BSAT0EULA.class);
            cmd.form.executeInNewThread(f);
        } else if ("lang".equals(name)) {
            cmd.lang.selectLanguage();
            String s = cmd.lang.getCurrentLanguageCode();
            INI.writeLanguage(this, s);
            UserFormInterface f = cmd.form.create(BSBL0CMP.class);
            cmd.form.executeInNewThread(f);
            cmd.form.closeForm();
        }
    }
}
