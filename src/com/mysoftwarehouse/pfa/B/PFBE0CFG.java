/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.B;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.data.PFCFG;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFBE0CFG extends EntryForm {

    @Override
    protected void buildFieldList() {
        //super.addComboField(arg0, arg1);
        super.addComboField("MainAccLstTyp", "PFCFG.MainAccLstTyp");
        super.setFieldHelpMessage("PFCFG.MainAccLstTyp.help");
        for (PFCFG.MainListType t : PFCFG.MainListType.values()) {
            super.addOption(t.code, t.name);
        }
//        super.addOption("A", "PFCFG.MainAccLstTyp.A");
//        super.addOption("B", "PFCFG.MainAccLstTyp.B");
//        super.addOption("Y", "PFCFG.MainAccLstTyp.Y");
//        super.addOption("M", "PFCFG.MainAccLstTyp.M");
//        super.addOption("D", "PFCFG.MainAccLstTyp.D");
//        super.addOption("I", "PFCFG.MainAccLstTyp.I");

        super.addTextField("CapAcc", "PFCFG.CapAcc");
        super.setFieldHelpMessage("PFCFG.CapAcc.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("IncAcc", "PFCFG.IncAcc");
        super.setFieldHelpMessage("PFCFG.IncAcc.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("RtIncAcc", "PFCFG.RtIncAcc");
        super.setFieldHelpMessage("PFCFG.RtIncAcc.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("DefRecAcc", "PFCFG.DefRecAcc");
        super.setFieldHelpMessage("PFCFG.DefRecAcc.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addBooleanField("ShowHelp", "PFCFG.ShowHelp");
        super.setFieldHelpMessage("PFCFG.ShowHelp.help");
        super.setFieldEditable(true);
    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "PFBE0CFG.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "PFBE0CFG.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("DefRecAcc")) {
            try {
                String id = cmd.global.texts.get(USR.PFUSR_ID);
                String defRecAcc = cmd.entry.getString("DefRecAcc");

                boolean i = ACC.hasACC(this, id, defRecAcc);
                if (!i) {
                    String title = "PFBE0CFG.error.AccountNotExist";
                    //cmd.entry.setStatus(title);
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "PFBE0CFG.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "PFBE0CFG.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String id = cmd.global.texts.get(USR.PFUSR_ID);
                String mainAccLstTyp = cmd.entry.getString("MainAccLstTyp");
                String defRecAcc = cmd.entry.getString("DefRecAcc");
                boolean showHelp = cmd.entry.getBoolean("ShowHelp");
                if (!verify("DefRecAcc")) {
                    return;
                }

                cmd.db.smartStart();
                CFG.update(this, id, mainAccLstTyp, defRecAcc, showHelp);
                cmd.db.smartComplete();
                cmd.global.texts.put(CFG.PFCFG_DEF_RECON_ACCOUNT, defRecAcc);
                cmd.global.booleans.put(CFG.PFCFG_SHOWHELP, showHelp);

            } catch (SQLException ex) {
                cmd.log.severe("PFBE0CFG.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("PFBE0CFG.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            String rsName = "PFBE0CFG";
            String id = cmd.global.texts.get(USR.PFUSR_ID);
            if (CFG.select(this, rsName, id)) {
                cmd.entry.setValue("MainAccLstTyp", cmd.db.getString(rsName, "MainAccLstTyp"));
                cmd.entry.setValue("CapAcc", cmd.db.getString(rsName, "CapAcc"));
                cmd.entry.setValue("IncAcc", cmd.db.getString(rsName, "IncAcc"));
                cmd.entry.setValue("RtIncAcc", cmd.db.getString(rsName, "RtIncAcc"));
                cmd.entry.setValue("DefRecAcc", cmd.db.getString(rsName, "DefRecAcc"));
                cmd.entry.setValue("ShowHelp", cmd.data.isTrue(
                        cmd.db.getString(rsName, "ShowHelp")));
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFBE0CFG.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("PFBE0CFG.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFBE0CFG.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
