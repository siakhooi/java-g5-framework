/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.E;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.LOC.LOC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISES0LOC extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("items", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("items", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Loc = cmd.entry.getString("Loc");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            LOC.insert(this, Whs, Loc, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Loc = cmd.entry.getString("Loc");
        String Nme = cmd.entry.getString("Nme");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            LOC.update(this, Whs, Loc, Nme, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String loc = cmd.entry.getString("Loc");
        cmd.out.map.texts.put(MAP.ISLOC.LOC, loc);
        UserFormInterface f = cmd.form.create(ISEP0LOC.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String whs = GET.Whs(this);
        String loc = cmd.in.map.texts.get(MAP.ISLOC.LOC);
        String rsName = "ISES0LOC";
        LOC.select(this, rsName, whs, loc);

        cmd.entry.setValue("Whs", whs);
        cmd.entry.setValue("Loc", loc);
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
        if (fieldName.equals("Loc")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Loc = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Loc)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String whs = GET.Whs(this);
                boolean i = LOC.hasLoc(this, whs, Loc);
                if (i) {
                    String title = "LOC.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "ISES0LOC.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "ISES0LOC.error";
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

        super.addTextField("Loc", "ISLOC.Loc");
        super.setFieldHelpMessage("ISLOC.Loc.help");
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "ISES0LOC.tab.general");
        super.addTextField("Nme", "ISLOC.Nme");
        super.setFieldHelpMessage("ISLOC.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("Remark", "ISLOC.Remark");
        super.setFieldHelpMessage("ISLOC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
        
        super.addTab("items", "ISES0LOC.tab.items", ISEL0ITMB.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "ISES0LOC.title";
    }
}
