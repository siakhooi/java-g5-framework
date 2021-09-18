/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.data.TcdEnum;
import com.mysoftwarehouse.is.db.TCD.TCD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFS0TCD extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
    }

    @Override
    public void saveAdd() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Tcd = cmd.entry.getString("Tcd");
        String Typ = cmd.entry.getString("Typ");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            TCD.insert(this, Whs, Tcd, Typ, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Tcd = cmd.entry.getString("Tcd");
        String Typ = cmd.entry.getString("Typ");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            TCD.update(this, Whs, Tcd, Typ, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String tcd = cmd.entry.getString("Tcd");
        cmd.out.map.texts.put(MAP.ISTCD.TCD, tcd);
        UserFormInterface f = cmd.form.create(ISFP0TCD.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String Whs = GET.Whs(this);
        String Tcd = cmd.in.map.texts.get(MAP.ISTCD.TCD);
        String rsName = "ISFS0TCD";
        TCD.select(this, rsName, Whs, Tcd);

        cmd.entry.setValue("Whs", Whs);
        cmd.entry.setValue("Tcd", Tcd);
        cmd.entry.setValue("Typ", cmd.db.getString(rsName, "Typ"));
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
        String whs = GET.Whs(this);
        cmd.entry.setValue("Whs", whs);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Tcd")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Tcd = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Tcd)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String Whs = GET.Whs(this);
                boolean i = TCD.hasTcd(this, Whs, Tcd);
                if (i) {
                    String title = "TCD.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "ISFS0TCD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "ISFS0TCD.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Whs", "ISWHS.Whs");
        super.setFieldHelpMessage("ISWHS.Whs.help");
        super.setFieldSelectForm(ISBL1WHS.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Tcd", "ISTCD.Tcd");
        super.setFieldHelpMessage("ISTCD.Tcd.help");
        super.setFieldSelectForm(ISFL1TCD.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "ISFS0TCD.tab.general");
        super.addTextField("Nme", "ISTCD.Nme");
        super.setFieldHelpMessage("ISTCD.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "ISTCD.Typ");
        super.setFieldHelpMessage("ISTCD.Typ.help");
        for (TcdEnum.TxnTyp i : TcdEnum.TxnTyp.values()) {
            super.addOption(i.typ, i.name);
        }


        super.addTextField("Remark", "ISTCD.Remark");
        super.setFieldHelpMessage("ISTCD.Status.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "ISFS0TCD.title";
    }
}
