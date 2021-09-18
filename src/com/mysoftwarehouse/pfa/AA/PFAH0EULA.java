/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public class PFAH0EULA extends StyledTextForm {

    @Override
    protected boolean useScrollPane() {
        return true;
    }

    @Override
    protected boolean showStatus() {
        return false;
    }

    @Override
    public void eventBeforeVisible() {
//        int w = cmd.sys.getScreenWidth();
//        int h = cmd.sys.getScreenHeight();
//        int x = w / 4, y = h / 4;
//        w = w / 2;
//        h = h / 2;
//        cmd.debug.println("x:" + x);
//        cmd.debug.println("y:" + y);
//        cmd.debug.println("w:" + w);
//        cmd.debug.println("h:" + h);
//        int w1 = cmd.frame.getWidth();
//        int h1 = cmd.frame.getHeight();
//        cmd.debug.println("w1:" + w1);
//        cmd.debug.println("h1:" + h1);
//        cmd.frame.setSize(w, h);
//        cmd.frame.setLocation(x, y);
//        int w2 = cmd.frame.getWidth();
//        int h2 = cmd.frame.getHeight();
//        cmd.debug.println("w2:" + w2);
//        cmd.debug.println("h2:" + h2);

    //cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
    }

    @Override
    public String getInitialText() {
        String s = "";
        try {
            InputStream in = PFAH0EULA.class.getResourceAsStream(
                    RESOURCE.DEFAULT_EULA_FILE);
            byte[] data = cmd.data.inputStream2ByteArray(in);
            s = new String(data);
            s = s.replaceAll("<!--A", "");
            s = s.replaceAll("A-->", "");

            s = s.replaceAll("<!--R", "");
            s = s.replaceAll("R-->", "");
        } catch (IOException ex) {
            String title = "PFAH0EULA.error";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            s = title;
        }
        return s;
    }

    @Override
    public void hyperlinkClick(String name) {
        if ("accept".equals(name)) {
            cmd.form.closeForm();
        } else if ("reject".equals(name)) {
            cmd.form.closeApplication(false);
        }
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "PFAH0EULA.title";
    }

    @Override
    public void onEscapeKeyPressed() {
    }
}
