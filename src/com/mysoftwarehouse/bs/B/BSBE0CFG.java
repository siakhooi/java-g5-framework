/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.CG.G.BSGL1ST;
import com.mysoftwarehouse.bs.CG.G.BSGL2ST;
import com.mysoftwarehouse.bs.CG.G.BSGL3ST;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.bs.data.PivEnum;
import com.mysoftwarehouse.bs.data.PorEnum;
import com.mysoftwarehouse.bs.data.QttEnum;
import com.mysoftwarehouse.bs.data.RcpEnum;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import com.mysoftwarehouse.bs.db.ST.ST;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBE0CFG extends EntryForm {

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

        super.addTab("qtt", "BSBE0CFG.tab.qtt");
        super.addNumericField("LstNumQtt", "BSCFG.LstNumQtt");
        super.setFieldHelpMessage("BSCFG.LstNumQtt.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);

        super.addNumericField("NumDgtsQtt", "BSCFG.NumDgtsQtt");
        super.setFieldHelpMessage("BSCFG.NumDgtsQtt.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(3);
        super.setFieldTextColumn(3);

        super.addTextField("PrfxQtt", "BSCFG.PrfxQtt");
        super.setFieldHelpMessage("BSCFG.PrfxQtt.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        super.addTextField("DfltTrmQtt", "BSCFG.DfltTrmQtt");
        super.setFieldHelpMessage("BSCFG.DfltTrmQtt.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldSelectForm(BSGL1ST.class);

        super.addRadioboxField("PrtFmtQtt", "BSCFG.PrtFmtQtt");
        super.setFieldHelpMessage("BSCFG.PrtFmtQtt.help");
        for (QttEnum.Format i : QttEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("inv", "BSBE0CFG.tab.inv");
        super.addNumericField("LstNumInv", "BSCFG.LstNumInv");
        super.setFieldHelpMessage("BSCFG.LstNumInv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);

        super.addNumericField("NumDgtsInv", "BSCFG.NumDgtsInv");
        super.setFieldHelpMessage("BSCFG.NumDgtsInv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(3);
        super.setFieldTextColumn(3);

        super.addTextField("PrfxInv", "BSCFG.PrfxInv");
        super.setFieldHelpMessage("BSCFG.PrfxInv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        super.addTextField("DfltTrmInv", "BSCFG.DfltTrmInv");
        super.setFieldHelpMessage("BSCFG.DfltTrmInv.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldSelectForm(BSGL2ST.class);

        super.addRadioboxField("PrtFmtInv", "BSCFG.PrtFmtInv");
        super.setFieldHelpMessage("BSCFG.PrtFmtInv.help");
        for (InvEnum.Format i : InvEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("rcp", "BSBE0CFG.tab.rcp");
        super.addNumericField("LstNumRcp", "BSCFG.LstNumRcp");
        super.setFieldHelpMessage("BSCFG.LstNumRcp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);

        super.addNumericField("NumDgtsRcp", "BSCFG.NumDgtsRcp");
        super.setFieldHelpMessage("BSCFG.NumDgtsRcp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(3);
        super.setFieldTextColumn(3);

        super.addTextField("PrfxRcp", "BSCFG.PrfxRcp");
        super.setFieldHelpMessage("BSCFG.PrfxRcp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        super.addTextField("DfltTrmRcp", "BSCFG.DfltTrmRcp");
        super.setFieldHelpMessage("BSCFG.DfltTrmRcp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldSelectForm(BSGL3ST.class);

        super.addRadioboxField("PrtFmtRcp", "BSCFG.PrtFmtRcp");
        super.setFieldHelpMessage("BSCFG.PrtFmtRcp.help");
        for (RcpEnum.Format i : RcpEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("por", "BSBE0CFG.tab.por");
        super.addNumericField("LstNumPor", "BSCFG.LstNumPor");
        super.setFieldHelpMessage("BSCFG.LstNumPor.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);

        super.addNumericField("NumDgtsPor", "BSCFG.NumDgtsPor");
        super.setFieldHelpMessage("BSCFG.NumDgtsPor.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(3);
        super.setFieldTextColumn(3);

        super.addTextField("PrfxPor", "BSCFG.PrfxPor");
        super.setFieldHelpMessage("BSCFG.PrfxPor.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        super.addRadioboxField("PrtFmtPor", "BSCFG.PrtFmtPor");
        super.setFieldHelpMessage("BSCFG.PrtFmtPor.help");
        for (PorEnum.Format i : PorEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("piv", "BSBE0CFG.tab.piv");
        super.addNumericField("LstNumPiv", "BSCFG.LstNumPiv");
        super.setFieldHelpMessage("BSCFG.LstNumPiv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);

        super.addNumericField("NumDgtsPiv", "BSCFG.NumDgtsPiv");
        super.setFieldHelpMessage("BSCFG.NumDgtsPiv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(3);
        super.setFieldTextColumn(3);

        super.addTextField("PrfxPiv", "BSCFG.PrfxPiv");
        super.setFieldHelpMessage("BSCFG.PrfxPiv.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextColumn(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        super.addRadioboxField("PrtFmtPiv", "BSCFG.PrtFmtPiv");
        super.setFieldHelpMessage("BSCFG.PrtFmtPiv.help");
        for (PivEnum.Format i : PivEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }

//        super.addTab("letterhead", "BSBE0CFG.tab.letterhead");
//        super.addImageField("LetterHead", "BSCFGI.LetterHead");
//        super.setFieldHelpMessage("BSCFGI.LetterHead.help");

    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "BSBE0CFG.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "BSBE0CFG.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("DfltTrmQtt") ||
                fieldName.equals("DfltTrmInv") ||
                fieldName.equals("DfltTrmRcp")) {
            try {
                String cmp = GET.Cmp(this);
                String Trm = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Trm)) {
                    return true;
                }

                boolean i = ST.hasST(this, cmp, Trm);
                if (!i) {
                    String title = "ST.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSBE0CFG.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSBE0CFG.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String cmp = GET.Cmp(this);
                int LstNumQtt = cmd.entry.getInteger("LstNumQtt");
                int LstNumInv = cmd.entry.getInteger("LstNumInv");
                int LstNumRcp = cmd.entry.getInteger("LstNumRcp");
                int LstNumPor = cmd.entry.getInteger("LstNumPor");
                int LstNumPiv = cmd.entry.getInteger("LstNumPiv");
                int NumDgtsQtt = cmd.entry.getInteger("NumDgtsQtt");
                int NumDgtsInv = cmd.entry.getInteger("NumDgtsInv");
                int NumDgtsRcp = cmd.entry.getInteger("NumDgtsRcp");
                int NumDgtsPor = cmd.entry.getInteger("NumDgtsPor");
                int NumDgtsPiv = cmd.entry.getInteger("NumDgtsPiv");
                String PrfxQtt = cmd.entry.getString("PrfxQtt");
                String PrfxInv = cmd.entry.getString("PrfxInv");
                String PrfxRcp = cmd.entry.getString("PrfxRcp");
                String PrfxPor = cmd.entry.getString("PrfxPor");
                String PrfxPiv = cmd.entry.getString("PrfxPiv");
                String DfltTrmQtt = cmd.entry.getString("DfltTrmQtt");
                String DfltTrmInv = cmd.entry.getString("DfltTrmInv");
                String DfltTrmRcp = cmd.entry.getString("DfltTrmRcp");
                String PrtFmtQtt = cmd.entry.getString("PrtFmtQtt");
                String PrtFmtInv = cmd.entry.getString("PrtFmtInv");
                String PrtFmtRcp = cmd.entry.getString("PrtFmtRcp");
                String PrtFmtPor = cmd.entry.getString("PrtFmtPor");
                String PrtFmtPiv = cmd.entry.getString("PrtFmtPiv");
                //String LetterHead = cmd.entry.getString("LetterHead");

                if (!verify("DfltTrmQtt")) {
                    return;
                }
                if (!verify("DfltTrmInv")) {
                    return;
                }
                if (!verify("DfltTrmRcp")) {
                    return;
                }

                cmd.db.begin();
                CFG.update(this, cmp,
                        LstNumQtt, LstNumInv, LstNumRcp, LstNumPor, LstNumPiv,
                        NumDgtsQtt, NumDgtsInv, NumDgtsRcp, NumDgtsPor, NumDgtsPiv,
                        PrfxQtt, PrfxInv, PrfxRcp, PrfxPor, PrfxPiv,
                        DfltTrmQtt, DfltTrmInv, DfltTrmRcp,
                        PrtFmtQtt, PrtFmtInv, PrtFmtRcp, PrtFmtPor, PrtFmtPiv);
                cmd.db.commit();
                
            cmd.global.texts.put(MAP.BSCFG.QTTFORMAT, PrtFmtQtt);
            cmd.global.texts.put(MAP.BSCFG.INVFORMAT, PrtFmtInv);
            cmd.global.texts.put(MAP.BSCFG.RCPFORMAT, PrtFmtRcp);
            cmd.global.texts.put(MAP.BSCFG.PORFORMAT, PrtFmtPor);
            cmd.global.texts.put(MAP.BSCFG.PIVFORMAT, PrtFmtPiv);
                
            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
                cmd.log.severe("BSBE0CFG.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSBE0CFG.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            String rsName = "BSBE0CFG";
            String cmp = GET.Cmp(this);
            cmd.entry.setValue("Cmp", cmp);
            if (CFG.select(this, rsName, cmp)) {

                cmd.entry.setValue("LstNumQtt", cmd.db.getInteger(rsName, "LstNumQtt"));
                cmd.entry.setValue("LstNumInv", cmd.db.getInteger(rsName, "LstNumInv"));
                cmd.entry.setValue("LstNumRcp", cmd.db.getInteger(rsName, "LstNumRcp"));
                cmd.entry.setValue("LstNumPor", cmd.db.getInteger(rsName, "LstNumPor"));
                cmd.entry.setValue("LstNumPiv", cmd.db.getInteger(rsName, "LstNumPiv"));
                cmd.entry.setValue("NumDgtsQtt", cmd.db.getInteger(rsName, "NumDgtsQtt"));
                cmd.entry.setValue("NumDgtsInv", cmd.db.getInteger(rsName, "NumDgtsInv"));
                cmd.entry.setValue("NumDgtsRcp", cmd.db.getInteger(rsName, "NumDgtsRcp"));
                cmd.entry.setValue("NumDgtsPor", cmd.db.getInteger(rsName, "NumDgtsPor"));
                cmd.entry.setValue("NumDgtsPiv", cmd.db.getInteger(rsName, "NumDgtsPiv"));
                cmd.entry.setValue("PrfxQtt", cmd.db.getString(rsName, "PrfxQtt"));
                cmd.entry.setValue("PrfxInv", cmd.db.getString(rsName, "PrfxInv"));
                cmd.entry.setValue("PrfxRcp", cmd.db.getString(rsName, "PrfxRcp"));
                cmd.entry.setValue("PrfxPor", cmd.db.getString(rsName, "PrfxPor"));
                cmd.entry.setValue("PrfxPiv", cmd.db.getString(rsName, "PrfxPiv"));
                cmd.entry.setValue("DfltTrmQtt", cmd.db.getString(rsName, "DfltTrmQtt"));
                cmd.entry.setValue("DfltTrmInv", cmd.db.getString(rsName, "DfltTrmInv"));
                cmd.entry.setValue("DfltTrmRcp", cmd.db.getString(rsName, "DfltTrmRcp"));
                cmd.entry.setValue("PrtFmtQtt", cmd.db.getString(rsName, "PrtFmtQtt"));
                cmd.entry.setValue("PrtFmtInv", cmd.db.getString(rsName, "PrtFmtInv"));
                cmd.entry.setValue("PrtFmtRcp", cmd.db.getString(rsName, "PrtFmtRcp"));
                cmd.entry.setValue("PrtFmtPor", cmd.db.getString(rsName, "PrtFmtPor"));
                cmd.entry.setValue("PrtFmtPiv", cmd.db.getString(rsName, "PrtFmtPiv"));
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSBE0CFG.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSBE0CFG.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSBE0CFG.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
