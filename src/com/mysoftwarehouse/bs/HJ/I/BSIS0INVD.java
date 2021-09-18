/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.D.BSDL1SI;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.bs.db.INV.INVJ_Amt;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIS0INVD extends ModeEntryForm {

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
        String InvNo = cmd.entry.getString("InvNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            INVD.insert(this, Cmp, InvNo, Itm, Qty);
            INVE.copyFrmSi(this, Cmp, InvNo, Itm);
            INVJ_Amt.calculate(this, Cmp, InvNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String InvNo = cmd.entry.getString("InvNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            INVD.update(this, Cmp, InvNo, Itm, Price, Qty);
            INVJ_Amt.calculate(this, Cmp, InvNo);
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
        String Itm = cmd.entry.getString("Itm");
        try {
            cmd.db.begin();
            INVD.delete(this, Cmp, InvNo, Itm);
            INVE.delete(this, Cmp, InvNo, Itm);
            INVJ_Amt.calculate(this, Cmp, InvNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String InvNo = cmd.in.map.texts.get(MAP.BSINV.INVNO);
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);

        String rsName = "BSIS0INVD.load";
        INVD.select(this, rsName, cmp, InvNo, Itm);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("InvNo", InvNo);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));
        
        cmd.local.map.texts.put(MAP.BSINV.INVNO, InvNo);
        cmd.local.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.form.broadcastSignal(InvSignal.REFRESH);
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
                String title = "BSIS0INVD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSIS0INVD.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String InvNo = cmd.in.map.texts.get(MAP.BSINV.INVNO);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("InvNo", InvNo);
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

        super.addTextField("InvNo", "BSINV.InvNo");
        super.setFieldHelpMessage("BSINV.InvNo.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSIL0INV.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("Itm", "BSSI.Itm");
        super.setFieldHelpMessage("BSSI.Itm.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSDL1SI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSIS0INVD.tab.general");

        super.addNumericField("Price", "BSINVD.Price");
        super.setFieldHelpMessage("BSINVD.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSINVD.Qty");
        super.setFieldHelpMessage("BSINVD.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTab("details", "BSIS0INVD.tab.details", BSIL0INVE.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSIS0INVD.title";
    }
}
