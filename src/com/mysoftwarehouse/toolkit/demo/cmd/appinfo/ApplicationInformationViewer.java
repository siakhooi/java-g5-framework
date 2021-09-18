/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.cmd.appinfo;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ActionForm_Close;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationInformationViewer extends TextForm2 {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_Close.class;
    }

    private void add(String title, int n) {
        add(title, cmd.data.int2String(n));
    }

    private void add(String title, String text) {
        cmd.linewrite.print(title);
        cmd.linewrite.println(text);
    }

    private void addText(String title, String text) {
        add(title, cmd.lang.getString(text));
    }

    @Override
    protected void init() {
        super.setEditable(false);
        super.setRows(12);
        super.setColumns(80);
        super.setLineWrap(false);

        cmd.linewrite.init();
        addText("Application Name: ", cmd.appinfo.getApplicationName());
        addText("Application Copyright: ", cmd.appinfo.getApplicationCopyright());
        addText("Application Description: ", cmd.appinfo.getApplicationDescription());
        addText("Company Name: ", cmd.appinfo.getCompanyName());
        add("Application Website: ", cmd.appinfo.getApplicationWebsite());
        add("Application Major Version: ", cmd.appinfo.getApplicationVersionMajor());
        add("Application Minor Version: ", cmd.appinfo.getApplicationVersionMinor());
        add("Application Revision Version: ", cmd.appinfo.getApplicationVersionRevision());
        add("Application Build Version: ", cmd.appinfo.getApplicationVersionBuild());
        add("Application Version: ", cmd.appinfo.getApplicationVersion());

        super.setText(cmd.linewrite.getString());
    }

    @Override
    public String getFormTitle() {
        return "ApplicationInformationViewer.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
