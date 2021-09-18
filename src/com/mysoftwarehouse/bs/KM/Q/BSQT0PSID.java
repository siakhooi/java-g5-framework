/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.PlainTextForm;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PsiEnum;
import com.mysoftwarehouse.bs.db.PSI.PSID;
import com.mysoftwarehouse.util.TextArea2Vector;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQT0PSID extends PlainTextForm {

    String Cmp, SpcInst;

    @Override
    protected void init() {
        try {
            super.setColumns(PsiEnum.PSID_LENGTH);
            super.setRows(10);
            super.setEditable(true);
            super.setLineWrap(true);
            super.setWrapStyleWord(false);

            Cmp = GET.Cmp(this);
            SpcInst = cmd.in.map.texts.get(MAP.BSPSI.PSI);
            String rsName = "BSQT0PSID";
            PSID.selectAll(this, rsName, Cmp, SpcInst);
            cmd.linewrite.init();
            while (cmd.db.next(rsName)) {
                String t1 = cmd.db.getString(rsName, "Text");
                cmd.linewrite.println(t1);
            }
            super.setText(cmd.linewrite.getString());
        } catch (SQLException ex) {
            cmd.log.severe("BSQT0PSID.error", ex);
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
//                    if (ln.length() > PsiEnum.PSID_LENGTH) {
//                        String t1 = ln.substring(0, PsiEnum.PSID_LENGTH);
//                        allText.add(t1);
//                        ln = ln.substring(PsiEnum.PSID_LENGTH);
//                    } else {
//                        String t1 = new String(ln);
//                        allText.add(t1);
//                        ln = "";
//                    }
//                } while (ln.length() > 0);
//            } catch (BadLocationException ex) {
//                cmd.log.sysSevere("BSQT0PSID.error", ex);
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
                    PsiEnum.PSID_LENGTH, true);
            cmd.db.begin();
            PSID.writeAll(this, Cmp, SpcInst, allText);
            cmd.db.commit();
        } catch (BadLocationException ex) {
            cmd.log.severe("BSQT0PSID.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSQT0PSID.error", ex);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
                cmd.log.severe("BSQT0PSID.error", e);
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "BSQT0PSID.title";
    }
    }
