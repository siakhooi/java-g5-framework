/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SiEnum;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIT0INVE extends TextForm {

    private String Cmp,  InvNo,  Itm;
    private String rsName = "BSIT0INVE";

    @Override
    protected void init() {
        try {
            super.setColumns(SiEnum.SID_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            InvNo = cmd.in.map.texts.get(MAP.BSINV.INVNO);
            Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            INVE.selectAll(this, rsName, Cmp, InvNo, Itm);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSIT0INVE.error", ex);
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
                    SiEnum.SID_LENGTH, true);

            cmd.db.begin();
            INVE.writeAll(this, Cmp, InvNo, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSIT0INVE.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSIT0INVE.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSIT0INVE.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSIT0INVE.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSIT0INVE.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSIT0INVE.button.cancel",
                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name) {
        if ("ok".equals(name)) {
            save();
            cmd.form.closeForm();
        } else if ("cancel".equals(name)) {
            cmd.form.closeForm();
        }
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
