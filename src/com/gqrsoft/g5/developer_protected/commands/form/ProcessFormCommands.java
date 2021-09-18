/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormCommands extends AbstractCommandComponent {

    public void completed() throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.completed();
        }
    }

    public void setMinorTotalCount(int i) throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.setMinorTotalCount(i);
        }
    }

    public void minorCompleted(int i) throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.minorCompleted(i);
        }
    }

    public void disableCancellation() {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.disableCancellation();
        }
    }

    public void cancelNow() throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.cancelNow();
        }
    }

    public void setI18nLogMessage(String s) throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.setI18nLogMessage(s);
        }
    }

    public void setI18nProgressMessage(String s) throws ProcessException {
        if (getFormControl().processPanel != null) {
            getFormControl().processPanel.setI18nProgressMessage(s);
        }
    }
}
