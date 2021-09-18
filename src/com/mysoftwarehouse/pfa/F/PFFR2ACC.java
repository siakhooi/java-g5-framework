/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.F;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFFR2ACC extends PFFR1ACC {

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFFJ2ACC.class;
    }

    @Override
    public String getFormTitle() {
        return "PFFR2ACC.title";
    }
}
