/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.E.BSEL1SA;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SaEnum;
import com.mysoftwarehouse.bs.db.QTT.QTTJ;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_Amt;
import com.mysoftwarehouse.bs.db.SA.SA;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHS0QTTJ extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.copy = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setEditable("Adj", true);
            cmd.entry.setEditable("Nme", false);
            cmd.entry.setEditable("Typ", false);
            cmd.entry.setEditable("Prio", false);
            cmd.entry.setEditable("Amt", false);
        }
        if (super.currentMode == super.currentMode.EDIT) {
            cmd.entry.setEditable("Adj", false);
            cmd.entry.setEditable("Nme", true);
            cmd.entry.setEditable("Typ", true);
            cmd.entry.setEditable("Prio", true);
            cmd.entry.setEditable("Amt", true);
            cmd.entry.setMandatory("Prio", true);
            cmd.entry.setMandatory("Amt", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String QttNo = cmd.entry.getString("QttNo");
        String Adj = cmd.entry.getString("Adj");

        try {
            cmd.db.begin();
            QTTJ.insert(this, Cmp, QttNo, Adj); //, Nme, Typ, Prio, Amt);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String QttNo = cmd.entry.getString("QttNo");
        int Seq = cmd.entry.getInteger("Seq");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        int Prio = cmd.entry.getInteger("Prio");
        BigDecimal Amt = cmd.entry.getBigDecimal("Amt");

        try {
            cmd.db.begin();
            QTTJ.update(this, Cmp, QttNo, Seq, Nme, Typ, Prio, Amt);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String QttNo = cmd.entry.getString("QttNo");
        int Seq = cmd.in.intValue;
        try {
            cmd.db.begin();
            QTTJ.delete(this, Cmp, QttNo, Seq);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
        int seq = cmd.in.intValue;

        String rsName = "BSHS0QTTJ.rss";
        QTTJ.select(this, rsName, cmp, QttNo, seq);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("QttNo", QttNo);
        cmd.entry.setValue("Seq", seq);
        cmd.entry.setValue("Adj", cmd.db.getString(rsName, "Adj"));
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Typ", cmd.db.getString(rsName, "Typ"));
        cmd.entry.setValue("Prio", cmd.db.getInteger(rsName, "Prio"));
        cmd.entry.setValue("Amt", cmd.db.getBigDecimal(rsName, "Amt"));
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("QttNo", QttNo);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Adj")) {
            try {
                String cmp = GET.Cmp(this);
                String Adj = cmd.entry.getString(fieldName);

                boolean editMode = super.currentMode == super.currentMode.EDIT;
                boolean viewMode = super.currentMode == super.currentMode.VIEW;
                if (editMode || viewMode) {
                    return true;
                }
                boolean i = SA.hasSA(this, cmp, Adj);
                if (!i) {
                    String title = "SA.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSHS0QTTJ.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSHS0QTTJ.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("QttNo", "BSQTT.QttNo");
        super.setFieldHelpMessage("BSQTT.QttNo.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSHL0QTT.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addNumericField("Seq", "BSQTTJ.Seq");
        super.setFieldHelpMessage("BSQTTJ.Seq.help");
        super.setFieldEditable(false);
        super.setFieldTextColumn(5);
        super.setFieldMaximumLength(5);

        super.addTab("general", "BSHS0QTTJ.tab.general");
        super.addTextField("Adj", "BSQTTJ.Adj");
        super.setFieldHelpMessage("BSQTTJ.Adj.help");
        super.setFieldEditable(false);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSEL1SA.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Nme", "BSQTTJ.Nme");
        super.setFieldHelpMessage("BSQTTJ.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "BSQTTJ.Typ");
        super.setFieldHelpMessage("BSQTTJ.Typ.help");
        for (SaEnum.Type i : SaEnum.Type.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addNumericField("Prio", "BSQTTJ.Prio");
        super.setFieldHelpMessage("BSQTTJ.Prio.help");
        super.setFieldTextColumn(2);
        super.setFieldMaximumLength(2);
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSQTTJ.Amt");
        super.setFieldHelpMessage("BSQTTJ.Amt.help");
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

//        super.addNumericField("EffAmt", "BSQTTJ.EffAmt");
//        super.setFieldHelpMessage("BSQTTJ.EffAmt.help");
//        super.setFieldEditable(false);
//        super.setFieldTextColumn(20);
//        super.setFieldMaximumLength(20);
//        super.setFieldOutputFormat("#0.00");
//
//        super.addNumericField("OpenAmt", "BSQTTJ.OpenAmt");
//        super.setFieldHelpMessage("BSQTTJ.OpenAmt.help");
//        super.setFieldEditable(false);
//        super.setFieldTextColumn(20);
//        super.setFieldMaximumLength(20);
//        super.setFieldOutputFormat("#0.00");
//
//        super.addNumericField("NewAmt", "BSQTTJ.NewAmt");
//        super.setFieldHelpMessage("BSQTTJ.NewAmt.help");
//        super.setFieldEditable(false);
//        super.setFieldTextColumn(20);
//        super.setFieldMaximumLength(20);
//        super.setFieldOutputFormat("#0.00");
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSHS0QTTJ.title";
    }
}
