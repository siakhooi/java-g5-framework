/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessEnum {

    public enum StatusType {

        NOT_STARTED,
        RUN,
        CRITICAL_RUNNING,
        USER_CANCELLED,
        PROCESS_CANCELLED,
        COMPLETED,
        USER_CANCELLING,
        PROCESS_CANCELLING;
    }
}
