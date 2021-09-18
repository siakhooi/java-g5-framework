/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PsiEnum;
import com.mysoftwarehouse.bs.db.PSI.PSI;
import com.mysoftwarehouse.bs.db.PSI.PSID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQS0PSI extends ModeEntryForm {

    private String CopySpcInst = "";

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
        String SpcInst = cmd.entry.getString("SpcInst");
        String Nme = cmd.entry.getString("Nme");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            PSI.insert(this, Cmp, SpcInst, Nme, Status);
            if (!cmd.data.isNull(CopySpcInst)) {
                PSID.copyFrmPsi(this, Cmp, CopySpcInst, SpcInst);
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
        String SpcInst = cmd.entry.getString("SpcInst");
        String Nme = cmd.entry.getString("Nme");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            PSI.update(this, Cmp, SpcInst, Nme, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String SpcInst = cmd.entry.getString("SpcInst");
        try {
            cmd.db.begin();
            PSI.delete(this, Cmp, SpcInst);
            PSID.delete(this, Cmp, SpcInst);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSQS0PSI.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSQS0PSI.error.delete.description");
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String SpcInst = cmd.in.map.texts.get(MAP.BSPSI.PSI);
        CopySpcInst = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopySpcInst = SpcInst;
        }

        String rsName = "BSQS0PSI";
        PSI.select(this, rsName, cmp, SpcInst);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("SpcInst", SpcInst);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
    }

    @Override
    public void loadDefault() throws Exception {
        CopySpcInst = "";
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
    }

    @Override
    public boolean verify(String fieldName) {
        if (super.currentMode == super.currentMode.ADD) {
            if ("SpcInst".equals(fieldName)) {
                try {
                    String Cmp = GET.Cmp(this);
                    String SpcInst = cmd.entry.getString(fieldName);
                    if (PSI.hasPSI(this, Cmp, SpcInst)) {
                        String title = "PSI.error.Exist";
                        cmd.entry.setStatus(StatusType.ERROR, title);
                        return false;
                    }
                } catch (SQLException ex) {
                    String title = "BSQS0PSI.error";
                    cmd.log.severe(title, ex);
                    return false;
                } catch (EntryFieldWrongDataTypeException ex) {
                    String title = "BSQS0PSI.error";
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

        super.addTextField("SpcInst", "BSPSI.SpcInst");
        super.setFieldHelpMessage("BSPSI.SpcInst.help");
        super.setFieldSelectForm(BSQL1PSI.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSQS0PSI.tab.general");
        super.addTextField("Nme", "BSPSI.Nme");
        super.setFieldHelpMessage("BSPSI.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSPSI.Status");
        super.setFieldHelpMessage("BSPSI.Status.help");
        for (PsiEnum.Status i : PsiEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("details", "BSQS0PSI.tab.details", BSQL0PSID.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSQS0PSI.title";
    }
}
