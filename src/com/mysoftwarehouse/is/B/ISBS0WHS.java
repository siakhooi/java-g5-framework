/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.data.CstEnum;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.db.WHS.WHS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBS0WHS extends ModeEntryForm {

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
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");
        String CstTyp = cmd.entry.getString("CstTyp");

        try {
            cmd.db.begin();
            WHS.insert(this, Whs, Nme, CstTyp, Remark);
//            CMP_Default.createPY(this, Cmp);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");
        String CstTyp = cmd.entry.getString("CstTyp");

        try {
            cmd.db.begin();
            WHS.update(this, Whs, Nme, CstTyp, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String whs = cmd.entry.getString("Whs");
        cmd.out.map.texts.put(MAP.ISWHS.WHS, whs);
        UserFormInterface f = cmd.form.create(ISBP2WHS.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String whs = cmd.in.map.texts.get(MAP.ISWHS.WHS);

        String rsName = "ISBS0WHS";
        WHS.select(this, rsName, whs);

        cmd.entry.setValue("Whs", whs);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("CstTyp", cmd.db.getString(rsName, "CstTyp"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Whs")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Whs = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Whs)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                boolean i = WHS.hasWHS(this, Whs);
                if (i) {
                    String title = "WHS.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "ISBS0WHS.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "ISBS0WHS.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Whs", "ISWHS.Whs");
        super.setFieldHelpMessage("ISWHS.Whs.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "ISBS0WHS.tab.general");
        super.addTextField("Nme", "ISWHS.Nme");
        super.setFieldHelpMessage("ISWHS.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("CstTyp", "ISWHS.CstTyp");
        super.setFieldHelpMessage("ISWHS.CstTyp.help");
        for (CstEnum.CstTyp i : CstEnum.CstTyp.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "ISWHS.Remark");
        super.setFieldHelpMessage("ISWHS.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "ISBS0WHS.title";
    }
}
