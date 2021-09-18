/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.external;

import com.gqrsoft.g5.developer.publicobject.EntryFormEnum;

/**
 *
 * @author Ng Siak Hooi
 */
public interface EntryFieldExternalStatusOutputInterface {

//    public void setStatus(String i18nMessage);
    public void setStatus(EntryFormEnum.StatusType type, String i18nMessage);
}
