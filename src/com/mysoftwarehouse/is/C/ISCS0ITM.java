/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.D.ISDL1UOM;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.UOM.UOM;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCS0ITM extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("locations", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("locations", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Itm = cmd.entry.getString("Itm");
        String Nme = cmd.entry.getString("Nme");
        String Uom = cmd.entry.getString("Uom");
        BigDecimal SsQty = cmd.entry.getBigDecimal("SsQty");
        BigDecimal StdCst = cmd.entry.getBigDecimal("StdCst");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            ITM.insert(this, Whs, Itm, Nme, Uom, SsQty, StdCst, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Whs = cmd.entry.getString("Whs");
        String Itm = cmd.entry.getString("Itm");
        String Nme = cmd.entry.getString("Nme");
        String Uom = cmd.entry.getString("Uom");
        BigDecimal SsQty = cmd.entry.getBigDecimal("SsQty");
        BigDecimal StdCst = cmd.entry.getBigDecimal("StdCst");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            ITM.update(this, Whs, Itm, Nme, Uom, SsQty, StdCst, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String itm = cmd.entry.getString("Itm");
        cmd.out.map.texts.put(MAP.ISITM.ITM, itm);
        UserFormInterface f = cmd.form.create(ISCP0ITM.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String Whs = GET.Whs(this);
        String Itm = cmd.in.map.texts.get(MAP.ISITM.ITM);
        String rsName = "ISCS0ITM";
        ITM.select(this, rsName, Whs, Itm);

        cmd.entry.setValue("Whs", Whs);
        cmd.entry.setValue("Itm", Itm);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Uom", cmd.db.getString(rsName, "Uom"));
        cmd.entry.setValue("Qty", cmd.db.getBigDecimal(rsName, "Qty"));
        cmd.entry.setValue("SsQty", cmd.db.getBigDecimal(rsName, "SsQty"));
        cmd.entry.setValue("StdCst", cmd.db.getBigDecimal(rsName, "StdCst"));
        cmd.entry.setValue("WacCst", cmd.db.getBigDecimal(rsName, "WacCst"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
        String whs = GET.Whs(this);
        cmd.entry.setValue("Whs", whs);
        cmd.entry.setValue("Qty", BigDecimal.ZERO);
        cmd.entry.setValue("SsQty", BigDecimal.ZERO);
        cmd.entry.setValue("StdCst", BigDecimal.ZERO);
        cmd.entry.setValue("WacCst", BigDecimal.ZERO);
    }

    @Override
    public boolean verify(String fieldName) {
        String whs = GET.Whs(this);
        try {
            if (fieldName.equals("Itm")) {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Itm = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Itm)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                boolean i = ITM.hasItm(this, whs, Itm);
                if (i) {
                    String title = "ITM.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            }
            if (fieldName.equals("Uom")) {

                String Uom = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Uom)) {
                    return true;
                }

                boolean i = UOM.hasUom(this, whs, Uom);
                if (!i) {
                    String title = "UOM.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            }
        } catch (SQLException ex) {
            String title = "ISCS0ITM.error";
            cmd.log.severe(title, ex);
            return false;
        } catch (EntryFieldWrongDataTypeException ex) {
            String title = "ISCS0ITM.error";
            cmd.log.sysSevere(title, ex);
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Whs", "ISWHS.Whs");
        super.setFieldHelpMessage("ISWHS.Whs.help");
        super.setFieldSelectForm(ISBL1WHS.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Itm", "ISITM.Itm");
        super.setFieldHelpMessage("ISITM.Itm.help");
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "ISCS0ITM.tab.general");
        super.addTextField("Nme", "ISITM.Nme");
        super.setFieldHelpMessage("ISITM.Nme.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("Uom", "ISUOM.Uom");
        super.setFieldHelpMessage("ISUOM.Uom.help");
        super.setFieldMandatory(true);
        super.setFieldSelectForm(ISDL1UOM.class);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addNumericField("Qty", "ISITM.Qty");
        super.setFieldHelpMessage("ISITM.Qty.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("SsQty", "ISITM.SsQty");
        super.setFieldHelpMessage("ISITM.SsQty.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("StdCst", "ISITM.StdCst");
        super.setFieldHelpMessage("ISITM.StdCst.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("WacCst", "ISITM.WacCst");
        super.setFieldHelpMessage("ISITM.WacCst.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addTextField("Remark", "ISITM.Remark");
        super.setFieldHelpMessage("ISITM.Remark.help");
        super.setFieldTextColumn(80);
        super.setFieldMaximumLength(200);

        super.addTab("locations", "ISCS0ITM.tab.locations", ISCL0ITMB.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "ISCS0ITM.title";
    }
}
