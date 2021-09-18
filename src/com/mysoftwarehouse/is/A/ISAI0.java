/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.mysoftwarehouse.is.conf.DONGLE;
import com.mysoftwarehouse.is.conf.RESOURCE;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAI0 extends ISAI_ {

    @Override
    protected String getURL() {
        return RESOURCE.IMG_800_URL;
    }

    @Override
    public Image getImage() {
        if (DONGLE.isDemoMode(this)) {
            return cmd.image.createImageIcon(
                    this.getClass(),
                    RESOURCE.IMG_800_FILE_D).getImage();
        } else {
            return cmd.image.createImageIcon(
                    this.getClass(),
                    RESOURCE.IMG_800_FILE_L).getImage();
        }
    }
}
