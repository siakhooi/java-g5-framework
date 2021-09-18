/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.APP;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import com.mysoftwarehouse.bs.db.CMP.CMP;
import com.mysoftwarehouse.bs.db.CMP.CMPA;
import com.mysoftwarehouse.bs.db.CMP.CMPC;
import com.mysoftwarehouse.bs.db.CMP.CMPS;
import com.mysoftwarehouse.bs.db.CMP.CMP_Default;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBS0CMP extends ModeEntryForm {

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
        String Nme = cmd.entry.getString("Nme");
        String RegNo = cmd.entry.getString("RegNo");
        String WebSte = cmd.entry.getString("WebSte");
        String CurCde = cmd.entry.getString("CurCde");

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
            int seqAddr = APP.BSCMP_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSCMP_DEFAULT_CNT_SEQ;
            CMP.insert(this, Cmp, Nme, RegNo, seqAddr, seqCnt, WebSte, CurCde);
            CMPA.insert(this, Cmp, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AStte,
                    ACtry, ATel, AFax, ARemark);
            CMPC.insert(this, Cmp, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);
            CFG.insert(this, Cmp);
            CMPS.insert(this, Cmp);
            CMP_Default.createPY(this, Cmp);
            CMP_Default.createSA(this, Cmp);
            CMP_Default.createST(this, Cmp);
            CMP_Default.createSY(this, Cmp);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveEdit() throws Exception {
        String Cmp = cmd.entry.getString("Cmp");
        String Nme = cmd.entry.getString("Nme");
        String RegNo = cmd.entry.getString("RegNo");
        String WebSte = cmd.entry.getString("WebSte");
        String CurCde = cmd.entry.getString("CurCde");

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
            int seqAddr = APP.BSCMP_DEFAULT_ADDR_SEQ;
            int seqCnt = APP.BSCMP_DEFAULT_CNT_SEQ;
            CMP.update(this, Cmp, Nme, RegNo, seqAddr, seqCnt, WebSte, CurCde);
            CMPA.update(this, Cmp, seqAddr, AAdd1, AAdd2, ACity, AZipCde, AState,
                    ACtry, ATel, AFax, ARemark);
            CMPC.update(this, Cmp, seqCnt, CFstNme, CLstNme, CTitle, CTel, CFax,
                    CMobile, CEmail, CRemark);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.db.rollback();
            throw ex;
        }
    }

    @Override
    public void saveDelete() throws Exception {
        String cmp = cmd.entry.getString("Cmp");
        cmd.out.map.texts.put(MAP.BSCMP.CMP, cmp);
        UserFormInterface f = cmd.form.create(BSBP2CMP.class);
        cmd.form.execute(f);
        cmd.form.closeForm();
    }

    @Override
    public void loadData() throws Exception {
        String cmp = cmd.in.map.texts.get(MAP.BSCMP.CMP);

        String rsName = "BSBS0CMP";
        CMP.select(this, rsName, cmp);
        String rsName2 = "BSBS0CMP2";
        CMPA.select(this, rsName2, cmp);
        String rsName3 = "BSBS0CMP3";
        CMPC.select(this, rsName3, cmp);

        cmd.entry.setValue("Cmp", cmp);
        cmd.entry.setValue("Nme", cmd.db.getString(rsName, "Nme"));
        cmd.entry.setValue("RegNo", cmd.db.getString(rsName, "RegNo"));
        cmd.entry.setValue("WebSte", cmd.db.getString(rsName, "WebSte"));
        cmd.entry.setValue("CurCde", cmd.db.getString(rsName, "CurCde"));

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
    }

    @Override
    public boolean verify(String fieldName) {
        if (fieldName.equals("Cmp")) {
            try {
                boolean addMode = super.currentMode == super.currentMode.ADD;
                String Cmp = cmd.entry.getString(fieldName);
                if (cmd.data.isNull(Cmp)) {
                    return true;
                }
                if (!addMode) {
                    return true;
                }
                boolean i = CMP.hasCMP(this, Cmp);
                if (i) {
                    String title = "CMP.error.Exist";
                    cmd.entry.setStatus(StatusType.ERROR, title);
                }
                return !i;
            } catch (SQLException ex) {
                String title = "BSBS0CMP.error";
                cmd.log.severe(title, ex);
                return false;
            } catch (EntryFieldWrongDataTypeException ex) {
                String title = "BSBS0CMP.error";
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
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("general", "BSBS0CMP.tab.general");
        super.addTextField("Nme", "BSCMP.Nme");
        super.setFieldHelpMessage("BSCMP.Nme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("RegNo", "BSCMP.RegNo");
        super.setFieldHelpMessage("BSCMP.RegNo.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("WebSte", "BSCMP.WebSte");
        super.setFieldHelpMessage("BSCMP.WebSte.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CurCde", "BSCMP.CurCde");
        super.setFieldHelpMessage("BSCMP.CurCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTab("address", "BSBS0CMP.tab.address");
        super.addTextField("AAdd1", "BSCMPA.Add1");
        super.setFieldHelpMessage("BSCMPA.Add1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("AAdd2", "BSCMPA.Add2");
        super.setFieldHelpMessage("BSCMPA.Add2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("ACity", "BSCMPA.City");
        super.setFieldHelpMessage("BSCMPA.City.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("AZipCde", "BSCMPA.ZipCde");
        super.setFieldHelpMessage("BSCMPA.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("AStte", "BSCMPA.Stte");
        super.setFieldHelpMessage("BSCMPA.Stte.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ACtry", "BSCMPA.Ctry");
        super.setFieldHelpMessage("BSCMPA.Ctry.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("ATel", "BSCMPA.Tel");
        super.setFieldHelpMessage("BSCMPA.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("AFax", "BSCMPA.Fax");
        super.setFieldHelpMessage("BSCMPA.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("ARemark", "BSCMPA.Remark");
        super.setFieldHelpMessage("BSCMPA.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);

        super.addTab("contact", "BSBS0CMP.tab.contact");
        super.addTextField("CFstNme", "BSCMPC.FstNme");
        super.setFieldHelpMessage("BSCMPC.FstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CLstNme", "BSCMPC.LstNme");
        super.setFieldHelpMessage("BSCMPC.LstNme.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CTitle", "BSCMPC.Title");
        super.setFieldHelpMessage("BSCMPC.Title.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("CMobile", "BSCMPC.Mobile");
        super.setFieldHelpMessage("BSCMPC.Mobile.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CEmail", "BSCMPC.Email");
        super.setFieldHelpMessage("BSCMPC.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("CTel", "BSCMPC.Tel");
        super.setFieldHelpMessage("BSCMPC.Tel.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CFax", "BSCMPC.Fax");
        super.setFieldHelpMessage("BSCMPC.Fax.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("CRemark", "BSCMPC.Remark");
        super.setFieldHelpMessage("BSCMPC.Remark.help");
        super.setFieldMaximumLength(200);
        super.setFieldTextColumn(80);
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "BSBS0CMP.title";
    }
}
