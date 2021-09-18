/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SiEnum;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHT0QTTE extends TextForm {

    private String Cmp,  QttNo,  Itm;
    private String rsName = "BSHT0QTTE";

    @Override
    protected void init() {
        try {
            super.setColumns(SiEnum.SID_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            QttNo = cmd.in.map.texts.get(MAP.BSQTT.QTTNO);
            Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            QTTE.selectAll(this, rsName, Cmp, QttNo, Itm);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSHT0QTTE.error", ex);
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
            QTTE.writeAll(this, Cmp, QttNo, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSHT0QTTE.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSHT0QTTE.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSHT0QTTE.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSHT0QTTE.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSHT0QTTE.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSHT0QTTE.button.cancel",
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
