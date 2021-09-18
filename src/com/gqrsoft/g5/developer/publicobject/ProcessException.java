/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessException extends Exception {

    public boolean userCancelled = true;

    public ProcessException(boolean userCancelled) {
        super();
        this.userCancelled = userCancelled;
    }

    public ProcessException(boolean userCancelled, String msg) {
        super(msg);
        this.userCancelled = userCancelled;
    }
}
