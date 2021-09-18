/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.KM.K.BSKL1SUP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PipEnum;
import com.mysoftwarehouse.bs.db.PI.PIP;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLS0PIP extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Itm = cmd.entry.getString("Itm");
        String Sup = cmd.entry.getString("Sup");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            PIP.insert(this, Cmp, Itm, Sup, Price, Status, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Itm = cmd.entry.getString("Itm");
        String Sup = cmd.entry.getString("Sup");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        try {
            cmd.db.begin();
            PIP.update(this, Cmp, Itm, Sup, Price, Status, Remark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String cmp = cmd.entry.getString("Cmp");
        String itm = cmd.entry.getString("Itm");
        String sup = cmd.entry.getString("Sup");
        cmd.db.begin();
        PIP.delete(this, cmp, itm, sup);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        String sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);

        String rsName = "BSLS0PIP";
        PIP.select(this, rsName, cmp, itm, sup);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Itm", itm);
        cmd.entry.setValue("Sup", sup);
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
        String itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        cmd.entry.setValue("Itm", itm);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Sup")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Sup = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Sup)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String Cmp = GET.Cmp(this);
                String Itm=cmd.entry.getString("Itm");
                boolean i = PIP.hasItm(this, Cmp, Itm, Sup);
                if (i) {
                    String title = "PIP.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                    return false;
                }
                i = SUP.hasSup(this, Cmp, Sup);
                if (!i) {
                    String title = "SUP.error.NotExist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                    return false;
                }
            } catch (SQLException ex) {
                String title = "BSLS0PIP.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSLS0PIP.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldEditable(false);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Itm", "BSPIP.Itm");
        super.setFieldHelpMessage("BSPIP.Itm.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldEditable(false);
        super.setFieldSelectForm(BSLL1PI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSLS0PIP.tab.general");

        super.addNumericField("Price", "BSPIP.Price");
        super.setFieldHelpMessage("BSPIP.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addRadioboxField("Status", "BSPIP.Status");
        super.setFieldHelpMessage("BSPIP.Status.help");
        for (PipEnum.Status i : PipEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSPIP.Remark");
        super.setFieldHelpMessage("BSPIP.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSLS0PIP.title";
    }
}
