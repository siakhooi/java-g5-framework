/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBL1CMP extends BSBL0CMP {
    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return null;
    }

    @Override
    public void eventAfterVisible() {
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows)  throws Exception{
        String cmp = cmd.list.getString(selectedRow, "Cmp");
        cmd.in.stringValue=cmp;
    }

    @Override
    public String getFormTitle() {
        return "BSBL1CMP.title";
    }
}
