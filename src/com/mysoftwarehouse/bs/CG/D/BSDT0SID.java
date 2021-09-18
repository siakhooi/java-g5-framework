/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.PlainTextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.SiEnum;
import com.mysoftwarehouse.bs.db.SI.SID;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDT0SID extends PlainTextForm {

    String Cmp, Itm;

    @Override
    protected void init() {
        try {
            super.setColumns(SiEnum.SID_LENGTH);
            super.setRows(10);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            String rsName = "BSDT0SID";
            SID.select_All(this, rsName, Cmp, Itm);
            cmd.linewrite.init();
            while (cmd.db.next(rsName)) {
                cmd.linewrite.println(cmd.db.getString(rsName, "Text"));
            }
            super.setText(cmd.linewrite.getString());
        } catch (SQLException ex) {
            cmd.log.severe("BSDT0SID.error", ex);
        }
    }

    @Override
    protected void save(String text) {
//        try {
//            cmd.db.begin();
//            SID.writeAll(this, Cmp, Itm, text);
//            cmd.db.commit();
//        } catch (IOException ex) {
//            cmd.log.severe("BSDT0SID.error", ex);
//        } catch (SQLException ex) {
//            try {
//                cmd.db.rollback();
//            } catch (Exception e) {
//            }
//            cmd.log.severe("BSDT0SID.error", ex);
//        }
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
//                    if (ln.length() > SiEnum.SID_LENGTH) {
//                        String t1 = ln.substring(0, SiEnum.SID_LENGTH);
//                        allText.add(t1);
//                        ln = ln.substring(SiEnum.SID_LENGTH);
//                    } else {
//                        String t1 = new String(ln);
//                        allText.add(t1);
//                        ln = "";
//                    }
//                } while (ln.length() > 0);
//            } catch (BadLocationException ex) {
//                cmd.log.sysSevere("BSDT0SID.error", ex);
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
                    SiEnum.SID_LENGTH, true);
            cmd.db.begin();
            SID.writeAll(this, Cmp, Itm, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSDT0SID.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSDT0SID.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSDT0SID.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSDT0SID.title";
    }
}
