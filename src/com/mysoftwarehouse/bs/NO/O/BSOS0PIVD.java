/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.KM.K.BSKL1SUP;
import com.mysoftwarehouse.bs.KM.L.BSLL1PIP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PI.PIP;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import com.mysoftwarehouse.bs.db.PIV.PIV_Amt;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOS0PIVD extends ModeEntryForm {

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
        String Sup = cmd.entry.getString("Sup");
        String PivNo = cmd.entry.getString("PivNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            PIVD.insert(this, Cmp, PivNo, Itm, Qty, Sup);
            PIVE.copyFrmPi(this, Cmp, PivNo, Itm);
            PIV_Amt.calculate(this, Cmp, PivNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String PivNo = cmd.entry.getString("PivNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            PIVD.update(this, Cmp, PivNo, Itm, Price, Qty);
            PIV_Amt.calculate(this, Cmp, PivNo);
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
        String Itm = cmd.entry.getString("Itm");
        try {
            cmd.db.begin();
            PIVD.delete(this, Cmp, PivNo, Itm);
            PIVE.delete(this, Cmp, PivNo, Itm);
            PIV_Amt.calculate(this, Cmp, PivNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String Cmp = GET.Cmp(this);
        String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
        String PivNo = cmd.in.map.texts.get(MAP.BSPIV.PIVNO);
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);

        String rsName = "BSOS0PIVD.load";
        PIVD.select(this, rsName, Cmp, PivNo, Itm);

        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("PivNo", PivNo);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Sup", Sup);
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));

        cmd.local.map.texts.put(MAP.BSPIV.PIVNO, PivNo);
        cmd.local.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.form.broadcastSignal(PivSignal.REFRESH);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Itm")) {
            try {
                String Cmp = GET.Cmp(this);
                String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
                String Itm = cmd.entry.getString(fieldName);
                boolean i = PIP.hasItm(this, Cmp, Itm, Sup);
                if (!i) {
                    String title = "PIP.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            } catch (SQLException ex) {
                String title = "BSOS0PIVD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSOS0PIVD.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String PivNo = cmd.in.map.texts.get(MAP.BSPIV.PIVNO);
        String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("PivNo", PivNo);
        cmd.entry.setValue("Sup", Sup);
        cmd.entry.setValue("Price", 0);
    }

    @Override
    public void onFieldOutExit(String fieldName) {
        String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
        cmd.out.map.texts.put(MAP.BSSUP.SUP, Sup);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("PivNo", "BSPIV.PivNo");
        super.setFieldHelpMessage("BSPIV.PivNo.help");
        super.setFieldSelectForm(BSOL1PIV.class);
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Itm", "BSPI.Itm");
        super.setFieldHelpMessage("BSPI.Itm.help");
        super.setFieldSelectForm(BSLL1PIP.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);


        super.addTab("general", "BSOS0PIVD.tab.general");

        super.addNumericField("Price", "BSPIVD.Price");
        super.setFieldHelpMessage("BSPIVD.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSPIVD.Qty");
        super.setFieldHelpMessage("BSPIVD.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTab("details", "BSOS0PIVD.tab.details", BSOL0PIVE.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSOS0PIVD.title";
    }
}
