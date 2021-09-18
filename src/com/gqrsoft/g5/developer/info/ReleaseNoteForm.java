/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.info;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.StyledTextType;
import com.gqrsoft.g5.kernel.config.EngineResource;
import java.io.IOException;
import java.io.InputStream;

/**
 *s
 * @author Ng Siak Hooi
 */
@Deprecated
public class ReleaseNoteForm extends StyledTextForm {

    @Override
    protected boolean useScrollPane() {
        return true;
    }

//    @Override
//    public void eventAfterVisible() {
//        super.eventAfterVisible();
//        int w = cmd.sys.getScreenWidth();
//        int h = cmd.sys.getScreenHeight();
//        int x = w / 4;
//        int y = w / 4;
//        cmd.debug.println("XY: "+x+":"+y);
//        cmd.frame.setLocation(x, y);
//        w = w * 3 / 4;
//        h = h * 3 / 4;
//        cmd.debug.println("WH: "+w+":"+h);
//        cmd.frame.setSize(w, h);
//    }
//
    @Override
    public String getInitialText() {
        String f = EngineResource.RELEASE_NOTE_TEXT;
        InputStream is = EngineResource.class.getResourceAsStream(f);
        byte[] data = null;
        try {
            data = cmd.data.inputStream2ByteArray(is);
        } catch (IOException ex) {
        }
        if (data == null) {
            return "";
        }
        String htmlText = new String(data);
//        String name = "", value = "";
//        name = "ReleaseNoteForm.title";
//        value = "ReleaseNoteForm.title";
//        value = cmd.lang.getSystemString(value);
//        htmlText = htmlText.replaceAll(name, value);
//        
//        name = "ReleaseNoteForm.Close.title";
//        value = "";
//        htmlText = htmlText.replaceAll(name, value);

//        name = "ReleaseNoteForm.Close.label";
//        value = "ReleaseNoteForm.close";
//        value = cmd.lang.getSystemString(value);
//        htmlText = htmlText.replaceAll(name, value);

        return htmlText;
    }

    @Override
    public void hyperlinkClick(String href) {
        if ("close".equals(href)) {
            cmd.form.closeForm();
        }
    }

    @Override
    public StyledTextType getType() {
        return StyledTextType.HTML;
    }

    @Override
    public String getFormI18nTitle() {
        String title = "ReleaseNoteForm.title";
        return cmd.lang.getSystemString(title);
    }

    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    protected boolean showStatus() {
        return false;
    }
}
