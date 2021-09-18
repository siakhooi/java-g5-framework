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
public class ISGE1TXN extends ISGE_TXN {

    @Override
    protected TxnTyp getTxnTyp() {
        return TxnTyp.O;
    }

    @Override
    public void initValue2() throws EntryFieldWrongDataTypeException {
        cmd.entry.setEditable("FrmLoc", true);
        cmd.entry.setMandatory("FrmLoc", true);
        cmd.entry.setEditable("ToLoc", false);
        cmd.entry.setEditable("TxnCst", false);
        cmd.entry.setValue("FrmLoc", "");
    }

    @Override
    public String getFormTitle() {
        return "ISGE1TXN.title";
    }
}
