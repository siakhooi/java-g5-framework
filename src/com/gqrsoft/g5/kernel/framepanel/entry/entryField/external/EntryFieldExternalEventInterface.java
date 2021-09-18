/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.external;

/**
 *
 * @author Ng Siak Hooi
 */
public interface EntryFieldExternalEventInterface {

    public VerifyResult verifyFieldValue(String fieldName);

//    public String getI18nVerifyErrorMessage();

    public void onFieldOutExit(String fieldName);

    public void onFieldOutEnter(String fieldName);

    public void onFocusLost(String fieldName);

    public void onFocusGain(String fieldName);
}
