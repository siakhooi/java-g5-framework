/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.N.PFNH1TXN;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.CFG.TxnId;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDS0TXN extends ModeEntryForm {

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        if (cmd.global.booleans.get(CFG.PFCFG_SHOWHELP)) {
            return PFNH1TXN.class;
        } else {
            return null;
        }
    }

    @Override
    public void initModeAction() {
        buttonConfig.search = null;
        if (super.currentMode == currentMode.ADD) {
            super.buttonConfig.add = ModeAction.SaveAddAndClose;
        }
        if (super.currentMode == currentMode.EDIT) {
            super.buttonConfig.edit = ModeAction.SaveEditAndClose;
        }
    }

    @Override
    public void finishModeAction() {
    }

    @Override
    public void saveAdd() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        //int txnId = cmd.entry.getInteger("TxnId");
        String txnTyp = cmd.entry.getString("TxnTyp");
        Calendar txnDte = cmd.entry.getCalendar("TxnDte");
        String desc = cmd.entry.getString("Dsc");
        String frmAcc = cmd.entry.getString("FrmAcc");
        BigDecimal UsrAmt = cmd.entry.getBigDecimal("UsrAmt");
        String toAcc = cmd.entry.getString("ToAcc");
        String refNo = cmd.entry.getString("RefNo");
        String remark = cmd.entry.getString("Remark");
        try {
            cmd.db.begin();
            int txnId = TxnId.nextTxnId(this, id);
            TXN2.insert(this, id, txnId, txnTyp,
                    txnDte, frmAcc, UsrAmt, toAcc, refNo,
                    desc, remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int txnId = cmd.entry.getInteger("TxnId");
        String txnTyp = cmd.entry.getString("TxnTyp");
        Calendar txnDte = cmd.entry.getCalendar("TxnDte");
        String desc = cmd.entry.getString("Dsc");
        String frmAcc = cmd.entry.getString("FrmAcc");
        BigDecimal UsrAmt = cmd.entry.getBigDecimal("UsrAmt");
        String toAcc = cmd.entry.getString("ToAcc");
        String refNo = cmd.entry.getString("RefNo");
        String remark = cmd.entry.getString("Remark");
        try {
            cmd.db.begin();
            TXN2.update(this, id, txnId, txnTyp,
                    txnDte, frmAcc, UsrAmt, toAcc, refNo,
                    desc, remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public boolean verify(String fieldName) {
        try {
            if ("TxnTyp".equals(fieldName)) {
                String v = cmd.entry.getString("TxnTyp");
                if ("R".equals(v)) {
                    cmd.entry.setValue("ToAcc",
                            cmd.global.texts.get(
                            CFG.PFCFG_DEF_RECON_ACCOUNT));
                }
                return true;
            }

            String id = cmd.global.texts.get(USR.PFUSR_ID);
            String acc = "";
            if ("FrmAcc".equals(fieldName)) {
                acc = cmd.entry.getString("FrmAcc");
            } else if ("ToAcc".equals(fieldName)) {
                acc = cmd.entry.getString("ToAcc");
            } else {
                return true;
            }
            return ACC.hasACC(this, id, acc);
        } catch (SQLException ex) {
            cmd.log.severe("PFDS0TXN.error", ex);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFDS0TXN.error", ex);
        }
        return false;
    }

    @Override
    public void saveDelete() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int txnId = cmd.in.map.integers.get(TXN.PFTXN_TXNID).intValue();
        try {
            cmd.db.begin();
            TXN2.delete(this, id, txnId);
            cmd.db.commit();
            cmd.form.closeForm();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int txnId = cmd.in.map.integers.get(TXN.PFTXN_TXNID).intValue();

        String rsName = "PFDS0TXN";
        TXN.select(this, rsName, id, txnId);
        cmd.entry.setValue("TxnId", txnId);
        cmd.entry.setValue("TxnTyp", cmd.db.getString(rsName, "TxnTyp"));
        cmd.entry.setValue("TxnDte", cmd.db.getTimestamp(rsName, "TxnDte"));
        cmd.entry.setValue("Dsc", cmd.db.getString(rsName, "Dsc"));
        cmd.entry.setValue("FrmAcc", cmd.db.getString(rsName, "FrmAcc"));
        cmd.entry.setValue("UsrAmt", cmd.db.getBigDecimal(rsName, "UsrAmt"));
        cmd.entry.setValue("ToAcc", cmd.db.getString(rsName, "ToAcc"));
        cmd.entry.setValue("RefNo", cmd.db.getString(rsName, "RefNo"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
        if (cmd.in.map.texts.containsKey(ACC.PFACC_ACC)) {
            String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
            cmd.entry.setValue("FrmAcc", acc);
        }
        cmd.entry.setValue("TxnDte", cmd.cal.getNow());
        cmd.entry.setValue("UsrAmt", BigDecimal.ZERO);
        cmd.entry.setValue("TxnTyp", "N");
    }

    @Override
    protected void buildFieldList() {
        super.addNumericField("TxnId", "PFTXN.TxnId");
        super.setFieldHelpMessage("PFTXN.TxnId.help");
        //super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldTextColumn(20);

        super.addTab("detail", "PFDS0TXN.Tab.detail");

        super.addRadioboxfield("TxnTyp", "PFTXN.TxnTyp");
        super.setFieldHelpMessage("PFTXN.TxnTyp.help");
        super.addOption("N", "PFTXN.TxnTyp.N");
        super.addOption("R", "PFTXN.TxnTyp.R");

        super.addCalendarField("TxnDte", "PFTXN.TxnDte");
        super.setFieldHelpMessage("PFTXN.TxnDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, true);

        super.addTextField("RefNo", "PFTXN.RefNo");
        super.setFieldHelpMessage("PFTXN.RefNo.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("FrmAcc", "PFTXN.FrmAcc");
        super.setFieldHelpMessage("PFTXN.FrmAcc.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addNumericField("UsrAmt", "PFTXN.UsrAmt");
        super.setFieldHelpMessage("PFTXN.UsrAmt.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTextField("ToAcc", "PFTXN.ToAcc");
        super.setFieldHelpMessage("PFTXN.ToAcc.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("Dsc", "PFTXN.Dsc");
        super.setFieldHelpMessage("PFTXN.Dsc.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(100);

        super.addTextField("Remark", "PFTXN.Remark");
        super.setFieldHelpMessage("PFTXN.Remark.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
        super.buttons.addButton(
                "setnow", "PFDS0TXN.button.now",
                cmd.icon.getCommandIcon(cmd.icon.getDefaultHeight()),
                false);
        super.buttons.addButton(
                "settoday", "PFDS0TXN.button.today",
                cmd.icon.getCommandIcon(cmd.icon.getDefaultHeight()),
                false);
        super.buttons.addButton(
                "seteod", "PFDS0TXN.button.eod",
                cmd.icon.getCommandIcon(cmd.icon.getDefaultHeight()),
                false);
    }

    @Override
    public void buttonClick(String name) {
        try {
            if ("setnow".equals(name)) {
                cmd.entry.setValue("TxnDte", cmd.cal.getNow());
            } else if ("settoday".equals(name)) {
                Calendar c = cmd.entry.getCalendar("TxnDte");
                Calendar n = cmd.cal.getNow();
                c.set(Calendar.YEAR, n.get(Calendar.YEAR));
                c.set(Calendar.MONTH, n.get(Calendar.MONTH));
                c.set(Calendar.DAY_OF_MONTH, n.get(Calendar.DAY_OF_MONTH));
                cmd.entry.setValue("TxnDte", c);
            } else if ("seteod".equals(name)) {
                Calendar c = cmd.entry.getCalendar("TxnDte");
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
                cmd.entry.setValue("TxnDte", c);
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.info("PFDS0TXN.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFDS0TXN.title";
    }
}
