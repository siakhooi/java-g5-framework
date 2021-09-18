/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.C.BSCL1CUS;
import com.mysoftwarehouse.bs.CG.F.BSFL1SY;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.RcpEnum;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import com.mysoftwarehouse.bs.db.RCP.RCPA;
import com.mysoftwarehouse.bs.db.RCP.RCPC;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPE;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_load;
import com.mysoftwarehouse.bs.db.RCP.RCPR;
import com.mysoftwarehouse.bs.db.RCP.RCP_RcpNo;
import com.mysoftwarehouse.bs.db.SY.SY;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJS0RCP extends ModeEntryForm {

    private String CopyRcpNo = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.escapeKey = ModeAction.CloseForm;
        if (super.nextModeType == super.nextModeType.AddMode) {
            super.buttonConfig.ok = ModeAction.SaveAddAndEdit;
        }
        if (super.currentMode == null) {
            try {
                cmd.entry.setValue("RcpNo", cmd.in.map.texts.get(MAP.BSRCP.RCPNO));
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSJS0RCP.error", ex);
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
//        String RcpNo = cmd.entry.getString("RcpNo");
        Calendar RcpDte = cmd.entry.getCalendar("RcpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");
        String PayTyp = cmd.entry.getString("PayTyp");
        String PayRefNum = cmd.entry.getString("PayRefNum");
        String PayInfo = cmd.entry.getString("PayInfo");

        try {
            cmd.db.begin();
            String RcpNo = RCP_RcpNo.getNextRcpNo(this, Cmp);
            RcpDte = CalendarTool.getDate(RcpDte);
            RCP.insert(this, Cmp, Cus, RcpNo, RcpDte, RefNum, Status, Remark,
                    PayTyp, PayRefNum, PayInfo);

            if (!cmd.data.isNull(CopyRcpNo)) {
                RCPA.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
                RCPC.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
                RCPJ.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
                RCPR.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
                RCPD.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
                RCPE.copyFrmRcp(this, Cmp, RcpNo, CopyRcpNo);
            } else {
                RCPA.copyFrmCus(this, Cmp, RcpNo, Cus);
                RCPC.copyFrmCus(this, Cmp, RcpNo, Cus);
                RCPJ_load.loadDefaultAdjustment(this, Cmp, RcpNo);
                RCPR.loadDefaultTerm(this, Cmp, RcpNo);
            }

            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
            cmd.entry.setValue("RcpNo", RcpNo);

        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        //String Cus = cmd.entry.getString("Cus");
        String RcpNo = cmd.entry.getString("RcpNo");
        Calendar RcpDte = cmd.entry.getCalendar("RcpDte");
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
            RcpDte = CalendarTool.getDate(RcpDte);
            RCP.update(this, Cmp, RcpNo, RcpDte, RefNum, Status, Remark,
                    PayTyp, PayRefNum, PayInfo);
            RCPA.update(this, Cmp, RcpNo, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            RCPC.update(this, Cmp, RcpNo, CFstNme, CLstNme, CTitle, CTel, CFax,
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
        String RcpNo = cmd.entry.getString("RcpNo");
        cmd.db.begin();
        RCP.delete(this, Cmp, RcpNo);
        RCPD.delete(this, Cmp, RcpNo);
        RCPE.delete(this, Cmp, RcpNo);
        RCPA.delete(this, Cmp, RcpNo);
        RCPC.delete(this, Cmp, RcpNo);
        RCPJ.delete(this, Cmp, RcpNo);
        RCPR.delete(this, Cmp, RcpNo);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String RcpNo = cmd.entry.getString("RcpNo");
        CopyRcpNo = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyRcpNo = RcpNo;
        }

        String rsName = "BSJS0RCPL1";
        RCP.select(this, rsName, Cmp, RcpNo);
        String rsName2 = "BSJS0RCPL2";
        RCPA.select(this, rsName2, Cmp, RcpNo);
        String rsName3 = "BSJS0RCPL3";
        RCPC.select(this, rsName3, Cmp, RcpNo);

        cmd.entry.setValue("Cmp", Cmp);
        //cmd.entry.setValue("RcpNo", RcpNo);
        cmd.entry.setValue("Cus", cmd.db.getString(rsName, "Cus"));
        cmd.entry.setValue("RcpDte", cmd.db.getTimestamp(rsName, "RcpDte"));
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

        cmd.local.stringValue = RcpNo;
        cmd.local.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
        cmd.form.broadcastSignal(RcpSignal.REFRESH);
    }

    @Override
    public void loadDefault() throws Exception {
        CopyRcpNo = "";
        String Cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("RcpDte", cmd.cal.getNow());
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

        super.addTextField("RcpNo", "BSRCP.RcpNo");
        super.setFieldHelpMessage("BSRCP.RcpNo.help");
        //super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSJL1RCP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("general", "BSJS0RCP.tab.general");
        super.addTextField("Cus", "BSCUS.Cus");
        super.setFieldHelpMessage("BSCUS.Cus.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("RcpDte", "BSRCP.RcpDte");
        super.setFieldHelpMessage("BSRCP.RcpDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addTextField("RefNum", "BSRCP.RefNum");
        super.setFieldHelpMessage("BSRCP.RefNum.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSRCP.Status");
        super.setFieldHelpMessage("BSRCP.Status.help");
        for (RcpEnum.Status i : RcpEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("PayTyp", "BSSY.PayTyp");
        super.setFieldHelpMessage("BSSY.PayTyp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSFL1SY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("PayRefNum", "BSRCP.PayRefNum");
        super.setFieldHelpMessage("BSRCP.PayRefNum.help");
        super.setFieldMaximumLength(20);
        super.setFieldTextColumn(20);

        super.addTextField("PayInfo", "BSRCP.PayInfo");
        super.setFieldHelpMessage("BSRCP.PayInfo.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("Remark", "BSRCP.Remark");
        super.setFieldHelpMessage("BSRCP.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("details", "BSJS0RCP.tab.details", BSJL0RCPD.class);

        super.addTab("address", "BSJS0RCP.tab.address");
        super.addTextField("AAdd1", "BSRCPA.Add1");
        super.setFieldHelpMessage("BSRCPA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSRCPA.Add2");
        super.setFieldHelpMessage("BSRCPA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSRCPA.City");
        super.setFieldHelpMessage("BSRCPA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSRCPA.ZipCde");
        super.setFieldHelpMessage("BSRCPA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSRCPA.Stte");
        super.setFieldHelpMessage("BSRCPA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSRCPA.Ctry");
        super.setFieldHelpMessage("BSRCPA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSRCPA.Tel");
        super.setFieldHelpMessage("BSRCPA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSRCPA.Fax");
        super.setFieldHelpMessage("BSRCPA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSRCPA.Remark");
        super.setFieldHelpMessage("BSRCPA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSJS0RCP.tab.contact");
        super.addTextField("CFstNme", "BSRCPC.FstNme");
        super.setFieldHelpMessage("BSRCPC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSRCPC.LstNme");
        super.setFieldHelpMessage("BSRCPC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSRCPC.Title");
        super.setFieldHelpMessage("BSRCPC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSRCPC.Mobile");
        super.setFieldHelpMessage("BSRCPC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSRCPC.Email");
        super.setFieldHelpMessage("BSRCPC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSRCPC.Tel");
        super.setFieldHelpMessage("BSRCPC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSRCPC.Fax");
        super.setFieldHelpMessage("BSRCPC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSRCPC.Remark");
        super.setFieldHelpMessage("BSRCPC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("adjustment", "BSJS0RCP.tab.adjustment", BSJL0RCPJ.class);

        super.addTab("terms", "BSJS0RCP.tab.terms", BSJL0RCPR.class);
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
                String title = "BSJS0RCP.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSJS0RCP.error";
                cmd.log.sysSevere(title, ex);
            }
        } else if (fieldName.equals("PayTyp")) {
            try {
                String Cmp = GET.Cmp(this);
                String PayTyp = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(PayTyp)) {
                    return true;
                }

                boolean i = SY.hasSY(this, Cmp, PayTyp);
                if (!i) {
                    String title = "SY.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSJS0RCP.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSJS0RCP.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(RcpSignal.REFRESH);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSJS0RCP.title";
    }
}
