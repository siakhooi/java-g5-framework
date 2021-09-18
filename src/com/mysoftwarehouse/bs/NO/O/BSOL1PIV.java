/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOL1PIV extends BSOL0PIV {

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String PivNo = cmd.list.getString(selectedRow, "PivNo");
        //cmd.in.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.in.stringValue = PivNo;
    }

    @Override
    public String getFormTitle() {
        return "BSOL1PIV.title";
    }
}
