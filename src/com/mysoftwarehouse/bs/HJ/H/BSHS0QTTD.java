/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.D.BSDL1SI;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_Amt;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHS0QTTD extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.copy = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setMandatory("Price", false);
            cmd.entry.setEditable("Price", false);
            cmd.entry.setTabEnabled("details", false);
        } else {
            //cmd.entry.setEditable("Price", true);
            cmd.entry.setMandatory("Price", true);
            cmd.entry.setTabEnabled("details", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String QttNo = cmd.entry.getString("QttNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            QTTD.insert(this, Cmp, QttNo, Itm, Qty);
            QTTE.copyFrmSi(this, Cmp, QttNo, Itm);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String QttNo = cmd.entry.getString("QttNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            QTTD.update(this, Cmp, QttNo, Itm, Price, Qty);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
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
        String Itm = cmd.entry.getString("Itm");
        try {
            cmd.db.begin();
            QTTD.delete(this, Cmp, QttNo, Itm);
            QTTE.delete(this, Cmp, QttNo, Itm);
            QTTJ_Amt.calculate(this, Cmp, QttNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);

        String rsName = "BSHS0QTTD.load";
        QTTD.select(this, rsName, Cmp, QttNo, Itm);

        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("QttNo", QttNo);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));

        cmd.local.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.local.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.form.broadcastSignal(QttSignal.REFRESH);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Itm")) {
            try {
                String cmp = GET.Cmp(this);
                String Itm = cmd.entry.getString(fieldName);
                boolean i = SI.hasItm(this, cmp, Itm);
                if (!i) {
                    String title = "SI.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSHS0QTTD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSHS0QTTD.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("QttNo", QttNo);
        cmd.entry.setValue("Price", 0);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("QttNo", "BSQTT.QttNo");
        super.setFieldHelpMessage("BSQTT.QttNo.help");
        super.setFieldSelectForm(BSHL1QTT.class);
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("Itm", "BSSI.Itm");
        super.setFieldHelpMessage("BSSI.Itm.help");
        super.setFieldSelectForm(BSDL1SI.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSHS0QTTD.tab.general");

        super.addNumericField("Price", "BSQTTD.Price");
        super.setFieldHelpMessage("BSQTTD.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSQTTD.Qty");
        super.setFieldHelpMessage("BSQTTD.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTab("details", "BSHS0QTTD.tab.details", BSHL0QTTE.class);

    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSHS0QTTD.title";
    }
}
