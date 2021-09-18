/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.E.BSEL1SA;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SaEnum;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import com.mysoftwarehouse.bs.db.SA.SA;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJS0RCPJ extends ModeEntryForm {

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
        String RcpNo = cmd.entry.getString("RcpNo");
        String Adj = cmd.entry.getString("Adj");

        try {
            cmd.db.begin();
            RCPJ.insert(this, Cmp, RcpNo, Adj); //, Nme, Typ, Prio, Amt);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String RcpNo = cmd.entry.getString("RcpNo");
        int Seq = cmd.entry.getInteger("Seq");
        String Adj = cmd.entry.getString("Adj");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        int Prio = cmd.entry.getInteger("Prio");
        BigDecimal Amt = cmd.entry.getBigDecimal("Amt");

        try {
            cmd.db.begin();
            RCPJ.update(this, Cmp, RcpNo, Seq, Adj, Nme, Typ, Prio, Amt);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String RcpNo = cmd.entry.getString("RcpNo");
        int Seq = cmd.in.intValue;
        try {
            cmd.db.begin();
            RCPJ.delete(this, Cmp, RcpNo, Seq);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String RcpNo = cmd.in.map.texts.get(MAP.BSRCP.RCPNO);
        int seq = cmd.in.intValue;

        String rsName = "BSJS0RCPJ.rss";
        RCPJ.select(this, rsName, cmp, RcpNo, seq);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("RcpNo", RcpNo);
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
        String RcpNo = cmd.in.map.texts.get(MAP.BSRCP.RCPNO);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("RcpNo", RcpNo);
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
                String title = "BSJS0RCPJ.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSJS0RCPJ.error";
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

        super.addTextField("RcpNo", "BSRCP.RcpNo");
        super.setFieldHelpMessage("BSRCP.RcpNo.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSJL1RCP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addNumericField("Seq", "BSRCPJ.Seq");
        super.setFieldHelpMessage("BSRCPJ.Seq.help");
        super.setFieldEditable(false);
        super.setFieldTextColumn(5);
        super.setFieldMaximumLength(5);

        super.addTab("general", "BSJS0RCPJ.tab.general");
        super.addTextField("Adj", "BSRCPJ.Adj");
        super.setFieldHelpMessage("BSRCPJ.Adj.help");
        super.setFieldEditable(false);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSEL1SA.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Nme", "BSRCPJ.Nme");
        super.setFieldHelpMessage("BSRCPJ.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "BSRCPJ.Typ");
        super.setFieldHelpMessage("BSRCPJ.Typ.help");
        for (SaEnum.Type i : SaEnum.Type.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addNumericField("Prio", "BSRCPJ.Prio");
        super.setFieldHelpMessage("BSRCPJ.Prio.help");
        super.setFieldTextColumn(2);
        super.setFieldMaximumLength(2);
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSRCPJ.Amt");
        super.setFieldHelpMessage("BSRCPJ.Amt.help");
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSJS0RCPJ.title";
    }
}
