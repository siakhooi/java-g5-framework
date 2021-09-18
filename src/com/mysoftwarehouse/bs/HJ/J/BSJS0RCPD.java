/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.CG.D.BSDL1SI;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPE;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJS0RCPD extends ModeEntryForm {

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
        String RcpNo = cmd.entry.getString("RcpNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            RCPD.insert(this, Cmp, RcpNo, Itm, Qty);
            RCPE.copyFrmSi(this, Cmp, RcpNo, Itm);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);

            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String RcpNo = cmd.entry.getString("RcpNo");
        String Itm = cmd.entry.getString("Itm");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        BigDecimal Qty = cmd.entry.getBigDecimal("Qty");

        try {
            cmd.db.begin();
            RCPD.update(this, Cmp, RcpNo, Itm, Price, Qty);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
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
        String Itm = cmd.entry.getString("Itm");
        try {
            cmd.db.begin();
            RCPD.delete(this, Cmp, RcpNo, Itm);
            RCPE.delete(this, Cmp, RcpNo, Itm);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String RcpNo = cmd.in.map.texts.get(MAP.BSRCP.RCPNO);
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);

        String rsName = "BSJS0RCPD.load";
        RCPD.select(this, rsName, cmp, RcpNo, Itm);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("RcpNo", RcpNo);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));

        cmd.local.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
        cmd.local.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.form.broadcastSignal(RcpSignal.REFRESH);
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
                String title = "BSJS0RCPD.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSJS0RCPD.error";
                cmd.log.severe(title, ex);
                return false;
            }
        }
        return super.verify(fieldName);
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        String RcpNo = cmd.in.map.texts.get(MAP.BSRCP.RCPNO);
        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("RcpNo", RcpNo);
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

        super.addTextField("RcpNo", "BSRCP.RcpNo");
        super.setFieldHelpMessage("BSRCP.RcpNo.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSJL0RCP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("Itm", "BSSI.Itm");
        super.setFieldHelpMessage("BSSI.Itm.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSDL1SI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSJS0RCPD.tab.general");

        super.addNumericField("Price", "BSRCPD.Price");
        super.setFieldHelpMessage("BSRCPD.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSRCPD.Qty");
        super.setFieldHelpMessage("BSRCPD.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addTab("details", "BSJS0RCPD.tab.details", BSJL0RCPE.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSJS0RCPD.title";
    }
}
