/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class GET {

    public static String Cmp(UserFormInterface form) {
        return form.cmd().global.texts.get(MAP.BSCMP.CMP);
    }

    public static String CurCde(UserFormInterface form) {
        return form.cmd().global.texts.get(MAP.BSCMP.CURCDE);
    }

}
