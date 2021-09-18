/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.gqrsoft.g5.developer_protected.abstractform.AbstractReportExecutionForm;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ReportExecutionForm extends AbstractReportExecutionForm {

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    @Override
    protected abstract void initReport() throws ReportExecutionException;

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    @Override
    protected abstract void prepareExecution() throws ReportExecutionException;

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    @Override
    protected abstract void initExecution() throws ReportExecutionException;

    @Override
    protected File getPdfFile() {
        return cmd.common.chooseSavePDFFile(null);

    }
}
