/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.D;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.UOM.UOM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISDS0UOM extends ModeEntryForm {

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
        String Uom = cmd.entry.getString("Uom");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            UOM.insert(this, Whs, Uom, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Uom = cmd.entry.getString("Uom");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            UOM.update(this, Whs, Uom, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String uom = cmd.entry.getString("Uom");
        cmd.out.map.texts.put(MAP.ISUOM.UOM, uom);
        UserFormInterface f = cmd.form.create(ISDP0UOM.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String Whs = GET.Whs(this);
        String Uom = cmd.in.map.texts.get(MAP.ISUOM.UOM);
        String rsName = "ISDS0UOM";
        UOM.select(this, rsName, Whs, Uom);

        cmd.entry.setValue("Whs", Whs);
        cmd.entry.setValue("Uom", Uom);
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
        if (fieldName.equals("Uom")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Uom = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Uom)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String Whs = GET.Whs(this);
                boolean i = UOM.hasUom(this, Whs, Uom);
                if (i) {
                    String title = "UOM.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "ISDS0UOM.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "ISDS0UOM.error";
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

        super.addTextField("Uom", "ISUOM.Uom");
        super.setFieldHelpMessage("ISUOM.Uom.help");
        super.setFieldSelectForm(ISDL1UOM.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "ISDS0UOM.tab.general");
        super.addTextField("Nme", "ISUOM.Nme");
        super.setFieldHelpMessage("ISUOM.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("Remark", "ISUOM.Remark");
        super.setFieldHelpMessage("ISUOM.Status.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "ISDS0UOM.title";
    }
}
