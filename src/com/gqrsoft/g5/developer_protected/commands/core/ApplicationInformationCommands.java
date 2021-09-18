/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationInformationCommands extends AbstractCommandComponent {

    public String getApplicationName() {
        return getFormControl().core.info().getApplicationName();
    }

    public String getApplicationCopyright() {
        return getFormControl().core.info().getApplicationCopyright();
    }

    public String getApplicationDescription() {
        return getFormControl().core.info().getApplicationDescription();
    }

    public String getApplicationWebsite() {
        return getFormControl().core.info().getApplicationWebsite();
    }

    public ImageIcon getApplicationIcon() {
        return getFormControl().core.info().getApplicationIcon();
    }

    public ImageIcon getApplicationLogo() {
        return getFormControl().core.info().getApplicationLogo();
    }

    public int getApplicationVersionMajor() {
        return getFormControl().core.info().getApplicationVersionMajor();
    }

    public int getApplicationVersionMinor() {
        return getFormControl().core.info().getApplicationVersionMinor();
    }

    public int getApplicationVersionRevision() {
        return getFormControl().core.info().getApplicationVersionRevision();
    }

    public int getApplicationVersionBuild() {
        return getFormControl().core.info().getApplicationVersionBuild();
    }

    public final String getApplicationVersion() {
        return getApplicationVersionMajor() + "." +
                getApplicationVersionMinor() + "." +
                getApplicationVersionRevision() + "." +
                getApplicationVersionBuild();
    }

    public String getCompanyName() {
        return getFormControl().core.info().getCompanyName();
    }

    public String getCompanyWebsite() {
        return getFormControl().core.info().getCompanyWebsite();
    }
}
