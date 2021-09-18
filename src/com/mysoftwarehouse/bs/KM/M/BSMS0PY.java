/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.M;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PY.PY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSMS0PY extends ModeEntryForm {

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
        String PayTyp = cmd.entry.getString("PayTyp");
        String Nme = cmd.entry.getString("Nme");

        try {
            cmd.db.begin();
            PY.insert(this, Cmp, PayTyp, Nme);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String PayTyp = cmd.entry.getString("PayTyp");
        String Nme = cmd.entry.getString("Nme");
        try {
            cmd.db.begin();
            PY.update(this, Cmp, PayTyp, Nme);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String PayTyp = cmd.entry.getString("PayTyp");
        cmd.out.map.texts.put(MAP.BSPY.PAYTYP, PayTyp);
        UserFormInterface f = cmd.form.create(BSMP0PY.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String PayTyp = cmd.in.map.texts.get(MAP.BSPY.PAYTYP);

        String rsName = "BSMS0PY";
        PY.select(this, rsName, cmp, PayTyp);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("PayTyp", PayTyp);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
    }

    @Override
    public boolean verify(String fieldName) {
        if (super.currentMode == super.currentMode.ADD) {
            if ("PayTyp".equals(fieldName)) {
                try {
                    String Cmp = GET.Cmp(this);
                    String PayTyp = cmd.entry.getString(fieldName);
                    if (PY.hasPY(this, Cmp, PayTyp)) {
                        String title = "PY.error.Exist";
                        cmd.entry.setStatus(StatusType.ERROR, title);
                        return false;
                    }
                } catch (SQLException ex) {
                    String title = "BSMS0PY.error";
                    cmd.log.severe(title, ex);
                    return false;
                } catch (EntryFieldWrongDataTypeException ex) {
                    String title = "BSMS0PY.error";
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

        super.addTextField("PayTyp", "BSPY.PayTyp");
        super.setFieldHelpMessage("BSPY.PayTyp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSML1PY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSMS0PY.tab.general");
        super.addTextField("Nme", "BSPY.Nme");
        super.setFieldHelpMessage("BSPY.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSMS0PY.title";
    }
}
