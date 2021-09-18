/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.DataType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGL0ACC extends ListForm {

    @Override
    public DataType getDataType() {
        return DataType.VECTOR;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.MULTIPLE_INTERVAL;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Acc", "PFGL0ACC.Acc");
        super.setFieldHelpMessage("PFGL0ACC.Acc.help");

    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "PFGL0ACC.Nme");
        super.setFieldHelpMessage("PFGL0ACC.Nme.help");

        super.addTextField("AccTyp", "PFGL0ACC.AccTyp");
        super.setFieldHelpMessage("PFGL0ACC.AccTyp.help");

        super.addTextField("Remark", "PFGL0ACC.Remark");
        super.setFieldHelpMessage("PFGL0ACC.Remark.help");
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

        sar = new SampleAccountResource();
        sar.init(this);
        sar.addAsset();
        sar.addLiability();
        sar.addIncome();
        sar.addExpenses();
    }
    private SampleAccountResource sar;

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        super.useVector(sar.getAll());
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        cmd.out.intValues = selectedRows;
        UserFormInterface f = cmd.form.create(PFGP2ACC.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "PFGL0ACC.title";
    }
}
