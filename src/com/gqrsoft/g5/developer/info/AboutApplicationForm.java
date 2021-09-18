/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.gqrsoft.g5.kernel.config.EngineResource;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class AboutApplicationForm extends StyledTextForm {

    @Override
    public String getInitialText() {
        String f = EngineResource.ABOUT_APPLICATION_TEMPLATE;
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
        name = "AboutApplicationForm.title";
        value = "AboutApplicationForm.title";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.ApplicationName.label";
        value = "AboutApplicationForm.ApplicationName.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.ApplicationName.value%";
        value = cmd.appinfo.getApplicationName();
        value = cmd.lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.ApplicationCopyright.label";
        value = "AboutApplicationForm.ApplicationCopyright.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.ApplicationCopyright.value%";
        value = cmd.appinfo.getApplicationCopyright();
        value = cmd.lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.ApplicationDescription.label";
        value = "AboutApplicationForm.ApplicationDescription.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.ApplicationDescription.value%";
        value = cmd.appinfo.getApplicationDescription();
        value = cmd.lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.ApplicationVersion.label";
        value = "AboutApplicationForm.ApplicationVersion.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.ApplicationVersion.value%";
        value = cmd.appinfo.getApplicationVersion();
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.ApplicationWebsite.label";
        value = "AboutApplicationForm.ApplicationWebsite.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.ApplicationWebsite.value%";
        value = cmd.appinfo.getApplicationWebsite();
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.CompanyName.label";
        value = "AboutApplicationForm.CompanyName.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.CompanyName.value%";
        value = cmd.appinfo.getCompanyName();
        value = cmd.lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.CompanyWebsite.label";
        value = "AboutApplicationForm.CompanyWebsite.label";
        value = cmd.lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = "%AboutApplicationForm.CompanyWebsite.value%";
        value = cmd.appinfo.getCompanyWebsite();
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.Close.title";
        value = "";
        htmlText = htmlText.replaceAll(name, value);

        name = "AboutApplicationForm.Close.label";
        value = "AboutApplicationForm.close";
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
        String title = "AboutApplicationForm.title";
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
