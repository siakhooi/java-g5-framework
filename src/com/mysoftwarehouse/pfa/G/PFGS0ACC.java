/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.N.PFNH1ACC;
import com.mysoftwarehouse.pfa.data.PFACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC1;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGS0ACC extends ModeEntryForm {

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        if (cmd.global.booleans.get(CFG.PFCFG_SHOWHELP)) {
            return PFNH1ACC.class;
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

        //if mode=edit, not allow to change AccTyp (not editable)
        if (super.nextModeType == super.nextModeType.EditMode) {
            cmd.entry.setEditable("AccTyp", false);
        } else if (super.nextModeType == super.nextModeType.AddMode) {
            cmd.entry.setEditable("AccTyp", true);
        }
    }

    @Override
    public void finishModeAction() {
    }

    @Override
    public void saveAdd() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String acc = cmd.entry.getString("Acc");
        String nme = cmd.entry.getString("Nme");
        String accTyp = cmd.entry.getString("AccTyp");
        BigDecimal balAmt = cmd.entry.getBigDecimal("BalAmt");
        Calendar balDte = cmd.entry.getCalendar("BalDte");
        String remark = cmd.entry.getString("Remark");
        String status=cmd.entry.getString("Status");
        boolean showInMain=cmd.entry.getBoolean("ShowInMain");
        
        try {
            cmd.db.begin();
            ACC1.insert(this, id, acc, nme, accTyp, "N", balAmt, balDte, remark,
                    status, showInMain);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);

        String nme = cmd.entry.getString("Nme");
        BigDecimal balAmt = cmd.entry.getBigDecimal("BalAmt");
        Calendar balDte = cmd.entry.getCalendar("BalDte");
        String remark = cmd.entry.getString("Remark");
        String status=cmd.entry.getString("Status");
        boolean showInMain=cmd.entry.getBoolean("ShowInMain");
        try {
            cmd.db.begin();
            ACC1.updateACC(this, id, acc, nme, balAmt, balDte, remark, status, showInMain);
            cmd.db.commit();
        } catch (SQLException ex3) {
            cmd.db.rollback();
            throw ex3;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);

        cmd.out.map.texts.put(ACC.PFACC_ACC, acc);
        UserFormInterface f = cmd.form.create(PFGP1ACC.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);

        String rsName = "PFGS0ACC";
        ACC.select(this, rsName, id, acc);
        cmd.entry.setValue("Acc", acc);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("AccTyp", cmd.db.getString(rsName, "AccTyp"));
        cmd.entry.setValue("BalAmt", cmd.db.getBigDecimal(rsName, "BalAmt"));
        cmd.entry.setValue("BalDte", cmd.db.getTimestamp(rsName, "BalDte"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
        cmd.entry.setValue("ShowInMain",
                cmd.data.isTrue(cmd.db.getString(rsName, "ShowInMain")));
    }

    @Override
    public void loadDefault() throws Exception {
        cmd.entry.setValue("AccTyp", "A");
        cmd.entry.setValue("BalAmt", 0);
        cmd.entry.setValue("BalDte", cmd.cal.getNow());
        cmd.entry.setValue("Status", PFACC.Status.A.code);
        cmd.entry.setValue("ShowInMain", true);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Acc", "PFACC.Acc");
        super.setFieldHelpMessage("PFACC.Acc.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("info", "PFGS0ACC.tab.info");

        super.addTextField("Nme", "PFACC.Nme");
        super.setFieldHelpMessage("PFACC.Nme.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);

        super.addRadioboxfield("AccTyp", "PFACC.AccTyp");
        super.setFieldHelpMessage("PFACC.AccTyp.help");
        for (PFACC.AccTyp s : PFACC.AccTyp.values()) {
            super.addOption(s.code, s.name);
        }

        super.addNumericField("BalAmt", "PFACC.BalAmt");
        super.setFieldHelpMessage("PFACC.BalAmt.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addCalendarField("BalDte", "PFACC.BalDte");
        super.setFieldHelpMessage("PFACC.BalDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, true);

        super.addTextField("Remark", "PFACC.Remark");
        super.setFieldHelpMessage("PFACC.Remark.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);

        super.addRadioboxfield("Status", "PFACC.Status");
        super.setFieldHelpMessage("PFACC.Status.help");
        for (PFACC.Status s : PFACC.Status.values()) {
            super.addOption(s.code, s.name);
        }

        super.addBooleanField("ShowInMain", "PFACC.ShowInMain");
        super.setFieldHelpMessage("PFACC.ShowInMain.help");
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "PFGS0ACC.title";
    }
}
