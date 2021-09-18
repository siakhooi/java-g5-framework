/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.B.BSBL1CMP;
import com.mysoftwarehouse.bs.conf.APP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.CusEnum;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.CUS.CUSA;
import com.mysoftwarehouse.bs.db.CUS.CUSC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCS0CUS extends ModeEntryForm {

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
        String Cus = cmd.entry.getString("Cus");
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
            int seqAddr = APP.BSCUS_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSCUS_DEFAULT_CNT_SEQ;
            CUS.insert(this, Cmp, Cus, Nme, Typ, RegNo, seqAddr, seqCnt, WebSte, Status, Remark);
            CUSA.insert(this, Cmp, Cus, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            CUSC.insert(this, Cmp, Cus, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
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
        String Cus = cmd.entry.getString("Cus");
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
            int seqAddr = APP.BSCUS_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSCUS_DEFAULT_CNT_SEQ;
            CUS.update(this, Cmp, Cus, Nme, Typ, RegNo, seqAddr, seqCnt, WebSte, Status, Remark);
            CUSA.update(this, Cmp, Cus, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            CUSC.update(this, Cmp, Cus, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String cus = cmd.entry.getString("Cus");
        cmd.out.map.texts.put(MAP.BSCUS.CUS, cus);
        UserFormInterface f = cmd.form.create(BSCP0CUS.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String cmp = GET.Cmp(this);
        String cus = cmd.in.map.texts.get(MAP.BSCUS.CUS);

        String rsName = "BSCS0CUS";
        CUS.select(this, rsName, cmp, cus);
        String rsName2 = "BSCS0CUS2";
        CUSA.select(this, rsName2, cmp, cus);
        String rsName3 = "BSCS0CUS3";
        CUSC.select(this, rsName3, cmp, cus);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Cus", cus);
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
        if (fieldName.equals("Cus")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Cus = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Cus)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                String Cmp = GET.Cmp(this);
                boolean i = CUS.hasCUS(this, Cmp, Cus);
                if (i) {
                    String title = "CUS.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "BSCS0CUS.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSCS0CUS.error";
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

        super.addTextField("Cus", "BSCUS.Cus");
        super.setFieldHelpMessage("BSCUS.Cus.help");
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(10);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSCS0CUS.tab.general");
        super.addTextField("Nme", "BSCUS.Nme");
        super.setFieldHelpMessage("BSCUS.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addRadioboxField("Typ", "BSCUS.Typ");
        super.setFieldHelpMessage("BSCUS.Typ.help");
        for (CusEnum.CusTyp i : CusEnum.CusTyp.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("RegNo", "BSCUS.RegNo");
        super.setFieldHelpMessage("BSCUS.RegNo.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("WebSte", "BSCUS.WebSte");
        super.setFieldHelpMessage("BSCUS.WebSte.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addRadioboxField("Status", "BSCUS.Status");
        super.setFieldHelpMessage("BSCUS.Status.help");
        for (CusEnum.CusStatus i : CusEnum.CusStatus.values()) {
            super.addOption(i.typ, i.name);
        }

        super.addTextField("Remark", "BSCUS.Remark");
        super.setFieldHelpMessage("BSCUS.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("address", "BSCS0CUS.tab.address");
        super.addTextField("AAdd1", "BSCUSA.Add1");
        super.setFieldHelpMessage("BSCUSA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSCUSA.Add2");
        super.setFieldHelpMessage("BSCUSA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSCUSA.City");
        super.setFieldHelpMessage("BSCUSA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSCUSA.ZipCde");
        super.setFieldHelpMessage("BSCUSA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSCUSA.Stte");
        super.setFieldHelpMessage("BSCUSA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSCUSA.Ctry");
        super.setFieldHelpMessage("BSCUSA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSCUSA.Tel");
        super.setFieldHelpMessage("BSCUSA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSCUSA.Fax");
        super.setFieldHelpMessage("BSCUSA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSCUSA.Remark");
        super.setFieldHelpMessage("BSCUSA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSCS0CUS.tab.contact");
        super.addTextField("CFstNme", "BSCUSC.FstNme");
        super.setFieldHelpMessage("BSCUSC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSCUSC.LstNme");
        super.setFieldHelpMessage("BSCUSC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSCUSC.Title");
        super.setFieldHelpMessage("BSCUSC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSCUSC.Mobile");
        super.setFieldHelpMessage("BSCUSC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSCUSC.Email");
        super.setFieldHelpMessage("BSCUSC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSCUSC.Tel");
        super.setFieldHelpMessage("BSCUSC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSCUSC.Fax");
        super.setFieldHelpMessage("BSCUSC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSCUSC.Remark");
        super.setFieldHelpMessage("BSCUSC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSCS0CUS.title";
    }
}
