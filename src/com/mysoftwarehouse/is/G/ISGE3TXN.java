/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.G;

import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISGE3TXN extends ISGE_TXN {

    @Override
    protected TxnTyp getTxnTyp() {
        return TxnTyp.X;
    }

    @Override
    public void initValue2() throws EntryFieldWrongDataTypeException {
        cmd.entry.setEditable("FrmLoc", true);
        cmd.entry.setMandatory("FrmLoc", true);
        cmd.entry.setEditable("ToLoc", true);
        cmd.entry.setMandatory("ToLoc", true);
        cmd.entry.setEditable("TxnCst", false);
        cmd.entry.setValue("FrmLoc", "");
        cmd.entry.setValue("ToLoc", "");
    }

    @Override
    public String getFormTitle() {
        return "ISGE3TXN.title";
    }
}
