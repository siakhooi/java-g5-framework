/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractReportEntryForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ReportEntryForm extends AbstractReportEntryForm {

    @Override
    protected abstract void buildFieldList();

    @Override
    protected void buildButtonsList() {
    }
}
