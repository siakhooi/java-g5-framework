/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCL1CUS extends BSCL0CUS {

    @Override
    protected void buildGeneral() {
        super.buildGeneral();
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String cus = cmd.list.getString(selectedRow, "Cus");
        cmd.in.stringValue = cus;
    }

    @Override
    public String getFormTitle() {
        return "BSCL1CUS.title";
    }
}
