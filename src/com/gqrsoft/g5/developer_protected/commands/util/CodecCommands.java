/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.net.QuotedPrintableCodec;

/**
 *
 * @author Ng Siak Hooi
 */
public class CodecCommands {

    private QuotedPrintableCodec qpc = new QuotedPrintableCodec();

    public String quotedPrintableEncode(String pString) throws EncoderException {
        return qpc.encode(pString);
    }

    public String quotedPrintableEncode(String pString, String charset) throws UnsupportedEncodingException {
        return qpc.encode(pString, charset);
    }

    public String quotedPrintableDecode(String pString) throws DecoderException {
        return qpc.decode(pString);
    }

    public String quotedPrintableDecode(String pString, String charset) throws DecoderException, UnsupportedEncodingException {
        return qpc.decode(pString, charset);
    }

    public byte[] base64Decode(byte[] base64Data) {
        return Base64.decodeBase64(base64Data);
    }

    public byte[] base64Encode(byte[] binaryData, boolean isChunked) {
        return Base64.encodeBase64(binaryData, isChunked);
    }

    public char[] hexEncode(byte[] data) {
        return Hex.encodeHex(data);
    }

    public byte[] hexDecode(char[] data) throws DecoderException {
        return Hex.decodeHex(data);
    }

    public String quotedHTMLEncode(String h) {
        String r = "";
        int currentpos = 0;
        for (int i = 0; i < h.length(); i++) {
            char c = h.charAt(i);
            if (c == '\"') {
                r = r + h.substring(currentpos, i);
                r = r + "&quot;";
                currentpos = i + 1;
            } else if (c == '&') {
                r = r + h.substring(currentpos, i);
                r = r + "&amp;";
                currentpos = i + 1;
            } else if (c == '\'') {
                r = r + h.substring(currentpos, i);
                r = r + "&#39;";
                currentpos = i + 1;
            } else if (c == '<') {
                r = r + h.substring(currentpos, i);
                r = r + "&lt;";
                currentpos = i + 1;
            } else if (c == '>') {
                r = r + h.substring(currentpos, i);
                r = r + "&gt;";
                currentpos = i + 1;
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
            if (s.startsWith("&quot;")) {
                r = r + h.substring(currentpos, i);
                r = r + "\"";
                currentpos = i + 6;
            } else if (s.startsWith("&amp;")) {
                r = r + h.substring(currentpos, i);
                r = r + "&";
                currentpos = i + 5;
            } else if (s.startsWith("&#39;")) {
                r = r + h.substring(currentpos, i);
                r = r + "\'";
                currentpos = i + 5;
            } else if (s.startsWith("&lt;")) {
                r = r + h.substring(currentpos, i);
                r = r + "<";
                currentpos = i + 4;
            } else if (s.startsWith("&gt;")) {
                r = r + h.substring(currentpos, i);
                r = r + ">";
                currentpos = i + 4;
            }
        }
        r = r + h.substring(currentpos);
        return r;
    }
}
