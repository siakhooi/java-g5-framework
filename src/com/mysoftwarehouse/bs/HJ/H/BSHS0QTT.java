/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.C.BSCL1CUS;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.MAP.BSQTT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.QttEnum;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import com.mysoftwarehouse.bs.db.QTT.QTTA;
import com.mysoftwarehouse.bs.db.QTT.QTTC;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.bs.db.QTT.QTTJ;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_Amt;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_load;
import com.mysoftwarehouse.bs.db.QTT.QTTR;
import com.mysoftwarehouse.bs.db.QTT.QTT_QttNo;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHS0QTT extends ModeEntryForm {

    private String CopyQttNo = "";

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.escapeKey = ModeAction.CloseForm;
        if (super.nextModeType == super.nextModeType.AddMode) {
            super.buttonConfig.ok = ModeAction.SaveAddAndEdit;
        }
        if (super.currentMode == null) {
            try {
                cmd.entry.setValue("QttNo", cmd.in.map.texts.get(BSQTT.QTTNO));
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSHS0QTT.error", ex);
            }
        }
    }

    @Override
    public void finishModeAction() {
        //cmd.entry.setEditable("QttNo", false);
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
        //String QttNo = cmd.entry.getString("QttNo");
        Calendar QttDte = cmd.entry.getCalendar("QttDte");
        Calendar ExpDte = cmd.entry.getCalendar("ExpDte");
        String RefNum = cmd.entry.getString("RefNum");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");


        try {
            cmd.db.begin();
            String QttNo = QTT_QttNo.getNextQttNo(this, Cmp);
            QttDte = CalendarTool.getDate(QttDte);
            ExpDte = CalendarTool.getDate(ExpDte);
            QTT.insert(this, Cmp, Cus, QttNo, QttDte, ExpDte, RefNum, Status, Remark);
            if (!cmd.data.isNull(CopyQttNo)) {
                QTTA.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
                QTTC.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
                QTTJ.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
                QTTR.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
                QTTD.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
                QTTE.copyFrmQtt(this, Cmp, QttNo, CopyQttNo);
            } else {
                QTTA.copyFrmCus(this, Cmp, QttNo, Cus);
                QTTC.copyFrmCus(this, Cmp, QttNo, Cus);
                QTTJ_load.loadDefaultAdjustment(this, Cmp, QttNo);
                QTTR.loadDefaultTerm(this, Cmp, QttNo);
            }
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
            cmd.entry.setValue("QttNo", QttNo);
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
//        String Cus = cmd.entry.getString("Cus");
        String QttNo = cmd.entry.getString("QttNo");
        Calendar QttDte = cmd.entry.getCalendar("QttDte");
        Calendar ExpDte = cmd.entry.getCalendar("ExpDte");
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
            QttDte = CalendarTool.getDate(QttDte);
            ExpDte = CalendarTool.getDate(ExpDte);

            QTT.update(this, Cmp, QttNo, QttDte, ExpDte, RefNum, Status, Remark);
            QTTA.update(this, Cmp, QttNo, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            QTTC.update(this, Cmp, QttNo, CFstNme, CLstNme, CTitle, CTel, CFax,
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
        String QttNo = cmd.entry.getString("QttNo");
        cmd.db.begin();
        QTT.delete(this, Cmp, QttNo);
        QTTD.delete(this, Cmp, QttNo);
        QTTE.delete(this, Cmp, QttNo);
        QTTA.delete(this, Cmp, QttNo);
        QTTC.delete(this, Cmp, QttNo);
        QTTJ.delete(this, Cmp, QttNo);
        QTTR.delete(this, Cmp, QttNo);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String QttNo = cmd.entry.getString("QttNo");
        CopyQttNo = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyQttNo = QttNo;
        }

        String rsName = "BSHS0QTTL1";
        QTT.select(this, rsName, Cmp, QttNo);
        String rsName2 = "BSHS0QTTL2";
        QTTA.select(this, rsName2, Cmp, QttNo);
        String rsName3 = "BSHS0QTTL3";
        QTTC.select(this, rsName3, Cmp, QttNo);

        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("Cus", cmd.db.getString(rsName, "Cus"));
        cmd.entry.setValue("QttDte", cmd.db.getTimestamp(rsName, "QttDte"));
        cmd.entry.setValue("ExpDte", cmd.db.getTimestamp(rsName, "ExpDte"));
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

        cmd.local.stringValue = QttNo;
        cmd.local.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.form.broadcastSignal(QttSignal.REFRESH);
    }

    @Override
    public void loadDefault() throws Exception {
        CopyQttNo = "";
        String Cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("QttDte", cmd.cal.getNow());
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

        super.addTextField("QttNo", "BSQTT.QttNo");
        super.setFieldHelpMessage("BSQTT.QttNo.help");
        //super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSHL0QTT.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTab("general", "BSHS0QTT.tab.general");
        super.addTextField("Cus", "BSCUS.Cus");
        super.setFieldHelpMessage("BSCUS.Cus.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("QttDte", "BSQTT.QttDte");
        super.setFieldHelpMessage("BSQTT.QttDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addCalendarField("ExpDte", "BSQTT.ExpDte");
        super.setFieldHelpMessage("BSQTT.ExpDte.help");
        super.setFieldMandatory(true);
        super.setFieldFormat(true, false);

        super.addTextField("RefNum", "BSQTT.RefNum");
        super.setFieldHelpMessage("BSQTT.RefNum.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSQTT.Status");
        super.setFieldHelpMessage("BSQTT.Status.help");
        for (QttEnum.Status i : QttEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSQTT.Remark");
        super.setFieldHelpMessage("BSQTT.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("details", "BSHS0QTT.tab.details", BSHL0QTTD.class);

        super.addTab("address", "BSHS0QTT.tab.address");
        super.addTextField("AAdd1", "BSQTTA.Add1");
        super.setFieldHelpMessage("BSQTTA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSQTTA.Add2");
        super.setFieldHelpMessage("BSQTTA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSQTTA.City");
        super.setFieldHelpMessage("BSQTTA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSQTTA.ZipCde");
        super.setFieldHelpMessage("BSQTTA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSQTTA.Stte");
        super.setFieldHelpMessage("BSQTTA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSQTTA.Ctry");
        super.setFieldHelpMessage("BSQTTA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSQTTA.Tel");
        super.setFieldHelpMessage("BSQTTA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSQTTA.Fax");
        super.setFieldHelpMessage("BSQTTA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSQTTA.Remark");
        super.setFieldHelpMessage("BSQTTA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSHS0QTT.tab.contact");
        super.addTextField("CFstNme", "BSQTTC.FstNme");
        super.setFieldHelpMessage("BSQTTC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSQTTC.LstNme");
        super.setFieldHelpMessage("BSQTTC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSQTTC.Title");
        super.setFieldHelpMessage("BSQTTC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSQTTC.Mobile");
        super.setFieldHelpMessage("BSQTTC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSQTTC.Email");
        super.setFieldHelpMessage("BSQTTC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSQTTC.Tel");
        super.setFieldHelpMessage("BSQTTC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSQTTC.Fax");
        super.setFieldHelpMessage("BSQTTC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSQTTC.Remark");
        super.setFieldHelpMessage("BSQTTC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("adjustment", "BSHS0QTT.tab.adjustment", BSHL0QTTJ.class);

        super.addTab("terms", "BSHS0QTT.tab.terms", BSHL0QTTR.class);
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
                String title = "BSHS0QTT.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSHS0QTT.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(QttSignal.REFRESH);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSHS0QTT.title";
    }
}
