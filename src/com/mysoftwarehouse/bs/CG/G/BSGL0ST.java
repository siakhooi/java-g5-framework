/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.ST.ST;
import com.mysoftwarehouse.bs.db.ST.STD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGL0ST extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Trm", "BSST.Trm");
        super.setFieldHelpMessage("BSST.Trm.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSST.Nme");
        super.setFieldHelpMessage("BSST.Nme.help");

        super.addBooleanField("ForQtt", "BSST.ForQtt");
        super.setFieldHelpMessage("BSST.ForQtt.help");

        super.addBooleanField("ForInv", "BSST.ForInv");
        super.setFieldHelpMessage("BSST.ForInv.help");

        super.addBooleanField("ForRcp", "BSST.ForRcp");
        super.setFieldHelpMessage("BSST.ForRcp.help");

        super.addTextField("Status", "BSST.Status");
        super.setFieldHelpMessage("BSST.Status.help");
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
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(BSGS0ST.class);
        super.setCopyForm(BSGS0ST.class);
        super.setEditForm(BSGS0ST.class);
        super.setViewForm(BSGS0ST.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSGL0ST";
            ST.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSGL0ST.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSGL0ST.error.description");
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
        String Trm = cmd.list.getString(selectedRow, "Trm");
        cmd.out.map.texts.put(MAP.BSST.TRM, Trm);
        cmd.out.stringValue = Trm;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        try {
            String Cmp = GET.Cmp(this);
            String Trm = cmd.list.getString(selectedRow, "Trm");
            cmd.db.begin();
            ST.delete(this, Cmp, Trm);
            STD.delete(this, Cmp, Trm);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSGL0ST.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSGL0ST.error.delete.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "BSGL0ST.title";
    }
}
