/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormCommands extends AbstractCommandComponent {

    public void closeForm() {
        if (getFormControl().frame != null) {
            getFormControl().frame.close();
        }
    }

//    public void closeThread() {
//        if (getFormControl().threadControl.root != null) {
//            getFormControl().threadControl.root.close();
//        }
//    }
    public void closeAllWindowsButMe() {
        getFormControl().core.win.closeAllWindowsButMe(
                getFormControl().frame);
    }

    public void closeApplication() {
        closeApplication(true);
    }

    public void closeApplication(boolean confirmation) {
        if (confirmation) {
            String i18nTitle = "WindowsManager.ExitConfirmation.Title";
            i18nTitle = getFormControl().cmd.lang.getSystemString(i18nTitle);
            String message = "WindowsManager.ExitConfirmation.Message";
            message = getFormControl().cmd.lang.getSystemString(message);
            boolean i = getFormControl().cmd.common.showI18nConfirmation(
                    DialogMessageType.WARNING, i18nTitle, message);
            if (!i) {
                return;
            }
        }
        getFormControl().core.exit();
    }

    public UserFormInterface create(Class<? extends UserFormInterface> userForm) {
        return getFormControl().core.form.create(userForm);
    }

    public void execute(UserFormInterface form) {
        getFormControl().core.form.execute(
                getFormControl().frame,
                form, true, false);
    }

    public void executeInNewThread(UserFormInterface form) {
        getFormControl().core.form.execute(
                getFormControl().frame,
                form,
                false,
                true);
    }

    public JPanel createEmbedded(UserFormInterface form) {
        FramePanelInterface fpi =
                getFormControl().core.form.createEmbedded(getFormControl(), form);
        return fpi.getPanel();
    }

    public JPopupMenu createPopupMenu(MenuForm form) {
        return getFormControl().core.form.generatePopupMenu(getFormControl(), form);
    }

    public void attachPopupMenu(JComponent jc, MenuForm form) {
        for (MouseListener m : jc.getMouseListeners()) {
            if (m instanceof ContextMenuMouseListener) {
                jc.removeMouseListener(m);
            }
        }
        ContextMenuMouseListener cmml = new ContextMenuMouseListener(createPopupMenu(form));
        jc.addMouseListener(cmml);
    }

    public void broadcastThreadedSignal(final int signalNumber) {
        for (final UserFormInterface i : getFormControl().allEmbedded) {
            (new Thread() {

                @Override
                public void run() {
                    i.receiveSignal(signalNumber);
                }
            }).start();
        }
    }

    public void broadcastBlockingSignal(int signalNumber) {
        for (final UserFormInterface i : getFormControl().allEmbedded) {
            i.receiveSignal(signalNumber);
        }
    }

    public void broadcastSystemSignal(int signalNumber, String signalCode) {
        for (final UserFormInterface i : getFormControl().allEmbedded) {
            i.receiveSystemSignal(signalNumber, signalCode);
        }
    }

    public void setLastWindowsCheck(boolean checkLastWindows) {
        getFormControl().core.win.setLastWindowsCheck(checkLastWindows);
    }

    @Deprecated
    public void broadcastSignal(int signalNumber) {
        broadcastSignal(signalNumber, false);
    }

    @Deprecated
    public void broadcastSignal(int signalNumber, boolean block) {
        if (block) {
            broadcastSignalBlocking(signalNumber);
        } else {
            broadcastSignalNonBlocking(signalNumber);
        }
    }

    @Deprecated
    private void broadcastSignalBlocking(int signalNumber) {
        for (final UserFormInterface i : getFormControl().allEmbedded) {
            i.receiveSignal(signalNumber);
        }
    }

    @Deprecated
    private void broadcastSignalNonBlocking(final int signalNumber) {
        for (final UserFormInterface i : getFormControl().allEmbedded) {
            (new Thread() {

                @Override
                public void run() {
                    i.receiveSignal(signalNumber);
                }
            }).start();
        }
    }
}
