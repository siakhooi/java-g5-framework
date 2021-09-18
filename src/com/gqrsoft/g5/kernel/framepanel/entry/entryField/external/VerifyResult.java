/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.external;

/**
 *
 * @author Ng Siak Hooi
 */
public class VerifyResult {

    public boolean valid = true;
    public String i18nErrorMessage = "";

    public VerifyResult(boolean valid, String i18nErrorMessage) {
        this.valid = valid;
        this.i18nErrorMessage = i18nErrorMessage;
    }
}
