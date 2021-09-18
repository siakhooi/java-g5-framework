/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.external;

import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultStatusOutput implements EntryFieldExternalStatusOutputInterface {
//do nothing
//    @Override
//    public void setStatus(String i18nMessage) {
//    }

    @Override
    public void setStatus(StatusType type, String i18nMessage) {
    }
}
