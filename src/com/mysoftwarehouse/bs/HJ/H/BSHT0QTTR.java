/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.CG.G.BSGL1ST;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.StEnum;
import com.mysoftwarehouse.bs.db.QTT.QTTR;
import com.mysoftwarehouse.bs.db.ST.STD;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHT0QTTR extends TextForm {

    String Cmp, QttNo;
    String rsName = "BSHT0QTTR";

    @Override
    protected void init() {
        try {
            super.setColumns(StEnum.STD_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
            QTTR.selectAll(this, rsName, Cmp, QttNo);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSHT0QTTR.error", ex);
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
            QTTR.writeAll(this, Cmp, QttNo, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSHT0QTTR.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSHT0QTTR.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSHT0QTTR.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSHT0QTTR.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSHT0QTTR.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSHT0QTTR.button.cancel",
                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
        super.buttons.addButton("load", "BSHT0QTTR.button.load",
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
                BSGL1ST f1 = new BSGL1ST();
                cmd.form.execute(f1);
                String trm = cmd.out.stringValue;
                if (cmd.data.isNull(trm)) {
                    return;
                }
                STD.selectAll(this, rsName, Cmp, trm);
                cmd.db.beforeFirst(rsName);
                load(rsName);
            } catch (SQLException ex) {
                cmd.log.severe("BSHT0QTTR.error", ex);
            }
        }
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
