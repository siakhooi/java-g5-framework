/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import com.mysoftwarehouse.bs.db.QTT.QTTA;
import com.mysoftwarehouse.bs.db.QTT.QTTC;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.bs.db.QTT.QTTJ;
import com.mysoftwarehouse.bs.db.QTT.QTTR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHL0QTT extends ListForm {

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
        super.addTextField("QttNo", "BSQTT.QttNo");
        super.setFieldHelpMessage("BSQTT.QttNo.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Cus", "BSQTT.Cus");
        super.setFieldHelpMessage("BSQTT.Cus.help");

        super.addCalendarField("QttDte", "BSQTT.QttDte");
        super.setFieldHelpMessage("BSQTT.QttDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addCalendarField("ExpDte", "BSQTT.ExpDte");
        super.setFieldHelpMessage("BSQTT.ExpDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addNumericField("TtlAmt", "BSQTT.TtlAmt");
        super.setFieldHelpMessage("BSQTT.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSQTT.Status");
        super.setFieldHelpMessage("BSQTT.Status.help");
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

        super.setAddForm(BSHS0QTT.class);
        super.setEditForm(BSHS0QTT.class);
        super.setViewForm(BSHS0QTT.class);
        super.setCopyForm(BSHS0QTT.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSHL0QTT";
            QTT.select_AllActive(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSHL0QTT.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSHL0QTT.error.description");
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
        String QttNo = cmd.list.getString(selectedRow, "QttNo");
        cmd.out.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.out.stringValue = QttNo;
    }

    @Override
    public void executeDelete(int selectedRow, int[] arg1) throws Exception {
        String Cmp = GET.Cmp(this);
        String QttNo = cmd.list.getString(selectedRow, "QttNo");
        cmd.db.begin();
        QTT.delete(this, Cmp, QttNo);
        QTTD.delete(this, Cmp, QttNo);
        QTTE.delete(this, Cmp, QttNo);
        QTTA.delete(this, Cmp, QttNo);
        QTTC.delete(this, Cmp, QttNo);
        QTTJ.delete(this, Cmp, QttNo);
        QTTR.delete(this, Cmp, QttNo);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSHL0QTT.title";
    }
}
