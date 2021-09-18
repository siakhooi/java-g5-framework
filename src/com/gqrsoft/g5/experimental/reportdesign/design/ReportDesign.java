/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.design;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractReportDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ReportDesign extends AbstractReportDesign {

    @Override
    public abstract void definePageInfo();

    @Override
    public abstract void defineImports();

    @Override
    public abstract void defineScriptlet();

    @Override
    public abstract void defineDataset();

    @Override
    public abstract void defineStyles();

    @Override
    public abstract void defineBands();
}
