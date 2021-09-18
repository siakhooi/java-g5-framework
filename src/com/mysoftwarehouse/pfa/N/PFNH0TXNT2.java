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
public class PFNH0TXNT2 extends PFNH_ {

    @Override
    public InputStream getResourceInputStream() {
        return PFNH0TXNT2.class.getResourceAsStream(
                RESOURCE.PFNH0TXNT2_HTML_FILE);
    }
}
