/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

/**
 *
 * @author SHNG
 */
public class QuotedHTML {

    char[] source = new char[]{
        '\"',
        '&',
        '\'',
        '<',
        '>'
    };
    String[] destination = new String[]{
        "&quot;",
        "&amp;",
        "&#39;",
        "&lt;",
        "&gt;"
    };

    public String quotedHTMLEncode(String h) {
        String r = "";
        int currentpos = 0;
        for (int i = 0; i < h.length(); i++) {
            char c = h.charAt(i);
            for (int j = 0; j < source.length; j++) {
                if (c == source[j]) {
                    r = r + h.substring(currentpos, i);
                    r = r + destination[j];
                    currentpos = i + 1;
                }
            }
        }
        r = r + h.substring(currentpos);
        return r;
    }

    public String quotedHTMLDecode(String h) {
        String r = "";
        int currentpos = 0;
        for (int i = 0; i < h.length(); i++) {
            String s = h.substring(i);
            for (int j = 0; j < source.length; j++) {
                if (s.startsWith(destination[j])) {
                    r = r + h.substring(currentpos, i);
                    r = r + source[j];
                    currentpos = i + destination[j].length();
                }
            }
        }
        r = r + h.substring(currentpos);
        return r;
    }
}
