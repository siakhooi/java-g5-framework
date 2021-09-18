/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ActionForm_Close;

/**
 *
 * @author Ng Siak Hooi
 */
public class AboutSystemPropertiesForm extends TextForm2 {

    private void a(String label, String value) {
        cmd.linewrite.print(label);
        cmd.linewrite.print(": ");
        cmd.linewrite.println(value);
    }

    @Override
    protected void init() {
        super.setEditable(false);
        super.setColumns(80);
        super.setLineWrap(false);
        super.setRows(20);
        cmd.linewrite.init();
        a("crlf", cmd.sysprop.crlf());
        a("tempDirectory", cmd.sysprop.tempDirectory());
        a("fileSeparator", cmd.sysprop.fileSeparator());
        a("pathSeparator", cmd.sysprop.pathSeparator());
        a("lineSeparator", cmd.sysprop.lineSeparator());

        a("userHome", cmd.sysprop.userHome());
        a("userDirectory", cmd.sysprop.userDirectory());
        a("userName", cmd.sysprop.userName());
        a("userLanguage", cmd.sysprop.userLanguage());
        a("userTimezone", cmd.sysprop.userTimezone());
        a("userRegion", cmd.sysprop.userRegion());

        a("javaClassVersion", cmd.sysprop.javaClassVersion());
        a("javaClassPath", cmd.sysprop.javaClassPath());
        a("javaLibraryPath", cmd.sysprop.javaLibraryPath());
        a("javaVersion", cmd.sysprop.javaVersion());
        a("javaVendor", cmd.sysprop.javaVendor());
        a("javaVendorUrl", cmd.sysprop.javaVendorUrl());
        a("javaHome", cmd.sysprop.javaHome());

        a("javaVMSpecificationVersion", cmd.sysprop.javaVMSpecificationVersion());
        a("javaVMSpecificationVendor", cmd.sysprop.javaVMSpecificationVendor());
        a("javaVMSpecificationName", cmd.sysprop.javaVMSpecificationName());
        a("javaVMVersion", cmd.sysprop.javaVMVersion());
        a("javaVMVendor", cmd.sysprop.javaVMVendor());
        a("javaVMName", cmd.sysprop.javaVMName());

        a("javaRuntimeSpecificationVersion", cmd.sysprop.javaRuntimeSpecificationVersion());
        a("javaRuntimeSpecificationVendor", cmd.sysprop.javaRuntimeSpecificationVendor());
        a("javaRuntimeSpecificationName", cmd.sysprop.javaRuntimeSpecificationName());

        a("osName", cmd.sysprop.osName());
        a("osArch", cmd.sysprop.osArch());
        a("osVersion", cmd.sysprop.osVersion());
        a("javaCompiler", cmd.sysprop.javaCompiler());
        a("javaExtDirs", cmd.sysprop.javaExtDirs());

        super.setText(cmd.linewrite.getString());
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        String title = "AboutSystemPropertiesForm.title";
        return cmd.lang.getSystemString(title);
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_Close.class;
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
