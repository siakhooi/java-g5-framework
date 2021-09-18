/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

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
import com.mysoftwarehouse.bs.db.POR.PORD;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.bs.db.POR.POR_Amt;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNS0PORD extends ModeEntryForm {

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
        String PorNo = cmd.entry.getString("PorNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            PORD.insert(this, Cmp, PorNo, Itm, Qty, Sup);
            PORE.copyFrmPi(this, Cmp, PorNo, Itm);
            POR_Amt.calculate(this, Cmp, PorNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String PorNo = cmd.entry.getString("PorNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            PORD.update(this, Cmp, PorNo, Itm, Price, Qty);
            POR_Amt.calculate(this, Cmp, PorNo);
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
        String Itm = cmd.entry.getString("Itm");
        try {
            cmd.db.begin();
            PORD.delete(this, Cmp, PorNo, Itm);
            PORE.delete(this, Cmp, PorNo, Itm);
            POR_Amt.calculate(this, Cmp, PorNo);
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
        String PorNo = cmd.in.map.texts.get(MAP.BSPOR.PORNO);
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);

        String rsName = "BSNS0PORD.load";
        PORD.select(this, rsName, Cmp, PorNo, Itm);

        cmd.entry.setValue("Cmp", Cmp);
        cmd.entry.setValue("PorNo", PorNo);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Sup", Sup);
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));

        cmd.local.map.texts.put(MAP.BSPOR.PORNO, PorNo);
        cmd.local.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.form.broadcastSignal(PorSignal.REFRESH);
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
                String title = "BSNS0PORD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSNS0PORD.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String PorNo = cmd.in.map.texts.get(MAP.BSPOR.PORNO);
        String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("PorNo", PorNo);
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

        super.addTextField("PorNo", "BSPOR.PorNo");
        super.setFieldHelpMessage("BSPOR.PorNo.help");
        super.setFieldSelectForm(BSNL1POR.class);
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

        super.addTab("general", "BSNS0PORD.tab.general");

        super.addNumericField("Price", "BSPORD.Price");
        super.setFieldHelpMessage("BSPORD.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSPORD.Qty");
        super.setFieldHelpMessage("BSPORD.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTab("details", "BSNS0PORD.tab.details", BSNL0PORE.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSNS0PORD.title";
    }
}
