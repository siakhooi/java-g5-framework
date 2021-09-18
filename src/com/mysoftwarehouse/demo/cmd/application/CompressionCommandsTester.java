/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.cmd.application;

import com.gqrsoft.g5.developer.form.ButtonForm;
///import com.gqrsoft.g5.kernel.config.EngineResource;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class CompressionCommandsTester extends ButtonForm {

    @Override
    public void buildButtonForm(JPanel parent) {
        super.buttons.addI18nButton("gzip", "gzip");
        super.buttons.addI18nButton("zip", "zip");
    }

    @Override
    public void buttonClick(String name) {
        try {
            if ("gzip".equals(name)) {
                byte[] data = cmd.data.inputStream2ByteArray(CompressionCommandsTester.class.getResourceAsStream(
                        "/com/mysoftwarehouse/demo/resources/languages/demo_en_US.properties"));
                byte[] gzipped = cmd.gzip.gzip(data);
                byte[] gunzipped = cmd.gzip.gunzip(gzipped);
                cmd.debug.println("md5: " + data.length + ": " +
                        cmd.digest.md5Hex(data));
                cmd.debug.println("md5: " + gzipped.length + ": " +
                        cmd.digest.md5Hex(gzipped));
                cmd.debug.println("md5: " + gunzipped.length + ": " +
                        cmd.digest.md5Hex(gunzipped));
            } else if ("zip".equals(name)) {
                byte[] data = cmd.data.inputStream2ByteArray(CompressionCommandsTester.class.getResourceAsStream(
                        "/com/mysoftwarehouse/demo/resources/languages/demo_en_US.properties"));
                cmd.zip.init();
                cmd.zip.add("test.txt", "comment1234", data);
                byte[] zipped = cmd.zip.finish();
                cmd.unzip.init(zipped);
                byte[] unzipped = cmd.unzip.getEntry();
                String entryName = cmd.unzip.getEntryName();
                String entryComment = cmd.unzip.getEntryComment();
                cmd.debug.println("md5: " + data.length + ": " +
                        cmd.digest.md5Hex(data));
                cmd.debug.println("md5: " + zipped.length + ": " +
                        cmd.digest.md5Hex(zipped));
                cmd.debug.println("md5: " + unzipped.length + ": " +
                        cmd.digest.md5Hex(unzipped));
                cmd.debug.println("name: " + entryName + " : " + entryComment);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getFormTitle() {
        return "CompressionCommandsTester.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
