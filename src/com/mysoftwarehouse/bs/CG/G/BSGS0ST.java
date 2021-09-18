/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.StEnum;
import com.mysoftwarehouse.bs.db.ST.ST;
import com.mysoftwarehouse.bs.db.ST.STD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGS0ST extends ModeEntryForm {

    private String CopyTrm = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("details", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("details", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Trm = cmd.entry.getString("Trm");
        String Nme = cmd.entry.getString("Nme");
        String ForQtt = cmd.entry.getString("ForQtt");
        String ForInv = cmd.entry.getString("ForInv");
        String ForRcp = cmd.entry.getString("ForRcp");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            ST.insert(this, Cmp, Trm, Nme, ForQtt, ForInv, ForRcp, Status);
            if (!cmd.data.isNull(CopyTrm)) {
                STD.copyFrmSt(this, Cmp, Trm, CopyTrm);
            }

            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Trm = cmd.entry.getString("Trm");
        String Nme = cmd.entry.getString("Nme");
        String ForQtt = cmd.entry.getString("ForQtt");
        String ForInv = cmd.entry.getString("ForInv");
        String ForRcp = cmd.entry.getString("ForRcp");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            ST.update(this, Cmp, Trm, Nme, ForQtt, ForInv, ForRcp, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Trm = cmd.entry.getString("Trm");
        try {
            cmd.db.begin();
            ST.delete(this, Cmp, Trm);
            STD.delete(this, Cmp, Trm);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSGS0ST.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSGS0ST.error.delete.description");
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String trm = cmd.in.map.texts.get(MAP.BSST.TRM);
        CopyTrm = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyTrm = trm;
        }

        String rsName = "BSGS0ST";
        ST.select(this, rsName, cmp, trm);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Trm", trm);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("ForQtt", cmd.db.getString(rsName, "ForQtt"));
        cmd.entry.setValue("ForInv", cmd.db.getString(rsName, "ForInv"));
        cmd.entry.setValue("ForRcp", cmd.db.getString(rsName, "ForRcp"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
    }

    @Override
    public void loadDefault() throws Exception {
        CopyTrm = "";
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
    }

    @Override
    public boolean verify(String fieldName) {
        if (super.currentMode == super.currentMode.ADD) {
            if ("Trm".equals(fieldName)) {
                try {
                    String Cmp = GET.Cmp(this);
                    String Trm = cmd.entry.getString(fieldName);
                    if (ST.hasST(this, Cmp, Trm)) {
                        String title = "ST.error.Exist";
                        cmd.entry.setStatus(StatusType.ERROR, title);
                        return false;
                    }
                } catch (SQLException ex) {
                    String title = "BSGS0ST.error";
                    cmd.log.severe(title, ex);
                    return false;
                } catch (EntryFieldWrongDataTypeException ex) {
                    String title = "BSGS0ST.error";
                    cmd.log.severe(title, ex);
                    return false;
                }
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Trm", "BSST.Trm");
        super.setFieldHelpMessage("BSST.Trm.help");
        super.setFieldSelectForm(BSGL4ST.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSGS0ST.tab.general");
        super.addTextField("Nme", "BSST.Nme");
        super.setFieldHelpMessage("BSST.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addBooleanField("ForQtt", "BSST.ForQtt");
        super.setFieldHelpMessage("BSST.ForQtt.help");

        super.addBooleanField("ForInv", "BSST.ForInv");
        super.setFieldHelpMessage("BSST.ForInv.help");

        super.addBooleanField("ForRcp", "BSST.ForRcp");
        super.setFieldHelpMessage("BSST.ForRcp.help");

        super.addRadioboxField("Status", "BSST.Status");
        super.setFieldHelpMessage("BSST.Status.help");
        for (StEnum.Status i : StEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("details", "BSGS0ST.tab.details", BSGL0STD.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSGS0ST.title";
    }
}
