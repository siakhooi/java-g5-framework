/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.CG.G.BSGL2ST;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.StEnum;
import com.mysoftwarehouse.bs.db.INV.INVR;
import com.mysoftwarehouse.bs.db.ST.STD;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIT0INVR extends TextForm {

    String Cmp, InvNo;
    String rsName = "BSIT0INVR";

    @Override
    protected void init() {
        try {
            super.setColumns(StEnum.STD_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            InvNo = cmd.in.map.texts.get(MAP.BSINV.INVNO);
            INVR.select_All(this, rsName, Cmp, InvNo);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSIT0INVR.error", ex);
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
                    StEnum.STD_LENGTH, true);
            cmd.db.begin();
            INVR.writeAll(this, Cmp, InvNo, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSIT0INVR.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSIT0INVR.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSIT0INVR.error", e);
            }
        }
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSIT0INVR.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSIT0INVR.button.cancel",
                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
        super.buttons.addButton("load", "BSIT0INVR.button.load",
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
                BSGL2ST f1 = new BSGL2ST();
                cmd.form.execute(f1);
                String trm = cmd.out.stringValue;
                if (cmd.data.isNull(trm)) {
                    return;
                }
                STD.selectAll(this, rsName, Cmp, trm);
                cmd.db.beforeFirst(rsName);
                load(rsName);
            } catch (SQLException ex) {
                cmd.log.severe("BSIT0INVR.error", ex);
            }
        }
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public String getFormTitle() {
        return "BSIT0INVR.title";
    }
}
