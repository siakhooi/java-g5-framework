/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.external;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultExternalVerifier implements EntryFieldExternalEventInterface {

    @Override
    public VerifyResult verifyFieldValue(String fieldName) {
        return new VerifyResult(true, "");
    }

//    @Override
//    public String getI18nVerifyErrorMessage() {
//        return null;
//    }
//
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
}
