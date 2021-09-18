/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractReportEntryForm extends EntryForm {

    public boolean hasViewButton = true;
    public boolean hasPrintDirectButton = true;
    public boolean hasPrintDialogButton = true;
    public boolean hasSaveAsPDFButton = true;
    public boolean hasResetButton = true;
    public boolean hasCloseButton = true;

    public abstract Class<? extends ReportExecutionForm> getReportForm();
    //public Class<? extends ReportExecutionForm> reportForm = null;
    /**
     * not in used.
     */
    @Override
    public final void onEscapeKeyPressed() {
    }
}
