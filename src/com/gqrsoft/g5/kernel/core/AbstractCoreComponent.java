/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractCoreComponent implements CoreComponentInterface {

    private Core core;

    @Override
    public Core core() {
        return this.core;
    }

    @Override
    public void setCore(Core core) {
        this.core = core;
    }
}
