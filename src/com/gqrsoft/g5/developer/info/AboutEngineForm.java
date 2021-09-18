/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.EngineResource;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class AboutEngineForm extends StyledTextForm {

    @Override
    public String getInitialText() {
        String f = EngineResource.ABOUT_ENGINE_TEMPLATE;
        InputStream is = EngineResource.class.getResourceAsStream(f);
        byte[] data = null;
        try {
            data = cmd.data.inputStream2ByteArray(is);
        } catch (IOException ex) {
        }
        if (data == null) {
            return "";
        }
        String htmlText = new String(data);
        String name = "", value = "";
        name = "AboutEngineForm.title";
        value = EngineConfiguration.ENGINE_TITLE;
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutEngineForm.Close.title";
        value = "";
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutEngineForm.Close.label";
        value = "AboutEngineForm.close";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        return htmlText;
    }

    @Override
    public void hyperlinkClick(String href) {
        cmd.form.closeForm();
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormI18nTitle() {
        String title = "AboutEngineForm.title";
        return cmd.lang.getSystemString(title);
    }

    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    protected boolean showStatus() {
        return false;
    }
}
