/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A.out;

import com.mysoftwarehouse.bs.A.*;
import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAE0REG extends EntryForm {

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return BSAH0REG.class;
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("DongleId", "BSAE0REG.DongleId");
        super.setFieldHelpMessage("BSAE0REG.DongleId.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTab("general", "BSAE0REG.tab.general");
        super.addTextField("CompanyName", "BSAE0REG.CompanyName");
        super.setFieldHelpMessage("BSAE0REG.CompanyName.help");
        super.setFieldMaximumLength(30);
        super.setFieldTextColumn(30);

        super.addTextField("RegNo", "BSAE0REG.RegNo");
        super.setFieldHelpMessage("BSAE0REG.RegNo.help");
        super.setFieldMaximumLength(15);
        super.setFieldTextColumn(15);

        super.addTextField("Add1", "BSAE0REG.Add1");
        super.setFieldHelpMessage("BSAE0REG.Add1.help");
        super.setFieldMaximumLength(80);
        super.setFieldTextColumn(80);

        super.addTextField("Add2", "BSAE0REG.Add2");
        super.setFieldHelpMessage("BSAE0REG.Add2.help");
        super.setFieldMaximumLength(80);
        super.setFieldTextColumn(80);

        super.addTextField("City", "BSAE0REG.City");
        super.setFieldHelpMessage("BSAE0REG.City.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("ZipCde", "BSAE0REG.ZipCde");
        super.setFieldHelpMessage("BSAE0REG.ZipCde.help");
        super.setFieldMaximumLength(5);
        super.setFieldTextColumn(5);

        super.addTextField("State", "BSAE0REG.State");
        super.setFieldHelpMessage("BSAE0REG.State.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("Ctry", "BSAE0REG.Ctry");
        super.setFieldHelpMessage("BSAE0REG.Ctry.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("WebSte", "BSAE0REG.WebSte");
        super.setFieldHelpMessage("BSAE0REG.WebSte.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);

        super.addTextField("Email", "BSAE0REG.Email");
        super.setFieldHelpMessage("BSAE0REG.Email.help");
        super.setFieldMaximumLength(50);
        super.setFieldTextColumn(50);
    }

    @Override
    protected void buildButtonsList() {
        super.buttons.addButton("submit", "BSAE0REG.button.submit");
    }

    @Override
    public void initValue() {
    }

    @Override
    public void buttonClick(String name) {
        if (name.equals("submit")) {
            try {
                //submit dongle?
                String CmpName = cmd.entry.getString("CmpName");
                String RegNo = cmd.entry.getString("RegNo");
                String WebSte = cmd.entry.getString("WebSte");
                String Addr1 = cmd.entry.getString("Addr1");
                String Addr2 = cmd.entry.getString("Addr2");
                String City = cmd.entry.getString("City");
                String ZipCde = cmd.entry.getString("ZipCde");
                String State = cmd.entry.getString("State");
                String Ctry = cmd.entry.getString("Ctry");
                String Email = cmd.entry.getString("Email");

                String DongleId = "";
                //cmd.out.map.texts.put(MAP.REG.DONGLEID, DongleId);
                //...
                cmd.form.execute(cmd.form.create(BSAP1REG.class));
            } catch (EntryFieldWrongDataTypeException ex) {
                
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSAE0REG.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
