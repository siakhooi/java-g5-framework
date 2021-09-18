/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.mysoftwarehouse.is.data.TcdEnum;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFL2TCD extends ISFL_TCD {

    @Override
    protected TxnTyp getTxnTyp() {
        return TcdEnum.TxnTyp.I;
    }
}
