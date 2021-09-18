/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.N;

import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFNH1ACC extends PFNH_ {

    @Override
    public InputStream getResourceInputStream() {
        return PFNH1ACC.class.getResourceAsStream(
                RESOURCE.PFNH1ACC_HTML_FILE);
    }
}
