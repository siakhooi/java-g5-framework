/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.ST.STD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGL0STD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
//        super.addNumericField("Seq", "BSSTD.Seq");
//        super.setFieldHelpMessage("BSSTD.Seq.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSSTD.Text");
        super.setFieldHelpMessage("BSSTD.Text.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, false);
        super.setActionEnable(ListActionType.RECORD_CHECK_ON_EDIT, false);

        super.setEditForm(BSGT0STD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String Trm = cmd.in.map.texts.get(MAP.BSST.TRM);
            String rsName = "BSGL0STD";
            STD.selectAll(this, rsName, Cmp, Trm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSGL0STD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSGL0STD.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String Trm = cmd.in.map.texts.get(MAP.BSST.TRM);
        cmd.out.map.texts.put(MAP.BSST.TRM, Trm);
        cmd.out.stringValue = Trm;
    }

    @Override
    public String getFormTitle() {
        return "BSGL0STD.title";
    }
}
