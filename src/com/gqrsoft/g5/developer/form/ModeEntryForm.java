/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractModeEntryForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ModeEntryForm extends AbstractModeEntryForm {

    public abstract void initModeAction();

    public abstract void finishModeAction();

    public abstract void saveAdd() throws Exception;

    public abstract void saveEdit() throws Exception;

    public abstract void saveDelete() throws Exception;

    public abstract void loadData() throws Exception;

    public abstract void loadDefault() throws Exception;

    public void throwNoRecordError() throws Exception {
        String msg = "ModeEntryForm.error.NoRecordFound";
        msg = cmd.lang.getSystemString(msg);
        throw new Exception(msg);
    }
}
