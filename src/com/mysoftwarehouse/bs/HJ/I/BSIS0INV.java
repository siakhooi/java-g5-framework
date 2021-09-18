/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.C.BSCL1CUS;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.MAP.BSINV;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.INV.INVA;
import com.mysoftwarehouse.bs.db.INV.INVC;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.bs.db.INV.INVJ;
import com.mysoftwarehouse.bs.db.INV.INVJ_Amt;
import com.mysoftwarehouse.bs.db.INV.INVJ_load;
import com.mysoftwarehouse.bs.db.INV.INVR;
import com.mysoftwarehouse.bs.db.INV.INV_InvNo;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIS0INV extends ModeEntryForm {

    private String CopyInvNo = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.escapeKey = ModeAction.CloseForm;
        if (super.nextModeType == super.nextModeType.AddMode) {
            super.buttonConfig.ok = ModeAction.SaveAddAndEdit;
        }
        if (super.currentMode == null) {
            try {
                cmd.entry.setValue("InvNo", cmd.in.map.texts.get(BSINV.INVNO));
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSIS0INV.error", ex);
            }
        }
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setEditable("Cus", true);
        } else {
            cmd.entry.setEditable("Cus", false);
        }
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("address", false);
            cmd.entry.setTabEnabled("contact", false);
            cmd.entry.setTabEnabled("details", false);
            cmd.entry.setTabEnabled("adjustment", false);
            cmd.entry.setTabEnabled("terms", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("address", true);
            cmd.entry.setTabEnabled("contact", true);
            cmd.entry.setTabEnabled("details", true);
            cmd.entry.setTabEnabled("adjustment", true);
            cmd.entry.setTabEnabled("terms", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Cus = cmd.entry.getString("Cus");
        Calendar InvDte = cmd.entry.getCalendar("InvDte");
        Calendar DueDte = cmd.entry.getCalendar("DueDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            String InvNo = INV_InvNo.getNextInvNo(this, Cmp);
            InvDte = CalendarTool.getDate(InvDte);
            DueDte = CalendarTool.getDate(DueDte);
            INV.insert(this, Cmp, Cus, InvNo, InvDte, DueDte, RefNum, Status, Remark);

            if (!cmd.data.isNull(CopyInvNo)) {
                INVA.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
                INVC.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
                INVJ.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
                INVR.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
                INVD.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
                INVE.copyFrmInv(this, Cmp, InvNo, CopyInvNo);
            } else {
                INVA.copyFrmCus(this, Cmp, InvNo, Cus);
                INVC.copyFrmCus(this, Cmp, InvNo, Cus);
                INVJ_load.loadDefaultAdjustment(this, Cmp, InvNo);
                INVR.loadDefaultTerm(this, Cmp, InvNo);
            }
            INVJ_Amt.calculate(this, Cmp, InvNo);

            cmd.db.commit();
            cmd.entry.setValue("InvNo", InvNo);

        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        //String Cus = cmd.entry.getString("Cus");
        String InvNo = cmd.entry.getString("InvNo");
        Calendar InvDte = cmd.entry.getCalendar("InvDte");
        Calendar DueDte = cmd.entry.getCalendar("DueDte");
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
            InvDte = CalendarTool.getDate(InvDte);
            DueDte = CalendarTool.getDate(DueDte);
            INV.update(this, Cmp, InvNo, InvDte, DueDte, RefNum, Status, Remark);
            INVA.update(this, Cmp, InvNo, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            INVC.update(this, Cmp, InvNo, CFstNme, CLstNme, CTitle, CTel, CFax,
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
        String InvNo = cmd.entry.getString("InvNo");
        cmd.db.begin();
        INV.delete(this, Cmp, InvNo);
        INVD.delete(this, Cmp, InvNo);
        INVE.delete(this, Cmp, InvNo);
        INVA.delete(this, Cmp, InvNo);
        INVC.delete(this, Cmp, InvNo);
        INVJ.delete(this, Cmp, InvNo);
        INVR.delete(this, Cmp, InvNo);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String InvNo = cmd.entry.getString("InvNo");
        CopyInvNo = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyInvNo = InvNo;
        }

        String rsName = "BSIS0INVL1";
        INV.select(this, rsName, Cmp, InvNo);
        String rsName2 = "BSIS0INVL2";
        INVA.select(this, rsName2, Cmp, InvNo);
        String rsName3 = "BSIS0INVL3";
        INVC.select(this, rsName3, Cmp, InvNo);

        cmd.entry.setValue("Cmp", Cmp);
        //cmd.entry.setValue("InvNo", InvNo);
        cmd.entry.setValue("Cus", cmd.db.getString(rsName, "Cus"));
        cmd.entry.setValue("InvDte", cmd.db.getTimestamp(rsName, "InvDte"));
        cmd.entry.setValue("DueDte", cmd.db.getTimestamp(rsName, "DueDte"));
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

        cmd.local.stringValue = InvNo;
        cmd.local.map.texts.put(MAP.BSINV.INVNO, InvNo);
        cmd.form.broadcastSignal(InvSignal.REFRESH);
    }

    @Override
    public void loadDefault() throws Exception {
        CopyInvNo = "";
        String Cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("InvDte", cmd.cal.getNow());
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

        super.addTextField("InvNo", "BSINV.InvNo");
        super.setFieldHelpMessage("BSINV.InvNo.help");
        //super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSIL0INV.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("general", "BSIS0INV.tab.general");
        super.addTextField("Cus", "BSCUS.Cus");
        super.setFieldHelpMessage("BSCUS.Cus.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("InvDte", "BSINV.InvDte");
        super.setFieldHelpMessage("BSINV.QttDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addCalendarField("DueDte", "BSINV.DueDte");
        super.setFieldHelpMessage("BSINV.DueDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addTextField("RefNum", "BSINV.RefNum");
        super.setFieldHelpMessage("BSINV.RefNum.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSINV.Status");
        super.setFieldHelpMessage("BSINV.Status.help");
        for (InvEnum.Status i : InvEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSINV.Remark");
        super.setFieldHelpMessage("BSINV.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("details", "BSIS0INV.tab.details", BSIL0INVD.class);

        super.addTab("address", "BSIS0INV.tab.address");
        super.addTextField("AAdd1", "BSINVA.Add1");
        super.setFieldHelpMessage("BSINVA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSINVA.Add2");
        super.setFieldHelpMessage("BSINVA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSINVA.City");
        super.setFieldHelpMessage("BSINVA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSINVA.ZipCde");
        super.setFieldHelpMessage("BSINVA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSINVA.Stte");
        super.setFieldHelpMessage("BSINVA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSINVA.Ctry");
        super.setFieldHelpMessage("BSINVA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSINVA.Tel");
        super.setFieldHelpMessage("BSINVA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSINVA.Fax");
        super.setFieldHelpMessage("BSINVA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSINVA.Remark");
        super.setFieldHelpMessage("BSINVA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSIS0INV.tab.contact");
        super.addTextField("CFstNme", "BSINVC.FstNme");
        super.setFieldHelpMessage("BSINVC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSINVC.LstNme");
        super.setFieldHelpMessage("BSINVC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSINVC.Title");
        super.setFieldHelpMessage("BSINVC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSINVC.Mobile");
        super.setFieldHelpMessage("BSINVC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSINVC.Email");
        super.setFieldHelpMessage("BSINVC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSINVC.Tel");
        super.setFieldHelpMessage("BSINVC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSINVC.Fax");
        super.setFieldHelpMessage("BSINVC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSINVC.Remark");
        super.setFieldHelpMessage("BSINVC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("adjustment", "BSIS0INV.tab.adjustment", BSIL0INVJ.class);

        super.addTab("terms", "BSIS0INV.tab.terms", BSIL0INVR.class);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Cus")) {
            try {
                String cmp = GET.Cmp(this);
                String Cus = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Cus)) {
                    return true;
                }

                boolean i = CUS.hasCUS(this, cmp, Cus);
                if (!i) {
                    String title = "CUS.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSIS0INV.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSIS0INV.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(InvSignal.REFRESH);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSIS0INV.title";
    }
}
