/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer_secret.abstractform.PrivateAbstractProcessForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractProcessForm extends PrivateAbstractProcessForm {

    /**
     * engine not use this method
     */
    @Override
    public final void onEscapeKeyPressed() {
    }

    protected final void addI18nProcess(String i18nTitle, int weight) {
        processTree.add(i18nTitle, weight);
    }

    protected final void setUserManualStart(boolean value) {
        processTree.isUserManualStart = value;
    }

    protected final void setUserManualClose(boolean value) {
        processTree.isUserManualClose = value;
    }

    protected final void setUserAllowCancel(boolean value) {
        processTree.isUserAllowCancel = value;
    }

    protected final void setLogListVisible(boolean value) {
        processTree.isShowLogList = value;
    }

    protected final void addProcess(String title, int weight) {
        title = cmd.lang.getString(title);
        addI18nProcess(title, weight);
    }

    protected final void completed() throws ProcessException {
        cmd.process.completed();
    }

    protected final void setMinorTotalCount(int i) throws ProcessException {
        cmd.process.setMinorTotalCount(i);
    }

    protected final void minorCompleted() throws ProcessException {
        minorCompleted(1);
    }

    protected final void minorCompleted(int i) throws ProcessException {
        cmd.process.minorCompleted(i);
    }

    protected final void disableCancellation() {
        cmd.process.disableCancellation();
    }

    protected final void cancelNow() throws ProcessException {
        cmd.process.cancelNow();
    }

    protected final void setI18nProgressMessage(String s) throws ProcessException {
        cmd.process.setI18nProgressMessage(s);
    }

    protected final void setI18nLogMessage(String s) throws ProcessException {
        cmd.process.setI18nLogMessage(s);
    }

    protected final void setI18nMessage(String s) throws ProcessException {
        setI18nProgressMessage(s);
        setI18nLogMessage(s);
    }

    protected final void setProgressMessage(String s) throws ProcessException {
        setI18nProgressMessage(cmd.lang.getString(s));
    }

    protected final void setLogMessage(String s) throws ProcessException {
        setI18nLogMessage(cmd.lang.getString(s));
    }

    protected final void setMessage(String s) throws ProcessException {
        setProgressMessage(s);
        setLogMessage(s);
    }
}
