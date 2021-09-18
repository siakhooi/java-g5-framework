/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBL1WHS extends ISBL0WHS {
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
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String whs = cmd.list.getString(selectedRow, "Whs");
        cmd.in.stringValue = whs;
    }

    @Override
    public String getFormTitle() {
        return "ISBL1WHS.title";
    }
}
