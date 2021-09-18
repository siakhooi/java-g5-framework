/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.KM.Q.BSQL1PSI;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PsiEnum;
import com.mysoftwarehouse.bs.db.POR.PORS;
import com.mysoftwarehouse.bs.db.PSI.PSID;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNT0PORS extends TextForm {

    String Cmp, PorNo;
    String rsName = "BSNT0PORS";

    @Override
    protected void init() {
        try {
            super.setColumns(PsiEnum.PSID_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            PorNo = cmd.in.map.texts.get(MAP.BSPOR.PORNO);
            PORS.selectAll(this, rsName, Cmp, PorNo);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSNT0PORS.error", ex);
        }
    }

    private void load(String rsName) throws SQLException {
        cmd.linewrite.init();
        while (cmd.db.next(rsName)) {
            cmd.linewrite.println(cmd.db.getString(rsName, "Text"));
        }
        super.setText(cmd.linewrite.getString());
    }

    private void save() {
        try {
            Vector<String> allText = TextArea2Vector.convert(textArea,
                    PsiEnum.PSID_LENGTH, true);
            cmd.db.begin();
            PORS.writeAll(this, Cmp, PorNo, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSNT0PORS.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSNT0PORS.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSNT0PORS.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSNT0PORS.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSNT0PORS.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSNT0PORS.button.cancel",
                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
        super.buttons.addButton("load", "BSNT0PORS.button.load",
                cmd.icon.getSearchIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name) {
        if ("ok".equals(name)) {
            save();
            cmd.form.closeForm();
        } else if ("cancel".equals(name)) {
            cmd.form.closeForm();
        } else if ("load".equals(name)) {
            try {
                BSQL1PSI f1 = new BSQL1PSI();
                cmd.form.execute(f1);
                String SpcInst = cmd.out.stringValue;
                if (cmd.data.isNull(SpcInst)) {
                    return;
                }
                PSID.selectAll(this, rsName, Cmp, SpcInst);
                cmd.db.beforeFirst(rsName);
                load(rsName);
            } catch (SQLException ex) {
                cmd.log.severe("BSNT0PORS.error", ex);
            }
        }
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
