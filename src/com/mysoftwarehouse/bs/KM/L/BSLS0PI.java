/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PiEnum;
import com.mysoftwarehouse.bs.db.PI.PI;
import com.mysoftwarehouse.bs.db.PI.PID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLS0PI extends ModeEntryForm {

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
    }

    @Override
    public void finishModeAction() {
        if (super.currentMode == super.currentMode.ADD) {
            cmd.entry.setTabEnabled("details", false);
            cmd.entry.setTabEnabled("price", false);
            cmd.entry.showTab("general");
        } else {
            cmd.entry.setTabEnabled("details", true);
            cmd.entry.setTabEnabled("price", true);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Itm = cmd.entry.getString("Itm");
        String Nme = cmd.entry.getString("Nme");
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            PI.insert(this, Cmp, Itm, Nme, Status);
            if (!cmd.data.isNull(CopyItm)) {
                PID.copyFrmPi(this, Cmp, CopyItm, Itm);
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
        String Status = cmd.entry.getString("Status");

        try {
            cmd.db.begin();
            PI.update(this, Cmp, Itm, Nme, Status);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String itm = cmd.entry.getString("Itm");
        cmd.out.map.texts.put(MAP.BSPI.ITM, itm);
        UserFormInterface f = cmd.form.create(BSLP0PI.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }
    String CopyItm = "";

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        CopyItm = "";
        if (super.nextModeType == super.nextModeType.AddMode) {
            CopyItm = itm;
        }

        String rsName = "BSLS0PI";
        PI.select(this, rsName, cmp, itm);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Itm", itm);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
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
                boolean i = PI.hasItm(this, Cmp, Itm);
                if (i) {
                    String title = "PI.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "BSLS0PI.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSLS0PI.error";
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
        super.setFieldEditable(false);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("Itm", "BSPI.Itm");
        super.setFieldHelpMessage("BSPI.Itm.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSLL1PI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSLS0PI.tab.general");
        super.addTextField("Nme", "BSPI.Nme");
        super.setFieldHelpMessage("BSPI.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Status", "BSPI.Status");
        super.setFieldHelpMessage("BSPI.Status.help");
        for (PiEnum.Status i : PiEnum.Status.values()) {
            super.addOption(i.typ, i.name);
        }
        super.addTab("details", "BSDS0SI.tab.details", BSLL0PID.class);
        super.addTab("price", "BSLS0PI.tab.price", BSLL0PIP.class);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSLS0PI.title";
    }
}
