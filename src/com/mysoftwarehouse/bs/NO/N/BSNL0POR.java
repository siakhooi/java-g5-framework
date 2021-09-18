/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.POR.POR;
import com.mysoftwarehouse.bs.db.POR.PORA;
import com.mysoftwarehouse.bs.db.POR.PORC;
import com.mysoftwarehouse.bs.db.POR.PORD;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.bs.db.POR.PORS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNL0POR extends ListForm {

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
        super.addTextField("PorNo", "BSPOR.PorNo");
        super.setFieldHelpMessage("BSPOR.PorNo.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Sup", "BSPOR.Sup");
        super.setFieldHelpMessage("BSPOR.Sup.help");

        super.addCalendarField("PorDte", "BSPOR.PorDte");
        super.setFieldHelpMessage("BSPOR.PorDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addCalendarField("ShpDte", "BSPOR.ShpDte");
        super.setFieldHelpMessage("BSPOR.ShpDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addNumericField("TtlAmt", "BSPOR.TtlAmt");
        super.setFieldHelpMessage("BSPOR.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSPOR.Status");
        super.setFieldHelpMessage("BSPOR.Status.help");
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

        super.setAddForm(BSNS0POR.class);
        super.setEditForm(BSNS0POR.class);
        super.setViewForm(BSNS0POR.class);
        super.setCopyForm(BSNS0POR.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSNL0POR";
            POR.selectAll(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSNL0POR.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSNL0POR.error.description");
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
        String PorNo = cmd.list.getString(selectedRow, "PorNo");
        cmd.out.map.texts.put(MAP.BSPOR.PORNO, PorNo);
        cmd.out.stringValue = PorNo;
    }

    @Override
    public void executeDelete(int selectedRow, int[] arg1) throws Exception {
        String Cmp = GET.Cmp(this);
        String PorNo = cmd.list.getString(selectedRow, "PorNo");
        cmd.db.begin();
        POR.delete(this, Cmp, PorNo);
        PORA.delete(this, Cmp, PorNo);
        PORC.delete(this, Cmp, PorNo);
        PORD.delete(this, Cmp, PorNo);
        PORE.delete(this, Cmp, PorNo);
        PORS.delete(this, Cmp, PorNo);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSNL0POR.title";
    }
}
