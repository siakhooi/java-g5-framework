/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractEntryForm;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalEventInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.VerifyResult;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class EntryForm extends AbstractEntryForm implements EntryFieldExternalEventInterface {

    @Override
    protected abstract void buildFieldList();

    @Override
    protected abstract void buildButtonsList();

    @Override
    public void buttonClick(String name) {
    }

    @Override
    public void onFieldOutExit(String fieldName) {
    }

    @Override
    public void onFieldOutEnter(String fieldName) {
    }

    @Override
    public void onFocusLost(String fieldName) {
    }

    @Override
    public void onFocusGain(String fieldName) {
    }

    @Deprecated
    public boolean verify(String fieldName) {
        return true;
    }

    public void verifyField(String fieldName) throws Exception {
    }

    @Override
    public VerifyResult verifyFieldValue(String fieldName) {
        VerifyResult vr = new VerifyResult(true, "");
        try {
            if (!verify(fieldName)) {
                vr.valid = false;
                vr.i18nErrorMessage = i18nVerifyErrorMessage;
            } else {
                verifyField(fieldName);
            }
        } catch (Exception ex) {
            vr.i18nErrorMessage = ex.getMessage();
            vr.valid = false;
        }
        return vr;
    }
    @Deprecated
    private String i18nVerifyErrorMessage;

//    @Override
//    public String getI18nVerifyErrorMessage() {
//        return i18nVerifyErrorMessage;
//    }
    protected void throwI18nVerifyError(String i18nErrorMessage) throws Exception {
        throw new Exception(i18nErrorMessage);
    }

    protected void throwVerifyError(String errorMessage) throws Exception {
        errorMessage = cmd.lang.getString(errorMessage);
        throwI18nVerifyError(errorMessage);
    }

    protected void throwVerifyError(String errorMessage, String... values) throws Exception {
        errorMessage = cmd.lang.getString(errorMessage, values);
        throwI18nVerifyError(errorMessage);
    }

    @Deprecated
    protected void setI18nVerifyErrorMessage(String i18nErrorMessage) {
        this.i18nVerifyErrorMessage = i18nErrorMessage;
    }

    @Deprecated
    protected void setVerifyErrorMessage(String errorMessage) {
        errorMessage = cmd.lang.getString(errorMessage);
        setI18nVerifyErrorMessage(errorMessage);
    }

    @Deprecated
    protected void setVerifyErrorMessage(String errorMessage, String... values) {
        errorMessage = cmd.lang.getString(errorMessage, values);
        setI18nVerifyErrorMessage(errorMessage);
    }
}
