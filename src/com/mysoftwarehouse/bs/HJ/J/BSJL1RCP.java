/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJL1RCP extends BSJL0RCP {

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
        String RcpNo = cmd.list.getString(selectedRow, "RcpNo");
        //cmd.in.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.in.stringValue = RcpNo;
    }

    @Override
    public String getFormTitle() {
        return "BSJL1RCP.title";
    }
}
