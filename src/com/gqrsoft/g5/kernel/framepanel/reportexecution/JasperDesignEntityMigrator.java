/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.reportexecution;

import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.gqrsoft.g5.developer_protected.commands.Commands;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public class JasperDesignEntityMigrator {

    public static void move(Commands cmd, JasperDesign jasperDesign, JasperDesign jd) throws ReportExecutionException {
        try {
            for (JRParameter p : jd.getMainDesignDataset().getParameters()) {
                jasperDesign.getMainDesignDataset().addParameter(p);
            }
            for (JRField f : jd.getMainDesignDataset().getFields()) {
                jasperDesign.getMainDesignDataset().addField(f);
            }
            for (JRSortField sf : jd.getMainDesignDataset().getSortFields()) {
                jasperDesign.getMainDesignDataset().addSortField(sf);
            }
            for (JRVariable v : jd.getMainDesignDataset().getVariables()) {
                JRDesignVariable v1 = (JRDesignVariable) v;
                jasperDesign.getMainDesignDataset().addVariable(v1);
            }
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.applyTemplate.EntityMigrator";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg, ex);
        }

    }
}
