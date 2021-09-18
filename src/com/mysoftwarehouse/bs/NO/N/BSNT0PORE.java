/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SiEnum;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNT0PORE extends TextForm {

    private String Cmp,  PorNo,  Itm;
    private String rsName = "BSNT0PORE";

    @Override
    protected void init() {
        try {
            super.setColumns(SiEnum.SID_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            PorNo = cmd.in.map.texts.get(MAP.BSPOR.PORNO);
            Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            PORE.selectAll(this, rsName, Cmp, PorNo, Itm);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSNT0PORE.error", ex);
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
            PORE.writeAll(this, Cmp, PorNo, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSNT0PORE.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSNT0PORE.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSNT0PORE.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSNT0PORE.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSNT0PORE.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSNT0PORE.button.cancel",
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
