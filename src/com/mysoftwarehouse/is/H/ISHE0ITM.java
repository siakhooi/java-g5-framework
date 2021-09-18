/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.H;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.C.ISCL1ITM;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISHE0ITM extends EntryForm {

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
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addNumericField("Qty", "ISITM.Qty");
        super.setFieldHelpMessage("ISITM.Qty.help");
        //super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("SsQty", "ISITM.SsQty");
        super.setFieldHelpMessage("ISITM.SsQty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "ISHE0ITM.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "ISHE0ITM.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String Whs = GET.Whs(this);
                String Itm = cmd.entry.getString("Itm");
                BigDecimal SsQty = cmd.entry.getBigDecimal("SsQty");
                if (SsQty != null) {

                    cmd.db.begin();
                    ITM.updateSsQty(this, Whs, Itm, SsQty);
                    cmd.db.commit();
                }
            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
                cmd.log.severe("ISHE0ITM.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("ISHE0ITM.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            String rsName = "ISHE0ITM";
            String whs = GET.Whs(this);
            String itm = cmd.in.stringValue;
            cmd.entry.setValue("Whs", whs);
            cmd.entry.setValue("Itm", itm);
            BigDecimal SsQty = BigDecimal.ZERO;
            BigDecimal Qty = BigDecimal.ZERO;
            if (ITM.select(this, rsName, whs, itm)) {
                Qty = cmd.db.getBigDecimal(rsName, "Qty");
                SsQty = cmd.db.getBigDecimal(rsName, "SsQty");
            }
            cmd.entry.setValue("Qty", Qty);
            cmd.entry.setValue("SsQty", SsQty);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISHE0ITM.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("ISHE0ITM.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "ISHE0ITM.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
