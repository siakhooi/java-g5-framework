/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.K;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.APP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SupEnum;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import com.mysoftwarehouse.bs.db.SUP.SUPA;
import com.mysoftwarehouse.bs.db.SUP.SUPC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSKS0SUP extends ModeEntryForm {

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
        String Sup = cmd.entry.getString("Sup");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        String RegNo = cmd.entry.getString("RegNo");
        String WebSte = cmd.entry.getString("WebSte");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        String AAdd1 = cmd.entry.getString("AAdd1");
        String AAdd2 = cmd.entry.getString("AAdd2");
        String ACity = cmd.entry.getString("ACity");
        String AZipCde = cmd.entry.getString("AZipCde");
        String AStte = cmd.entry.getString("AStte");
        String ACtry = cmd.entry.getString("ACtry");
        String ATel = cmd.entry.getString("ATel");
        String AFax = cmd.entry.getString("AFax");
        String ARemark = cmd.entry.getString("ARemark");

        String CFstNme = cmd.entry.getString("CFstNme");
        String CLstNme = cmd.entry.getString("CLstNme");
        String CTitle = cmd.entry.getString("CTitle");
        String CTel = cmd.entry.getString("CTel");
        String CFax = cmd.entry.getString("CFax");
        String CMobile = cmd.entry.getString("CMobile");
        String CEmail = cmd.entry.getString("CEmail");
        String CRemark = cmd.entry.getString("CRemark");
        try {
            cmd.db.begin();
            int seqAddr = APP.BSSUP_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSSUP_DEFAULT_CNT_SEQ;
            SUP.insert(this, Cmp, Sup, Nme, Typ, RegNo, seqAddr, seqCnt, WebSte, Status, Remark);
            SUPA.insert(this, Cmp, Sup, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            SUPC.insert(this, Cmp, Sup, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Sup = cmd.entry.getString("Sup");
        String Nme = cmd.entry.getString("Nme");
        String Typ = cmd.entry.getString("Typ");
        String RegNo = cmd.entry.getString("RegNo");
        String WebSte = cmd.entry.getString("WebSte");
        String Status = cmd.entry.getString("Status");
        String Remark = cmd.entry.getString("Remark");

        String AAdd1 = cmd.entry.getString("AAdd1");
        String AAdd2 = cmd.entry.getString("AAdd2");
        String ACity = cmd.entry.getString("ACity");
        String AZipCde = cmd.entry.getString("AZipCde");
        String AState = cmd.entry.getString("AStte");
        String ACtry = cmd.entry.getString("ACtry");
        String ATel = cmd.entry.getString("ATel");
        String AFax = cmd.entry.getString("AFax");
        String ARemark = cmd.entry.getString("ARemark");

        String CFstNme = cmd.entry.getString("CFstNme");
        String CLstNme = cmd.entry.getString("CLstNme");
        String CTitle = cmd.entry.getString("CTitle");
        String CTel = cmd.entry.getString("CTel");
        String CFax = cmd.entry.getString("CFax");
        String CMobile = cmd.entry.getString("CMobile");
        String CEmail = cmd.entry.getString("CEmail");
        String CRemark = cmd.entry.getString("CRemark");
        try {
            cmd.db.begin();
            int seqAddr = APP.BSSUP_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSSUP_DEFAULT_CNT_SEQ;
            SUP.update(this, Cmp, Sup, Nme, Typ, RegNo, seqAddr, seqCnt, WebSte, Status, Remark);
            SUPA.update(this, Cmp, Sup, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AState,
                    ACtry, ATel, AFax, ARemark);
            SUPC.update(this, Cmp, Sup, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String sup = cmd.entry.getString("Sup");
        cmd.out.map.texts.put(MAP.BSSUP.SUP, sup);
        UserFormInterface f = cmd.form.create(BSKP0SUP.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);

        String rsName = "BSKS0SUP";
        SUP.select(this, rsName, cmp, sup);
        String rsName2 = "BSKS0SUP2";
        SUPA.select(this, rsName2, cmp, sup);
        String rsName3 = "BSKS0SUP3";
        SUPC.select(this, rsName3, cmp, sup);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Sup", sup);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("Typ", cmd.db.getString(rsName, "Typ"));
        cmd.entry.setValue("RegNo", cmd.db.getString(rsName, "RegNo"));
        cmd.entry.setValue("WebSte", cmd.db.getString(rsName, "WebSte"));
        cmd.entry.setValue("Status", cmd.db.getString(rsName, "Status"));
        cmd.entry.setValue("Remark", cmd.db.getString(rsName, "Remark"));

        cmd.entry.setValue("AAdd1", cmd.db.getString(rsName2, "Add1"));
        cmd.entry.setValue("AAdd2", cmd.db.getString(rsName2, "Add2"));
        cmd.entry.setValue("ACity", cmd.db.getString(rsName2, "City"));
        cmd.entry.setValue("AZipCde", cmd.db.getString(rsName2, "ZipCde"));
        cmd.entry.setValue("AStte", cmd.db.getString(rsName2, "Stte"));
        cmd.entry.setValue("ACtry", cmd.db.getString(rsName2, "Ctry"));
        cmd.entry.setValue("ATel", cmd.db.getString(rsName2, "Tel"));
        cmd.entry.setValue("AFax", cmd.db.getString(rsName2, "Fax"));
        cmd.entry.setValue("ARemark", cmd.db.getString(rsName2, "Remark"));

        cmd.entry.setValue("CFstNme", cmd.db.getString(rsName3, "FstNme"));
        cmd.entry.setValue("CLstNme", cmd.db.getString(rsName3, "LstNme"));
        cmd.entry.setValue("CTitle", cmd.db.getString(rsName3, "Title"));
        cmd.entry.setValue("CTel", cmd.db.getString(rsName3, "Tel"));
        cmd.entry.setValue("CFax", cmd.db.getString(rsName3, "Fax"));
        cmd.entry.setValue("CMobile", cmd.db.getString(rsName3, "Mobile"));
        cmd.entry.setValue("CEmail", cmd.db.getString(rsName3, "Email"));
        cmd.entry.setValue("CRemark", cmd.db.getString(rsName3, "Remark"));
    }

    @Override
    public void loadDefault() throws Exception {
        String cmp = GET.Cmp(this);
        cmd.entry.setValue("Cmp", cmp);
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
                boolean i = SUP.hasSup(this, Cmp, Sup);
                if (i) {
                    String title = "SUP.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "BSKS0SUP.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSKS0SUP.error";
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

        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSKS0SUP.tab.general");
        super.addTextField("Nme", "BSSUP.Nme");
        super.setFieldHelpMessage("BSSUP.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "BSSUP.Typ");
        super.setFieldHelpMessage("BSSUP.Typ.help");
        for (SupEnum.SupTyp i : SupEnum.SupTyp.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("RegNo", "BSSUP.RegNo");
        super.setFieldHelpMessage("BSSUP.RegNo.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("WebSte", "BSSUP.WebSte");
        super.setFieldHelpMessage("BSSUP.WebSte.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addRadioboxField("Status", "BSSUP.Status");
        super.setFieldHelpMessage("BSSUP.Status.help");
        for (SupEnum.SupStatus i : SupEnum.SupStatus.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSSUP.Remark");
        super.setFieldHelpMessage("BSSUP.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("address", "BSKS0SUP.tab.address");
        super.addTextField("AAdd1", "BSSUPA.Add1");
        super.setFieldHelpMessage("BSSUPA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSSUPA.Add2");
        super.setFieldHelpMessage("BSSUPA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSSUPA.City");
        super.setFieldHelpMessage("BSSUPA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSSUPA.ZipCde");
        super.setFieldHelpMessage("BSSUPA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSSUPA.Stte");
        super.setFieldHelpMessage("BSSUPA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSSUPA.Ctry");
        super.setFieldHelpMessage("BSSUPA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSSUPA.Tel");
        super.setFieldHelpMessage("BSSUPA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSSUPA.Fax");
        super.setFieldHelpMessage("BSSUPA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSSUPA.Remark");
        super.setFieldHelpMessage("BSSUPA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSKS0SUP.tab.contact");
        super.addTextField("CFstNme", "BSSUPC.FstNme");
        super.setFieldHelpMessage("BSSUPC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSSUPC.LstNme");
        super.setFieldHelpMessage("BSSUPC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSSUPC.Title");
        super.setFieldHelpMessage("BSSUPC.Title.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CMobile", "BSSUPC.Mobile");
        super.setFieldHelpMessage("BSSUPC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSSUPC.Email");
        super.setFieldHelpMessage("BSSUPC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSSUPC.Tel");
        super.setFieldHelpMessage("BSSUPC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSSUPC.Fax");
        super.setFieldHelpMessage("BSSUPC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSSUPC.Remark");
        super.setFieldHelpMessage("BSSUPC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSKS0SUP.title";
    }
}
