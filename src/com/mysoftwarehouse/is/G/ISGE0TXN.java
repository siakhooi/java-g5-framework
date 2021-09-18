/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.G;

import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;
import java.math.BigDecimal;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISGE0TXN extends ISGE_TXN {

    @Override
    protected TxnTyp getTxnTyp() {
        return TxnTyp.I;
    }

    @Override
    public void initValue2() throws EntryFieldWrongDataTypeException {
        cmd.entry.setEditable("FrmLoc", false);
        cmd.entry.setEditable("ToLoc", true);
        cmd.entry.setMandatory("ToLoc", true);
        cmd.entry.setEditable("TxnCst", true);
        cmd.entry.setMandatory("TxnCst", true);
        cmd.entry.setValue("ToLoc", "");
        cmd.entry.setValue("TxnCst", BigDecimal.ZERO);
    }

    @Override
    public String getFormTitle() {
        return "ISGE0TXN.title";
    }
}
