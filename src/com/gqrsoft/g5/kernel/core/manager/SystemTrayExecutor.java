/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_protected.parameters.FormParameters;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.control.ThreadControl;
import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.core.manager.frame.FormInitializer;
import com.gqrsoft.g5.kernel.framepanel.systray.SysTrayPopupMenuGenerator;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class SystemTrayExecutor extends AbstractCoreComponent {

    public void execute(FormControl fc, SystemTrayMenuForm userForm) throws AWTException {
        FormControl mfc = new FormControl();
        ThreadControl tc = new ThreadControl();
        mfc.in = new FormParameters();
        mfc.out = new FormParameters();
        mfc.local = new FormParameters(); //GlobalParameters();
////set Parent
        FormInitializer.initFormControl(mfc, super.core(),
                fc.frame, userForm, tc);
//        FormInitializer.initFormControl(mfc, super.core(),
//                fc.parentFrame, userForm, fc.threadControl);

        FormInitializer.initCommand(mfc);

//        mfc.cmd = fc.cmd;
//        userForm.setCommands(mfc.cmd);
//        mfc.uniqueSessionId = fc.cmd.random.getAlphaNumericString(
//                EngineConfiguration.Form.FORM_UNIQUE_SESSION_ID_LENGTH);

        mfc.allEmbedded = new Vector<UserFormInterface>();
//        mfc.allEmbedded = fc.allEmbedded;
        //TODO: should this here? remove if global signal exist
        mfc.allEmbedded.add(userForm);

        //mfc.in = fc.in;
        //mfc.out = new FormParameters();

        userForm.initThread(true);
        userForm.initForm();
        userForm.build();
        userForm.onInEnter();

        userForm.eventBeforeVisible();

        showTrayIcon(userForm);

        userForm.eventAfterVisible();

    }

    private void showTrayIcon(SystemTrayMenuForm userForm) throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = userForm.getTrayIconImage();

            SysTrayPopupMenuGenerator pg = new SysTrayPopupMenuGenerator();
            PopupMenu popup = pg.generate(userForm);

            userForm.trayIcon = new TrayIcon(image,
                    userForm.getDefaultTooltips(), popup);
            userForm.trayIcon.setImageAutoSize(true);
            userForm.trayIcon.addActionListener(userForm);
            tray.add(userForm.trayIcon);
        }
    }
}
