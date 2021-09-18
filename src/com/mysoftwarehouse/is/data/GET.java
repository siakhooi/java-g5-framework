/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.data;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class GET {

    public static String Whs(UserFormInterface form) {
        return form.cmd().global.texts.get(MAP.ISWHS.WHS);
    }

    public static String CstTyp(UserFormInterface form) {
        return form.cmd().global.texts.get(MAP.ISWHS.CSTTYP);
    }
}
