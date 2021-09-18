/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SiEnum;
import com.mysoftwarehouse.bs.db.SI.SI;
import com.mysoftwarehouse.bs.db.SI.SID;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDS0SI extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("details", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("details", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Itm = cmd.entry.getString("Itm");
        String Nme = cmd.entry.getString("Nme");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            SI.insert(this, Cmp, Itm, Nme, Price, Status);
            if (!cmd.data.isNull(CopyItm)) {
                SID.copyFrmSi(this, Cmp, CopyItm, Itm);
            }
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
        String Nme = cmd.entry.getString("Nme");
        BigDecimal Price = cmd.entry.getBigDecimal("Price");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            SI.update(this, Cmp, Itm, Nme, Price, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String itm = cmd.entry.getString("Itm");
        cmd.out.map.texts.put(MAP.BSSI.ITM, itm);
        UserFormInterface f = cmd.form.create(BSDP0SI.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }
    String CopyItm = "";

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
        CopyItm = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyItm = itm;
        }
        String rsName = "BSDS0SI";
        SI.select(this, rsName, cmp, itm);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Itm", itm);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Price", cmd.db.getBigDecimal(rsName, "Price"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
    }

    @Override
    public void loadDefault() throws Exception {
        CopyItm = "";
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Itm")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Itm = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Itm)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String Cmp = GET.Cmp(this);
                boolean i = SI.hasItm(this, Cmp, Itm);
                if (i) {
                    String title = "SI.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "BSDS0SI.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSDS0SI.error";
                cmd.log.sysSevere(title, ex);
            }
        }
        return super.verify(fieldName);
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

        super.addTextField("Itm", "BSSI.Itm");
        super.setFieldHelpMessage("BSSI.Itm.help");
        super.setFieldSelectForm(BSDL1SI.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSDS0SI.tab.general");
        super.addTextField("Nme", "BSSI.Nme");
        super.setFieldHelpMessage("BSSI.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addNumericField("Price", "BSSI.Price");
        super.setFieldHelpMessage("BSSI.Price.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(20);
        super.setFieldOutputFormat("#0.00");

        super.addRadioboxField("Status", "BSSI.Status");
        super.setFieldHelpMessage("BSSI.Status.help");
        for (SiEnum.Status i : SiEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTab("details", "BSDS0SI.tab.details", BSDL0SID.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSDS0SI.title";
    }
}
