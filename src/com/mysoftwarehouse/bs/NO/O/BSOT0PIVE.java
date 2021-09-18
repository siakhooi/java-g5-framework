/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.TextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PiEnum;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOT0PIVE extends TextForm {

    private String Cmp,  PivNo,  Itm;
    private String rsName = "BSOT0PIVE";

    @Override
    protected void init() {
        try {
            super.setColumns(PiEnum.PID_LENGTH);
            super.setRows(20);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            PivNo = cmd.in.map.texts.get(MAP.BSPIV.PIVNO);
            Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            PIVE.selectAll(this, rsName, Cmp, PivNo, Itm);
            load(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSOT0PIVE.error", ex);
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
                    PiEnum.PID_LENGTH, true);
            cmd.db.begin();
            PIVE.writeAll(this, Cmp, PivNo, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSOT0PIVE.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSOT0PIVE.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSOT0PIVE.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSOT0PIVE.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("ok", "BSOT0PIVE.button.ok",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("cancel", "BSOT0PIVE.button.cancel",
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
