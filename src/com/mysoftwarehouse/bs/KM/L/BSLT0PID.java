/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.PlainTextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PiEnum;
import com.mysoftwarehouse.bs.db.PI.PID;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLT0PID extends PlainTextForm {

    String Cmp, Itm;

    @Override
    protected void init() {
        try {
            super.setColumns(PiEnum.PID_LENGTH);
            super.setRows(10);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
            String rsName = "BSLT0PID";
            PID.select_All(this, rsName, Cmp, Itm);
            cmd.linewrite.init();
            while (cmd.db.next(rsName)) {
                cmd.linewrite.println(cmd.db.getString(rsName, "Text"));
            }
            super.setText(cmd.linewrite.getString());
        } catch (SQLException ex) {
            cmd.log.severe("BSLT0PID.error", ex);
        }
    }

    @Override
    protected void save(String text) {
//        int lc = super.textArea.getDocument().getDefaultRootElement().getElementCount();
//        Vector<String> allText = new Vector<String>();
//        for (int i = 0; i < lc; i++) {
//            try {
//                Element em = super.textArea.getDocument().getDefaultRootElement().getElement(i);
//                int s = em.getStartOffset();
//                int t = em.getEndOffset();
//                String ln = super.textArea.getText(s, t - s);
//                ln = StringTool.rtrim(ln);
//                do {
//                    if (ln.length() > PiEnum.PID_LENGTH) {
//                        String t1 = ln.substring(0, PiEnum.PID_LENGTH);
//                        allText.add(t1);
//                        ln = ln.substring(PiEnum.PID_LENGTH);
//                    } else {
//                        String t1 = new String(ln);
//                        allText.add(t1);
//                        ln = "";
//                    }
//                } while (ln.length() > 0);
//            } catch (BadLocationException ex) {
//                cmd.log.sysSevere("BSLT0PID.error", ex);
//            }
//        }
//        for (int it = allText.size(); it > 0; it--) {
//            String t = allText.elementAt(it - 1);
//            if (cmd.data.isNull(t)) {
//                allText.removeElementAt(it - 1);
//            } else {
//                break;
//            }
//        }

        try {
            Vector<String> allText = TextArea2Vector.convert(textArea,
                    PiEnum.PID_LENGTH, true);
            cmd.db.begin();
            PID.writeAll(this, Cmp, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSLT0PID.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSLT0PID.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSLT0PID.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSLT0PID.title";
    }
}
