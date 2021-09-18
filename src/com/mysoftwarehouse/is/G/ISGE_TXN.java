/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.G;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.mysoftwarehouse.is.B.ISBL1WHS;
import com.mysoftwarehouse.is.C.ISCL1ITM;
import com.mysoftwarehouse.is.E.ISEL1LOC;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.data.TcdEnum;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.LOC.LOC;
import com.mysoftwarehouse.is.db.TCD.TCD;
import com.mysoftwarehouse.is.db.TXN.TXN_C;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ISGE_TXN extends ModeEntryForm {

    protected abstract TcdEnum.TxnTyp getTxnTyp();

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

        super.addTextField("Tcd", "ISTCD.Tcd");
        super.setFieldHelpMessage("ISTCD.Tcd.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(getTxnTyp().lookupForm);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Itm", "ISITM.Itm");
        super.setFieldHelpMessage("ISITM.Itm.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("FrmLoc", "ISTXN.FrmLoc");
        super.setFieldHelpMessage("ISTXN.FrmLoc.help");
        //super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToLoc", "ISTXN.ToLoc");
        super.setFieldHelpMessage("ISTXN.ToLoc.help");
        //super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addNumericField("TxnQty", "ISTXN.TxnQty");
        super.setFieldHelpMessage("ISTXN.TxnQty.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("TxnCst", "ISTXN.TxnCst");
        super.setFieldHelpMessage("ISTXN.TxnCst.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.0000");

        super.addTextField("Remark", "ISTXN.Remark");
        super.setFieldHelpMessage("ISTXN.Remark.help");
        super.setFieldTextColumn(80);
        super.setFieldMaximumLength(200);

        super.enteringAction = ModeAction.AddMode;
    }

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        super.buttonConfig.ok = ModeAction.SaveAddAndClose;
    }

    @Override
    public void saveAdd() throws Exception {
        String Whs = GET.Whs(this);
        String Tcd = cmd.entry.getString("Tcd");
        String Itm = cmd.entry.getString("Itm");
        String FrmLoc = cmd.entry.getString("FrmLoc");
        String ToLoc = cmd.entry.getString("ToLoc");
        BigDecimal TxnQty = cmd.entry.getBigDecimal("TxnQty");
        BigDecimal TxnCst = cmd.entry.getBigDecimal("TxnCst");
        String Remark = cmd.entry.getString("Remark");

        cmd.db.begin();
        TXN_C.create(this, getTxnTyp().typ,
                Whs, Tcd, Itm, FrmLoc, ToLoc, TxnQty, TxnCst, Remark);
        cmd.db.commit();
    }

    @Override
    public void saveEdit() throws Exception {
    }

    @Override
    public void saveDelete() throws Exception {
    }

    @Override
    public void loadData() throws Exception {
    }

    protected abstract void initValue2() throws EntryFieldWrongDataTypeException;

    @Override
    public void loadDefault() throws Exception {
        String Whs = GET.Whs(this);
        try {
            cmd.entry.setValue("Whs", Whs);
            cmd.entry.setValue("TxnQty", 0);
            cmd.entry.setValue("TxnCst", 0);
            initValue2();
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISGE_TXN.error", ex);
        }
    }

//    @Override
//    protected void buildButtonsList() {
//        String name = "", label = "";
//        name = "OK";
//        label = "ISGE_TXN.button.ok";
//        label = cmd.lang.getString(label);
//        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
//        super.buttons.addI18nButton(name, label, icon, true);
//        name = "CLOSE";
//        label = "ISGE_TXN.button.close";
//        label = cmd.lang.getString(label);
//        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
//        super.buttons.addI18nButton(name, label, icon, false);
//    }
    @Override
    public boolean verify(String fieldName) {
        String Whs = GET.Whs(this);
        try {
            String fieldValue = cmd.entry.getString(fieldName);
            if (cmd.data.isNull(fieldValue)) {
                return true;
            }
            if (fieldName.equals("Tcd")) {
                boolean i = TCD.hasTcd(this, Whs, fieldValue, getTxnTyp().typ);
                if (!i) {
                    String title = "TCD.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            }
            if (fieldName.equals("Itm")) {
                boolean i = ITM.hasItm(this, Whs, fieldValue);
                if (!i) {
                    String title = "ITM.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            }
            if (fieldName.equals("FrmLoc")) {
                boolean i = LOC.hasLoc(this, Whs, fieldValue);
                if (!i) {
                    String title = "LOC.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return i;
            }
            if (fieldName.equals("ToLoc")) {
                boolean i = LOC.hasLoc(this, Whs, fieldValue);
                if (!i) {
                    String title = "LOC.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                    return i;
                }
                if (getTxnTyp() == getTxnTyp().X) {
                    String f = cmd.entry.getString("FrmLoc");
                    if (f.equals(fieldValue)) {
                        String title = "LOC.error.SameWithFrmLoc";
                        cmd.entry.setStatus(StatusType.ERROR, title);
                        return false;
                    }
                }
                return i;
            }
            if (fieldName.equals("TxnQty")) {
                BigDecimal qty = cmd.entry.getBigDecimal(fieldName);
                boolean i = qty.compareTo(BigDecimal.ZERO) == 0;
                if (i) {
                    String title = "TxnQty.error.Zero";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;

            }
        } catch (SQLException ex) {
            String title = "ISGE_TXN.error";
            cmd.log.severe(title, ex);
            return false;
        } catch (EntryFieldWrongDataTypeException ex) {
            String title = "ISGE_TXN.error";
            cmd.log.sysSevere(title, ex);
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildButtonsList() {
    }

//    @Override
//    public void buttonClick(String name) {
//        if ("OK".equals(name)) {
//            try {
//                String Whs = GET.Whs(this);
//                String Tcd = cmd.entry.getString("Tcd");
//                String Itm = cmd.entry.getString("Itm");
//                String FrmLoc = cmd.entry.getString("FrmLoc");
//                String ToLoc = cmd.entry.getString("ToLoc");
//                BigDecimal TxnQty = cmd.entry.getBigDecimal("TxnQty");
//                BigDecimal TxnCst = cmd.entry.getBigDecimal("TxnCst");
//                String Remark = cmd.entry.getString("Remark");
//
//                cmd.db.begin();
//                TXN_C.create(this, getTxnTyp().typ,
//                        Whs, Tcd, Itm, FrmLoc, ToLoc, TxnQty, TxnCst, Remark);
//                cmd.db.commit();
//            } catch (SQLException ex) {
//                try {
//                    cmd.db.rollback();
//                } catch (SQLException ex2) {
//                }
//                cmd.log.severe("ISGE_TXN.error", ex);
//            } catch (EntryFieldWrongDataTypeException ex) {
//                cmd.log.sysSevere("ISGE_TXN.error", ex);
//            }
//        }
//        cmd.form.closeForm();
//    }
}
