/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.tools;

import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextArea2Vector {

    private static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    private static String remove0D0A(String source) {
        return source.replaceAll("\n", "").replaceAll("\r", "");
    }

    public static Vector<String> convert(JTextArea textArea,
            boolean removeTrailingBlankLine) throws BadLocationException {
        int lc = textArea.getDocument().getDefaultRootElement().getElementCount();
        Vector<String> allText = new Vector<String>();
        for (int i = 0; i < lc; i++) {
            Element em = textArea.getDocument().getDefaultRootElement().getElement(i);
            int s = em.getStartOffset();
            int t = em.getEndOffset();
            String ln = textArea.getText(s, t - s);
            ln = rtrim(ln);
            ln = remove0D0A(ln);
            do {
                String t1 = new String(ln);
                allText.add(t1);
                ln = "";
            } while (ln.length() > 0);
        }
        if (removeTrailingBlankLine) {
            for (int it = allText.size(); it > 0; it--) {
                String t = allText.elementAt(it - 1);
                if (isNull(t)) {
                    allText.removeElementAt(it - 1);
                } else {
                    break;
                }
            }
        }
        return allText;
    }

    private static boolean isNull(String t) {
        if (t == null) {
            return true;
        }
        return t.equals("");
    }
}
