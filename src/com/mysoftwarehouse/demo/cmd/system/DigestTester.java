/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.cmd.system;

import com.gqrsoft.g5.developer.form.PlainTextForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class DigestTester extends PlainTextForm {

    @Override
    protected void init() {
        super.setColumns(80);
        super.setRows(15);
        super.setEditable(false);
        super.setLineWrap(false);
        cmd.linewrite.init();
        String text = "The quick brown fox jumps over the lazy dog.";
        String s;
        cmd.linewrite.println(text);
        cmd.linewrite.println("CRF32:");
        s = cmd.data.long2String(cmd.digest.crc32(text.getBytes()));
        cmd.linewrite.println(s);

        cmd.linewrite.println("MD5  :");
        s = cmd.digest.md5Hex(text);
        cmd.linewrite.println(s);

        cmd.linewrite.println("SHA  :");
        s = cmd.digest.shaHex(text);
        cmd.linewrite.println(s);

        cmd.linewrite.println("Base64:");
        byte[] md5 = cmd.digest.md5(text);
        byte[] base64encode = cmd.codec.base64Encode(md5, true);
        byte[] base64decode = cmd.codec.base64Decode(base64encode);
        cmd.linewrite.println(new String(cmd.codec.hexEncode(md5)));

        cmd.linewrite.println(new String(base64encode));
        cmd.linewrite.println(new String(cmd.codec.hexEncode(base64decode)));

        cmd.linewrite.println("Hex:");
        char[] hexencode = cmd.codec.hexEncode(md5);
        try {
            byte[] hexdecode = cmd.codec.hexDecode(hexencode);

            cmd.linewrite.println(new String(hexencode));
            cmd.linewrite.println(new String(cmd.codec.base64Encode(hexdecode, true)));
        } catch (Exception e) {
        }

        cmd.linewrite.println("QuotedHTMLEncode");
        String text1 = "if(a < b & c>d & e!='\"'){}";
        String c = cmd.codec.quotedHTMLEncode(text1);
        cmd.linewrite.println(c);
        cmd.linewrite.println(cmd.codec.quotedHTMLDecode(c));

        try {
            cmd.linewrite.println("QuotedPrintableEncode");
            String d = cmd.codec.quotedPrintableEncode(new String(md5));
            cmd.linewrite.println(d);
            cmd.linewrite.println(
                    new String(cmd.codec.hexEncode(
                    cmd.codec.quotedPrintableDecode(d).getBytes())));
        } catch (Exception e) {
        }
        try {
            cmd.linewrite.println("UrlCodec");
            String text2 = cmd.url.encode(text);
            cmd.linewrite.println(text2);
            cmd.linewrite.println(cmd.url.decode(text2));
        } catch (Exception e) {
        }

        super.setText(cmd.linewrite.getString());
    }

    @Override
    protected void save(String newText) {
    }

    @Override
    public String getFormTitle() {
        return "DigestTester.title";
    }
}
