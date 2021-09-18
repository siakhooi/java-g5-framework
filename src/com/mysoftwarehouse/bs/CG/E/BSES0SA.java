/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.E;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SaEnum;
import com.mysoftwarehouse.bs.db.SA.SA;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSES0SA extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Adj = cmd.entry.getString("Adj");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        int Prio = cmd.entry.getInteger("Prio");
        BigDecimal Amt = cmd.entry.getBigDecimal("Amt");
        String Dflt = cmd.entry.getString("Dflt");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            SA.insert(this, Cmp, Adj, Nme, Typ, Prio, Amt, Dflt, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Adj = cmd.entry.getString("Adj");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        int Prio = cmd.entry.getInteger("Prio");
        BigDecimal Amt = cmd.entry.getBigDecimal("Amt");
        String Dflt = cmd.entry.getString("Dflt");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            SA.update(this, Cmp, Adj, Nme, Typ, Prio, Amt, Dflt, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Adj = cmd.entry.getString("Adj");
        try {
            cmd.db.begin();
            SA.delete(this, Cmp, Adj);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSES0SA.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSES0SA.error.delete.description");
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String adj = cmd.in.map.texts.get(MAP.BSSA.ADJ);

        String rsName = "BSES0SA";
        SA.select(this, rsName, cmp, adj);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Adj", adj);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Typ", cmd.db.getString(rsName, "Typ"));
        cmd.entry.setValue("Prio", cmd.db.getBigDecimal(rsName, "Prio"));
        cmd.entry.setValue("Amt", cmd.db.getBigDecimal(rsName, "Amt"));
        cmd.entry.setValue("Dflt", cmd.db.getString(rsName, "Dflt"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
    }

    @Override
    public boolean verify(String fieldName) {
        if (super.currentMode == super.currentMode.ADD) {
            if ("Adj".equals(fieldName)) {
                try {
                    String Cmp = GET.Cmp(this);
                    String Adj = cmd.entry.getString(fieldName);
                    if (SA.hasSA(this, Cmp, Adj)) {
                        String title = "SA.error.Exist";
                        cmd.entry.setStatus(StatusType.ERROR, title);
                        return false;
                    }
                } catch (SQLException ex) {
                    String title = "BSES0SA.error";
                    cmd.log.severe(title, ex);
                    return false;
                } catch (EntryFieldWrongDataTypeException ex) {
                    String title = "BSES0SA.error";
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
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Adj", "BSSA.Adj");
        super.setFieldHelpMessage("BSSA.Adj.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSEL1SA.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSES0SA.tab.general");
        super.addTextField("Nme", "BSSA.Nme");
        super.setFieldHelpMessage("BSSA.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "BSSA.Typ");
        super.setFieldHelpMessage("BSSA.Typ.help");
        for (SaEnum.Type i : SaEnum.Type.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addNumericField("Prio", "BSSA.Prio");
        super.setFieldHelpMessage("BSSA.Prio.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(2);
        super.setFieldMaximumLength(2);
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSSA.Amt");
        super.setFieldHelpMessage("BSSA.Amt.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addBooleanField("Dflt", "BSSA.Dflt");
        super.setFieldHelpMessage("BSSA.Dflt.help");

        super.addRadioboxField("Status", "BSSA.Status");
        super.setFieldHelpMessage("BSSA.Status.help");
        for (SaEnum.Status i : SaEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSES0SA.title";
    }
}
