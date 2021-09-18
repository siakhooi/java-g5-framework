/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_protected.commands.Commands;
import com.gqrsoft.g5.developer.form.MenuForm;
import java.util.logging.Level;

/**
 * default partial implementation of UserFormInterface.
 * Most of UserForm will derived from this class.
 * 
 * @author Ng Siak Hooi
 */
public abstract class AbstractUserForm implements UserFormInterface {

    public Commands cmd;

    @Override
    public Commands cmd() {
        return this.cmd;
    }

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return null;
    }

    public Class<? extends MenuForm> getMenuFormForToolbar() {
        return null;
    }

    @Override
    public Class<? extends UserFormInterface> getRightForm() {
        return null;
    }

    @Override
    public Class<? extends UserFormInterface> getLeftForm() {
        return null;
    }

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return null;
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return null;
    }

    @Override
    public void initThread(boolean newThread) {
        if (newThread) {
            try {
                cmd.db.initThreadDBConnection();
            } catch (Exception ex) {
                cmd.log.log(Level.ALL, "UserForm.InitThread", ex);
            }
        } else {
            cmd.db.useThreadDBConnection();
        }
    }

    @Override
    public void exitThread() {
        try {
            cmd.db.disconnect();
        } catch (Exception ex) {
            cmd.log.log(Level.ALL, "UserForm.CloseThread", ex);
        }
    }

    @Override
    public String getFormLevelLocaleBaseName() {
        return null;
    }

    @Override
    public final void setCommands(Commands value) {
        this.cmd = value;
    }

    /**
     * return original Form Title text to be display at window title bar.
     * Engine will translate the form into I18n before showing.
     * @return
     */
    public abstract String getFormTitle();

    @Override
    public String getFormI18nTitle() {
        return cmd.lang.getString(getFormTitle());
    }

    @Override
    public void initForm() {
    }

    @Override
    public void eventAfterVisible() {
    }

    @Override
    public void eventBeforeVisible() {
    }

    @Override
    public void onInEnter() {
    }

    @Override
    public void onOutExit() {
    }

    @Override
    public void onOutEnter() {
    }

    @Override
    public void onInExit() {
    }

    @Override
    public void receiveSignal(int signalNumber) {
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
    }

    @Override
    public void receiveGlobalSignal(int signalNumber) {
    }
}
