/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.KM.K.BSKL1SUP;
import com.mysoftwarehouse.bs.KM.M.BSML1PY;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.MAP.BSPIV;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PivEnum;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import com.mysoftwarehouse.bs.db.PIV.PIVA;
import com.mysoftwarehouse.bs.db.PIV.PIVC;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import com.mysoftwarehouse.bs.db.PIV.PIV_Amt;
import com.mysoftwarehouse.bs.db.PIV.PIV_PivNo;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOS0PIV extends ModeEntryForm {

    private String CopyPivNo = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.escapeKey = ModeAction.CloseForm;
        if (super.nextModeType == super.nextModeType.AddMode) {
            super.buttonConfig.ok = ModeAction.SaveAddAndEdit;
        }
        if (super.currentMode == null) {
            try {
                cmd.entry.setValue("PivNo", cmd.in.map.texts.get(BSPIV.PIVNO));
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSOS0PIV.error", ex);
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
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("address", true);
            cmd.entry.setTabEnabled("contact", true);
            cmd.entry.setTabEnabled("details", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Sup = cmd.entry.getString("Sup");
        //String PivNo = cmd.entry.getString("PivNo");
        Calendar PivDte = cmd.entry.getCalendar("PivDte");
        //Calendar ShpDte = cmd.entry.getCalendar("ShpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");
        String PayTyp = cmd.entry.getString("PayTyp");
        String PayRefNum = cmd.entry.getString("PayRefNum");
        String PayInfo = cmd.entry.getString("PayInfo");

        try {
            cmd.db.begin();
            String PivNo = PIV_PivNo.getNextPivNo(this, Cmp);
            PivDte = CalendarTool.getDate(PivDte);
            PIV.insert(this, Cmp, Sup, PivNo, PivDte, RefNum, Status, Remark,
                    PayTyp, PayRefNum, PayInfo);
            if (!cmd.data.isNull(CopyPivNo)) {
                PIVA.copyFrmPiv(this, Cmp, PivNo, CopyPivNo);
                PIVC.copyFrmPiv(this, Cmp, PivNo, CopyPivNo);
                PIVD.copyFrmPiv(this, Cmp, PivNo, CopyPivNo);
                PIVE.copyFrmPiv(this, Cmp, PivNo, CopyPivNo);
            } else {
                PIVA.copyFrmSup(this, Cmp, PivNo, Sup);
                PIVC.copyFrmSup(this, Cmp, PivNo, Sup);
            }

            PIV_Amt.calculate(this, Cmp, PivNo);
            cmd.db.commit();
            cmd.entry.setValue("PivNo", PivNo);

        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        //String Sup = cmd.entry.getString("Sup");
        String PivNo = cmd.entry.getString("PivNo");
        Calendar PivDte = cmd.entry.getCalendar("PivDte");
        //Calendar ShpDte = cmd.entry.getCalendar("ShpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");
        String PayTyp = cmd.entry.getString("PayTyp");
        String PayRefNum = cmd.entry.getString("PayRefNum");
        String PayInfo = cmd.entry.getString("PayInfo");

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
            PivDte = CalendarTool.getDate(PivDte);
            //ShpDte = CalendarTool.getDate(ShpDte);
            PIV.update(this, Cmp, PivNo, PivDte, RefNum, Status, Remark,
                    PayTyp, PayRefNum, PayInfo);
            PIVA.update(this, Cmp, PivNo, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            PIVC.update(this, Cmp, PivNo, CFstNme, CLstNme, CTitle, CTel, CFax,
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
        String PivNo = cmd.entry.getString("PivNo");
        cmd.db.begin();
        PIV.delete(this, Cmp, PivNo);
        PIVA.delete(this, Cmp, PivNo);
        PIVC.delete(this, Cmp, PivNo);
        PIVD.delete(this, Cmp, PivNo);
        PIVE.delete(this, Cmp, PivNo);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String PivNo = cmd.entry.getString("PivNo");
        CopyPivNo = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyPivNo = PivNo;
        }

        String rsName = "BSOS0PIVL1";
        PIV.select(this, rsName, Cmp, PivNo);
        String rsName2 = "BSOS0PIVL2";
        PIVA.select(this, rsName2, Cmp, PivNo);
        String rsName3 = "BSOS0PIVL3";
        PIVC.select(this, rsName3, Cmp, PivNo);

        String Sup = cmd.db.getString(rsName, "Sup");
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("Sup", Sup);
        cmd.entry.setValue("PivDte", cmd.db.getTimestamp(rsName, "PivDte"));
        cmd.entry.setValue("RefNum", cmd.db.getString(rsName, "RefNum"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
        cmd.entry.setValue("PayTyp", cmd.db.getString(rsName, "PayTyp"));
        cmd.entry.setValue("PayRefNum", cmd.db.getString(rsName, "PayRefNum"));
        cmd.entry.setValue("PayInfo", cmd.db.getString(rsName, "PayInfo"));

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

        cmd.local.stringValue = PivNo;
        cmd.local.map.texts.put(MAP.BSPIV.PIVNO, PivNo);
        cmd.local.map.texts.put(MAP.BSSUP.SUP, Sup);
        cmd.form.broadcastSignal(PivSignal.REFRESH);
    }

    @Override
    public void loadDefault() throws Exception {
        CopyPivNo = "";
        String Cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("PivDte", cmd.cal.getNow());
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

        super.addTextField("PivNo", "BSPIV.PivNo");
        super.setFieldHelpMessage("BSPIV.PivNo.help");
        super.setFieldSelectForm(BSOL1PIV.class);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("general", "BSOS0PIV.tab.general");
        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("PivDte", "BSPIV.PivDte");
        super.setFieldHelpMessage("BSPIV.PivDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addTextField("RefNum", "BSPIV.RefNum");
        super.setFieldHelpMessage("BSPIV.RefNum.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSPIV.Status");
        super.setFieldHelpMessage("BSPIV.Status.help");
        for (PivEnum.Status i : PivEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("PayTyp", "BSPY.PayTyp");
        super.setFieldHelpMessage("BSPY.PayTyp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSML1PY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("PayRefNum", "BSPIV.PayRefNum");
        super.setFieldHelpMessage("BSPIV.PayRefNum.help");
        super.setFieldMaximumLength(20);
        super.setFieldTextColumn(20);

        super.addTextField("PayInfo", "BSPIV.PayInfo");
        super.setFieldHelpMessage("BSPIV.PayInfo.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("Remark", "BSPIV.Remark");
        super.setFieldHelpMessage("BSPIV.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("details", "BSOS0PIV.tab.details", BSOL0PIVD.class);

        super.addTab("address", "BSOS0PIV.tab.address");
        super.addTextField("AAdd1", "BSPIVA.Add1");
        super.setFieldHelpMessage("BSPIVA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSPIVA.Add2");
        super.setFieldHelpMessage("BSPIVA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSPIVA.City");
        super.setFieldHelpMessage("BSPIVA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSPIVA.ZipCde");
        super.setFieldHelpMessage("BSPIVA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSPIVA.Stte");
        super.setFieldHelpMessage("BSPIVA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSPIVA.Ctry");
        super.setFieldHelpMessage("BSPIVA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSPIVA.Tel");
        super.setFieldHelpMessage("BSPIVA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSPIVA.Fax");
        super.setFieldHelpMessage("BSPIVA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSPIVA.Remark");
        super.setFieldHelpMessage("BSPIVA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSOS0PIV.tab.contact");
        super.addTextField("CFstNme", "BSPIVC.FstNme");
        super.setFieldHelpMessage("BSPIVC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSPIVC.LstNme");
        super.setFieldHelpMessage("BSPIVC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSPIVC.Title");
        super.setFieldHelpMessage("BSPIVC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSPIVC.Mobile");
        super.setFieldHelpMessage("BSPIVC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSPIVC.Email");
        super.setFieldHelpMessage("BSPIVC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSPIVC.Tel");
        super.setFieldHelpMessage("BSPIVC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSPIVC.Fax");
        super.setFieldHelpMessage("BSPIVC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSPIVC.Remark");
        super.setFieldHelpMessage("BSPIVC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
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
                String title = "BSOS0PIV.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSOS0PIV.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(PivSignal.REFRESH);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSOS0PIV.title";
    }
}
