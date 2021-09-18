/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.dist.rockey;

import com.gqrsoft.g5.deploy.ApplicationInformationInterface;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationInformation implements ApplicationInformationInterface {

    @Override
    public String getApplicationName() {
        return "application.name";
    }

    @Override
    public String getApplicationCopyright() {
        return "application.copyright";
    }

    @Override
    public String getApplicationDescription() {
        return "application.description";
    }

    @Override
    public String getCompanyName() {
        return "application.company";
    }

    public String getCompanyWebsite() {
        return "http://www.mysoftwarehouse.com";
    }

    @Override
    public String getApplicationWebsite() {
        return "http://www.mysoftwarehouse.com";
    }

    @Override
    public ImageIcon getApplicationIcon() {
        return createImageIcon("/com/mysoftwarehouse/bs/resources/logo.png");
//        return createImageIcon("/com/mysoftwarehouse/demo/icon.jpg");
    }

    @Override
    public ImageIcon getApplicationLogo() {
        return createImageIcon("/com/mysoftwarehouse/bs/resources/logo.png");
    }

    @Override
    public int getApplicationVersionMajor() {
        return 1;
    }

    @Override
    public int getApplicationVersionMinor() {
        return 0;
    }

    @Override
    public int getApplicationVersionRevision() {
        return 0;
    }

    @Override
    public int getApplicationVersionBuild() {
        return 1;
    }

    private ImageIcon createImageIcon(String jarPath) {
        java.net.URL imgURL =
                ApplicationInformation.class.getResource(jarPath);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + jarPath);
            return null;
        }
    }
}
