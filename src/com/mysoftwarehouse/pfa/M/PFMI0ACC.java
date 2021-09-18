/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.M;

import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFMI0ACC extends PFMI_ {
    @Override
    protected String getURL() { return null;    }
    @Override
    public Image getImage() { return null; }
/*
    @Override
    protected String getURL() {
        return RESOURCE.ACC_COMMERCIAL_URL;
    }

    @Override
    public Image getImage() {
        return cmd.image.createImageIcon(
                this.getClass(),
                RESOURCE.ACC_COMMERCIAL_FILE).getImage();
    }
	*/
}
