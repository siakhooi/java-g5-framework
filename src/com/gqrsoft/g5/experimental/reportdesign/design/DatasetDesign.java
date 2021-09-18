/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.design;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractDatasetDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class DatasetDesign extends AbstractDatasetDesign {

    @Override
    public abstract void defineReportQuery();

    @Override
    public abstract void defineVariables();

    @Override
    public abstract void defineParameters();

    @Override
    public abstract void defineFields();

    @Override
    public abstract void defineSortFields();

    @Override
    public abstract void defineGroups();
}
