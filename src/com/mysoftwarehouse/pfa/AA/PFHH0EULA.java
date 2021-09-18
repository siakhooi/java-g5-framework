/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public class PFHH0EULA extends StyledTextForm {

    @Override
    protected boolean useScrollPane() {
        return true;
    }

    @Override
    protected boolean showStatus() {
        return false;
    }

    @Override
    public void eventAfterVisible() {
        int w = cmd.sys.getScreenWidth();
        int h = cmd.sys.getScreenHeight();
        cmd.frame.setSize(w / 2, h / 2);
        cmd.frame.setLocation(w / 4, h / 4);
        cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
    }

    @Override
    public String getInitialText() {
        String s = "";
        try {
            InputStream in = PFHH0EULA.class.getResourceAsStream(
                    RESOURCE.DEFAULT_EULA_FILE);
            byte[] data = cmd.data.inputStream2ByteArray(in);
            s = new String(data);
            s = s.replaceAll("<!--O", "");
            s = s.replaceAll("O-->", "");

        } catch (IOException ex) {
            String title = "PFHH0EULA.error";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            s = title;
        }
        return s;

    }

    @Override
    public void hyperlinkClick(String name) {
        cmd.form.closeForm();
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormTitle() {
        return "PFHH0EULA.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
