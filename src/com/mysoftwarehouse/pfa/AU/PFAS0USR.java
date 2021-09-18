/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AU;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.pfa.data.PFUSR;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAS0USR extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
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
        String id = cmd.entry.getString("Id");
        String FstNme = cmd.entry.getString("FstNme");
        String LstNme = cmd.entry.getString("LstNme");
        Calendar Dob = cmd.entry.getCalendar("Dob");
        String Gender = cmd.entry.getString("Gender");
        String Career = cmd.entry.getString("Career");
        String Ind = cmd.entry.getString("Ind");
        String MrtSts = cmd.entry.getString("MrtSts");
        String Address1 = cmd.entry.getString("Add1");
        String Address2 = cmd.entry.getString("Add2");
        String City = cmd.entry.getString("City");
        String ZipCode = cmd.entry.getString("ZipCde");
        String State = cmd.entry.getString("State");
        String Ctry = cmd.entry.getString("Ctry");

        try {
            cmd.db.begin();
            USR.insert(this, id, FstNme, LstNme, Dob, Gender, Career,
                    Ind, MrtSts, Address1, Address2, City, ZipCode,
                    State, Ctry);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String id = cmd.entry.getString("Id");
        String FstNme = cmd.entry.getString("FstNme");
        String LstNme = cmd.entry.getString("LstNme");
        Calendar Dob = cmd.entry.getCalendar("Dob");
        String Gender = cmd.entry.getString("Gender");
        String Career = cmd.entry.getString("Career");
        String Ind = cmd.entry.getString("Ind");
        String MrtSts = cmd.entry.getString("MrtSts");
        String Address1 = cmd.entry.getString("Add1");
        String Address2 = cmd.entry.getString("Add2");
        String City = cmd.entry.getString("City");
        String ZipCode = cmd.entry.getString("ZipCde");
        String State = cmd.entry.getString("State");
        String Ctry = cmd.entry.getString("Ctry");
        try {
            cmd.db.begin();
            USR.update(this, id, FstNme, LstNme, Dob, Gender, Career,
                    Ind, MrtSts, Address1, Address2, City, ZipCode,
                    State, Ctry);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String id = cmd.entry.getString("Id");
        cmd.out.map.texts.put(USR.PFUSR_ID, id);
        UserFormInterface f = cmd.form.create(PFAP1USR.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String id = cmd.in.map.texts.get(USR.PFUSR_ID);

        String rsName = "PFAS0USR";
        USR.select(this, rsName, id);
        cmd.entry.setValue("Id", id);
        cmd.entry.setValue("FstNme", cmd.db.getString(rsName, "FstNme"));
        cmd.entry.setValue("LstNme", cmd.db.getString(rsName, "LstNme"));
        cmd.entry.setValue("Dob", cmd.db.getTimestamp(rsName, "Dob"));
        cmd.entry.setValue("Gender", cmd.db.getString(rsName, "Gender"));
        cmd.entry.setValue("Career", cmd.db.getString(rsName, "Career"));
        cmd.entry.setValue("Ind", cmd.db.getString(rsName, "Ind"));
        cmd.entry.setValue("MrtSts", cmd.db.getString(rsName, "MrtSts"));
        cmd.entry.setValue("Add1", cmd.db.getString(rsName, "Add1"));
        cmd.entry.setValue("Add2", cmd.db.getString(rsName, "Add2"));
        cmd.entry.setValue("City", cmd.db.getString(rsName, "City"));
        cmd.entry.setValue("ZipCde", cmd.db.getString(rsName, "ZipCde"));
        cmd.entry.setValue("State", cmd.db.getString(rsName, "State"));
        cmd.entry.setValue("Ctry", cmd.db.getString(rsName, "Ctry"));
    }

    @Override
    public void loadDefault() throws Exception {
        cmd.entry.setValue("Gender", "M");
        cmd.entry.setValue("MrtSts", "S");
        cmd.entry.setValue("Dob", cmd.cal.getNow());
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Id", "PFUSR.Id");
        super.setFieldHelpMessage("PFUSR.Id.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(100);
        //super.setFieldSelectForm(PFCL2ACCY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(50);

        super.addTab("general", "PFAS0USR.tab.general");

        super.addTextField("FstNme", "PFUSR.FstNme");
        super.setFieldHelpMessage("PFUSR.FstNme.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(40);

        super.addTextField("LstNme", "PFUSR.LstNme");
        super.setFieldHelpMessage("PFUSR.LstNme.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(40);

        super.addCalendarField("Dob", "PFUSR.Dob");
        super.setFieldHelpMessage("PFUSR.Dob.help");
        super.setFieldFormat(true, false);

        super.addRadioboxfield("Gender", "PFUSR.Gender");
        super.setFieldHelpMessage("PFUSR.Gender.help");
        for (PFUSR.Gender g : PFUSR.Gender.values()) {
            super.addOption(g.code, g.name);
        }

        super.addRadioboxfield("MrtSts", "PFUSR.MrtSts");
        super.setFieldHelpMessage("PFUSR.MrtSts.help");
        for (PFUSR.MrtSts m : PFUSR.MrtSts.values()) {
            super.addOption(m.code, m.name);
        }

        super.addTextField("Career", "PFUSR.Career");
        super.setFieldHelpMessage("PFUSR.Career.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(40);

        super.addTextField("Ind", "PFUSR.Ind");
        super.setFieldHelpMessage("PFUSR.Ind.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(40);

        super.addTab("address", "PFAS0USR.tab.address");

        super.addTextField("Add1", "PFUSR.Add1");
        super.setFieldHelpMessage("PFUSR.Add1.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(100);

        super.addTextField("Add2", "PFUSR.Add2");
        super.setFieldHelpMessage("PFUSR.Add2.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(100);

        super.addTextField("City", "PFUSR.City");
        super.setFieldHelpMessage("PFUSR.City.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);

        super.addTextField("ZipCde", "PFUSR.ZipCde");
        super.setFieldHelpMessage("PFUSR.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("State", "PFUSR.State");
        super.setFieldHelpMessage("PFUSR.State.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);

        super.addTextField("Ctry", "PFUSR.Ctry");
        super.setFieldHelpMessage("PFUSR.Ctry.help");
        super.setFieldMaximumLength(100);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "PFAS0USR.title";
    }
}
