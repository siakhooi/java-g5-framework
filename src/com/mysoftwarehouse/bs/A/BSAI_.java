/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.ImageForm2;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jnlp.UnavailableServiceException;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class BSAI_ extends ImageForm2 {

    protected abstract String getURL();

    @Override
    protected void userAction() {
        try {
            cmd.jnlp.showWebDocument(new URL(
                    getURL()));
        } catch (UnavailableServiceException ex) {
            String title = "BSAI_.error";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            String message = "BSAI_.error.URL.{0}";
            message = cmd.lang.getString(message, getURL());
            cmd.common.showMessage(DialogMessageType.ERROR, title,
                    message);
        } catch (MalformedURLException ex) {
            String title = "BSAI_.error";
            cmd.log.severe(title, ex);
        }
    }

    @Override
    protected boolean hasAction() {
        return true;
    }

//    @Override
//    public boolean showButtons() {
//        return false;
//    }

    @Override
    public Color getBackgroundColor() {
        return Color.WHITE;
    }
    @Override
    public String getFormTitle() {
        return "";
    }
}
