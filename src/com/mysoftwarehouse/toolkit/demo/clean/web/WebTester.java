/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.clean.web;

import com.gqrsoft.g5.developer.form.ButtonForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class WebTester extends ButtonForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return WebReturnInfo.class;
    }

    @Override
    public void buildButtonForm(JPanel parent) {
        super.buttons.addI18nButton("GET", "GET");
        super.buttons.addI18nButton("POST", "POST");
        super.buttons.addI18nButton("MPOST", "MultiPart POST");
    }

    @Override
    public void buttonClick(String name) {
        try {
            if ("GET".equals(name)) {
                doGet();
            } else if ("POST".equals(name)) {
                doPost();
            } else if ("MPOST".equals(name)) {
                doMultipartPost();
            }
        } catch (Exception ex) {
            cmd.local.stringValue = cmd.debug.getStackTraceText(ex);
            cmd.form.broadcastSignal(0);
        }
    }

    @Override
    public String getFormTitle() {
        return "WebTester.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    private void doGet() throws MalformedURLException, URISyntaxException, Exception {
        cmd.web.init("http://www.mysoftwarehouse.com/phpinfo.php?a=1&b=2");
        cmd.web.setDoInput(true);
        cmd.web.connect();
//     InputStream is=cmd.web.getInputStream();
        byte[] b = cmd.web.getAllBytes();
        cmd.web.close();
        cmd.local.stringValue = new String(b);
        cmd.form.broadcastSignal(0);
    }

    private void doPost() throws MalformedURLException, URISyntaxException, Exception {
        cmd.web.init("http://www.mysoftwarehouse.com/phpinfo.php");
        cmd.web.setDoInput(true);
        cmd.web.setDoOutput(true);
        cmd.web.connect();
        OutputStream os = cmd.web.getOutputStream();
        String s = "a=3&b=4";
        os.write(s.getBytes());
        cmd.web.flushOutput();
        //InputStream is=cmd.web.getInputStream();
        byte[] b = cmd.web.getAllBytes();
        cmd.web.close();
        cmd.local.stringValue = new String(b);
        cmd.form.broadcastSignal(0);
    }

    private void doMultipartPost() throws IOException, MalformedURLException, URISyntaxException, Exception {
        cmd.web.init("http://www.mysoftwarehouse.com/phpinfo.php");
        cmd.web.setDoInput(true);
        cmd.web.useMultipart();
        cmd.web.connect();
        cmd.web.addPart("name", "g5");
        //cmd.web.addPart("uploadfile", "filename", inputstream);
        //cmd.web.addPart("uploadfile", File);
        cmd.web.sendMultipart();
        //InputStream is=cmd.web.getInputStream();
        byte[] b = cmd.web.getAllBytes();
        cmd.web.close();
        cmd.local.stringValue = new String(b);
        cmd.form.broadcastSignal(0);
    }
}
