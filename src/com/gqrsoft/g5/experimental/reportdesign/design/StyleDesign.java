/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.design;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractStyleDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class StyleDesign extends AbstractStyleDesign {

    @Override
    protected abstract void defineGeneral();

    @Override
    protected abstract void defineText();

    @Override
    protected abstract void defineGraphics();

    @Override
    protected abstract void defineBorder();
}
