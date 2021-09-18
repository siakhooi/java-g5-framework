/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.KM.K.BSKL1SUP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.MAP.BSPOR;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PorEnum;
import com.mysoftwarehouse.bs.db.POR.POR;
import com.mysoftwarehouse.bs.db.POR.PORA;
import com.mysoftwarehouse.bs.db.POR.PORC;
import com.mysoftwarehouse.bs.db.POR.PORD;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.bs.db.POR.PORS;
import com.mysoftwarehouse.bs.db.POR.POR_Amt;
import com.mysoftwarehouse.bs.db.POR.POR_PorNo;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNS0POR extends ModeEntryForm {

    private String CopyPorNo = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.escapeKey = ModeAction.CloseForm;
        if (super.nextModeType == super.nextModeType.AddMode) {
            super.buttonConfig.ok = ModeAction.SaveAddAndEdit;
        }
        if (super.currentMode == null) {
            try {
                cmd.entry.setValue("PorNo", cmd.in.map.texts.get(BSPOR.PORNO));
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSNS0POR.error", ex);
            }
        }
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setEditable("Sup", true);
        } else {
            cmd.entry.setEditable("Sup", false);
        }
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("address", false);
            cmd.entry.setTabEnabled("contact", false);
            cmd.entry.setTabEnabled("details", false);
            cmd.entry.setTabEnabled("spcinst", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("address", true);
            cmd.entry.setTabEnabled("contact", true);
            cmd.entry.setTabEnabled("details", true);
            cmd.entry.setTabEnabled("spcinst", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Sup = cmd.entry.getString("Sup");
        //String PorNo = cmd.entry.getString("PorNo");
        Calendar PorDte = cmd.entry.getCalendar("PorDte");
        Calendar ShpDte = cmd.entry.getCalendar("ShpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            String PorNo = POR_PorNo.getNextPorNo(this, Cmp);
            PorDte = CalendarTool.getDate(PorDte);
            ShpDte = CalendarTool.getDate(ShpDte);
            POR.insert(this, Cmp, Sup, PorNo, PorDte, ShpDte, RefNum, Status, Remark);

            if (!cmd.data.isNull(CopyPorNo)) {
                PORA.copyFrmPor(this, Cmp, PorNo, CopyPorNo);
                PORC.copyFrmPor(this, Cmp, PorNo, CopyPorNo);
                PORD.copyFrmPor(this, Cmp, PorNo, CopyPorNo);
                PORE.copyFrmPor(this, Cmp, PorNo, CopyPorNo);
                PORS.copyFrmPor(this, Cmp, PorNo, CopyPorNo);
            } else {
                PORA.copyFrmSup(this, Cmp, PorNo, Sup);
                PORC.copyFrmSup(this, Cmp, PorNo, Sup);
            }

            POR_Amt.calculate(this, Cmp, PorNo);
            cmd.db.commit();
            cmd.entry.setValue("PorNo", PorNo);

        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        //String Sup = cmd.entry.getString("Sup");
        String PorNo = cmd.entry.getString("PorNo");
        Calendar PorDte = cmd.entry.getCalendar("PorDte");
        Calendar ShpDte = cmd.entry.getCalendar("ShpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        String AAdd1 = cmd.entry.getString("AAdd1");
        String AAdd2 = cmd.entry.getString("AAdd2");
        String ACity = cmd.entry.getString("ACity");
        String AZipCde = cmd.entry.getString("AZipCde");
        String AStte = cmd.entry.getString("AStte");
        String ACtry = cmd.entry.getString("ACtry");
        String ATel = cmd.entry.getString("ATel");
        String AFax = cmd.entry.getString("AFax");
        String ARemark = cmd.entry.getString("ARemark");

        String CFstNme = cmd.entry.getString("CFstNme");
        String CLstNme = cmd.entry.getString("CLstNme");
        String CTitle = cmd.entry.getString("CTitle");
        String CTel = cmd.entry.getString("CTel");
        String CFax = cmd.entry.getString("CFax");
        String CMobile = cmd.entry.getString("CMobile");
        String CEmail = cmd.entry.getString("CEmail");
        String CRemark = cmd.entry.getString("CRemark");
        try {
            cmd.db.begin();
            PorDte = CalendarTool.getDate(PorDte);
            ShpDte = CalendarTool.getDate(ShpDte);
            POR.update(this, Cmp, PorNo, PorDte, ShpDte, RefNum, Status, Remark);
            PORA.update(this, Cmp, PorNo, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            PORC.update(this, Cmp, PorNo, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);

            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String PorNo = cmd.entry.getString("PorNo");
        cmd.db.begin();
        POR.delete(this, Cmp, PorNo);
        PORA.delete(this, Cmp, PorNo);
        PORC.delete(this, Cmp, PorNo);
        PORD.delete(this, Cmp, PorNo);
        PORE.delete(this, Cmp, PorNo);
        PORS.delete(this, Cmp, PorNo);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String PorNo = cmd.entry.getString("PorNo");
        CopyPorNo = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyPorNo = PorNo;
        }

        String rsName = "BSNS0PORL1";
        POR.select(this, rsName, Cmp, PorNo);
        String rsName2 = "BSNS0PORL2";
        PORA.select(this, rsName2, Cmp, PorNo);
        String rsName3 = "BSNS0PORL3";
        PORC.select(this, rsName3, Cmp, PorNo);

        String Sup = cmd.db.getString(rsName, "Sup");
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("Sup", Sup);
        cmd.entry.setValue("PorDte", cmd.db.getTimestamp(rsName, "PorDte"));
        cmd.entry.setValue("ShpDte", cmd.db.getTimestamp(rsName, "ShpDte"));
        cmd.entry.setValue("RefNum", cmd.db.getString(rsName, "RefNum"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));

        cmd.entry.setValue("AAdd1", cmd.db.getString(rsName2, "Add1"));
        cmd.entry.setValue("AAdd2", cmd.db.getString(rsName2, "Add2"));
        cmd.entry.setValue("ACity", cmd.db.getString(rsName2, "City"));
        cmd.entry.setValue("AZipCde", cmd.db.getString(rsName2, "ZipCde"));
        cmd.entry.setValue("AStte", cmd.db.getString(rsName2, "Stte"));
        cmd.entry.setValue("ACtry", cmd.db.getString(rsName2, "Ctry"));
        cmd.entry.setValue("ATel", cmd.db.getString(rsName2, "Tel"));
        cmd.entry.setValue("AFax", cmd.db.getString(rsName2, "Fax"));
        cmd.entry.setValue("ARemark", cmd.db.getString(rsName2, "Remark"));

        cmd.entry.setValue("CFstNme", cmd.db.getString(rsName3, "FstNme"));
        cmd.entry.setValue("CLstNme", cmd.db.getString(rsName3, "LstNme"));
        cmd.entry.setValue("CTitle", cmd.db.getString(rsName3, "Title"));
        cmd.entry.setValue("CTel", cmd.db.getString(rsName3, "Tel"));
        cmd.entry.setValue("CFax", cmd.db.getString(rsName3, "Fax"));
        cmd.entry.setValue("CMobile", cmd.db.getString(rsName3, "Mobile"));
        cmd.entry.setValue("CEmail", cmd.db.getString(rsName3, "Email"));
        cmd.entry.setValue("CRemark", cmd.db.getString(rsName3, "Remark"));

        cmd.local.stringValue = PorNo;
        cmd.local.map.texts.put(MAP.BSPOR.PORNO, PorNo);
        cmd.local.map.texts.put(MAP.BSSUP.SUP, Sup);
        cmd.form.broadcastSignal(PorSignal.REFRESH);
    }

    @Override
    public void loadDefault() throws Exception {
        CopyPorNo = "";
        String Cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("PorDte", cmd.cal.getNow());
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("PorNo", "BSPOR.PorNo");
        super.setFieldHelpMessage("BSPOR.PorNo.help");
        super.setFieldSelectForm(BSNL1POR.class);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("general", "BSNS0POR.tab.general");
        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("PorDte", "BSPOR.PorDte");
        super.setFieldHelpMessage("BSPOR.PorDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addCalendarField("ShpDte", "BSPOR.ShpDte");
        super.setFieldHelpMessage("BSPOR.ShpDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addTextField("RefNum", "BSPOR.RefNum");
        super.setFieldHelpMessage("BSPOR.RefNum.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSPOR.Status");
        super.setFieldHelpMessage("BSPOR.Status.help");
        for (PorEnum.Status i : PorEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSPOR.Remark");
        super.setFieldHelpMessage("BSPOR.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("details", "BSNS0POR.tab.details", BSNL0PORD.class);

        super.addTab("address", "BSNS0POR.tab.address");
        super.addTextField("AAdd1", "BSPORA.Add1");
        super.setFieldHelpMessage("BSPORA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSPORA.Add2");
        super.setFieldHelpMessage("BSPORA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSPORA.City");
        super.setFieldHelpMessage("BSPORA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSPORA.ZipCde");
        super.setFieldHelpMessage("BSPORA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSPORA.Stte");
        super.setFieldHelpMessage("BSPORA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSPORA.Ctry");
        super.setFieldHelpMessage("BSPORA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSPORA.Tel");
        super.setFieldHelpMessage("BSPORA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSPORA.Fax");
        super.setFieldHelpMessage("BSPORA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSPORA.Remark");
        super.setFieldHelpMessage("BSPORA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSNS0POR.tab.contact");
        super.addTextField("CFstNme", "BSPORC.FstNme");
        super.setFieldHelpMessage("BSPORC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSPORC.LstNme");
        super.setFieldHelpMessage("BSPORC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSPORC.Title");
        super.setFieldHelpMessage("BSPORC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSPORC.Mobile");
        super.setFieldHelpMessage("BSPORC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSPORC.Email");
        super.setFieldHelpMessage("BSPORC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSPORC.Tel");
        super.setFieldHelpMessage("BSPORC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSPORC.Fax");
        super.setFieldHelpMessage("BSPORC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSPORC.Remark");
        super.setFieldHelpMessage("BSPORC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("spcinst", "BSNS0POR.tab.spcinst", BSNL0PORS.class);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Sup")) {
            try {
                String cmp = GET.Cmp(this);
                String Sup = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Sup)) {
                    return true;
                }

                boolean i = SUP.hasSup(this, cmp, Sup);
                if (!i) {
                    String title = "SUP.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSNS0POR.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSNS0POR.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(PorSignal.REFRESH);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSNS0POR.title";
    }
}
