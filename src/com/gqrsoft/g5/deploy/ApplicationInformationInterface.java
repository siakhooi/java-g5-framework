/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.deploy;

import javax.swing.ImageIcon;

/**
 * contains detail of application to be read by users.
 * @author Ng Siak Hooi
 */
public interface ApplicationInformationInterface {

    public String getApplicationName();

    public String getApplicationCopyright();

    public String getApplicationDescription();

    public String getCompanyName();

    public String getCompanyWebsite();

    public String getApplicationWebsite();

    public ImageIcon getApplicationIcon();

    public ImageIcon getApplicationLogo();

    public int getApplicationVersionMajor();

    public int getApplicationVersionMinor();

    public int getApplicationVersionRevision();

    public int getApplicationVersionBuild();
}
