/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class SystemTrayMenuForm extends MenuForm implements ActionListener {

    public TrayIcon trayIcon = null;

    public Image getTrayIconImage() {
        return cmd.icon.getLogo().getImage();
    }

    public String getDefaultTooltips() {
        return "";
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public void onEscapeKeyPressed() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    protected final void remove() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            tray.remove(trayIcon);
            
        }
        cmd.form.closeForm();
    }

    protected final void setImage(Image image) {
        trayIcon.setImage(image);
    }

    protected final void setToolTip(String tooltip) {
        trayIcon.setToolTip(tooltip);
    }
}
