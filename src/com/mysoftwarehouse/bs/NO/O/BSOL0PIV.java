/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import com.mysoftwarehouse.bs.db.PIV.PIVA;
import com.mysoftwarehouse.bs.db.PIV.PIVC;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOL0PIV extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI0.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("PivNo", "BSPIV.PivNo");
        super.setFieldHelpMessage("BSPIV.PivNo.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Sup", "BSPIV.Sup");
        super.setFieldHelpMessage("BSPIV.Sup.help");

        super.addCalendarField("PivDte", "BSPIV.PivDte");
        super.setFieldHelpMessage("BSPIV.PivDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addTextField("PayTyp", "BSPIV.PayTyp");
        super.setFieldHelpMessage("BSPIV.PayTyp.help");

        super.addNumericField("TtlAmt", "BSPIV.TtlAmt");
        super.setFieldHelpMessage("BSPIV.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSPIV.Status");
        super.setFieldHelpMessage("BSPIV.Status.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(BSOS0PIV.class);
        super.setEditForm(BSOS0PIV.class);
        super.setViewForm(BSOS0PIV.class);
        super.setCopyForm(BSOS0PIV.class);
        super.buttons.addButton("genFrmPor", "BSOL0PIV.genFrmPor",
                cmd.icon.getSearchIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("genFrmPor".equals(name)) {
            BSOP0PIV f = new BSOP0PIV();
            cmd.form.execute(f);
            cmd.list.refreshList();
        } else {
            super.buttonClick(name, selectedRow, selectedRows);
        }
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSOL0PIV";
            PIV.selectAll(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSOL0PIV.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSOL0PIV.error.description");
        }

    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String PivNo = cmd.list.getString(selectedRow, "PivNo");
        cmd.out.map.texts.put(MAP.BSPIV.PIVNO, PivNo);
        cmd.out.stringValue = PivNo;
    }

    @Override
    public void executeDelete(int selectedRow, int[] arg1) throws Exception {
        String Cmp = GET.Cmp(this);
        String PivNo = cmd.list.getString(selectedRow, "PivNo");
        cmd.db.begin();
        PIV.delete(this, Cmp, PivNo);
        PIVA.delete(this, Cmp, PivNo);
        PIVC.delete(this, Cmp, PivNo);
        PIVD.delete(this, Cmp, PivNo);
        PIVE.delete(this, Cmp, PivNo);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSOL0PIV.title";
    }
}
