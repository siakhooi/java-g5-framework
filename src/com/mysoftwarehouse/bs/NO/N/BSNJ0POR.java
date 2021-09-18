/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.DONGLE;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PorEnum;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.POR.POR_NJ0POR;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNJ0POR extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String fmt = cmd.in.map.texts.get(MAP.BSCFG.PORFORMAT);
        String r;
        InputStream in;
        switch (PorEnum.Format.get(fmt)) {
            case B:
                r = REPORT.REPORT_PATH + "BSNX0POR_HB.jasper";
                in = BSNJ0POR.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case T:
                r = REPORT.REPORT_PATH + "BSNX0POR_HT.jasper";
                in = BSNJ0POR.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case I:
                r = REPORT.REPORT_PATH + "BSNX0POR_HI.jasper";
                in = BSNJ0POR.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
        }        
    }
    String PorNo = "";
    String rsNamePors = "BSNJ0POR.rsPors";
    boolean hasPors = false;
    String rsNameCmp = "BSNJ0POR.rsCmp";
    String rsName = "BSNJ0POR.por";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            PorNo = cmd.in.map.texts.get(MAP.BSPOR.FROM);
            String Cmp = GET.Cmp(this);
            POR_NJ0POR.select(this, rsName, Cmp, PorNo);
            
            POR_NJ0POR.selectPors(this, rsNamePors, Cmp, PorNo);
            if (cmd.db.next(rsNamePors)) {
                hasPors = true;
                cmd.db.beforeFirst(rsNamePors);
            }
            POR_NJ0POR.selectCmp(this, rsNameCmp, Cmp);
            super.useResultSetDataSource(rsNameCmp);
            
        } catch (SQLException ex) {
            cmd.log.severe("BSNJ0POR.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);

        String fmt = cmd.in.map.texts.get(MAP.BSCFG.PORFORMAT);
        switch (PorEnum.Format.get(fmt)) {
            case B:
                break;
            case T:
                break;
            case I:
                super.putParameter("BSNX0POR.IMG.HEAD", CFGI.getLetterHead(this, Cmp));
                break;
        }

        InputStream pors = BSNJ0POR.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSNX0POR_S.jasper");
        super.putCompiledSubreport("BSNX0POR.SR.PORS", pors);
        super.putDataSource("BSNX0POR.DS.PORS", cmd.db.getResultSet(rsNamePors));
        super.putParameter("BSNX0POR.HasPORS", new Boolean(hasPors));

        InputStream qtt = BSNJ0POR.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSNX0POR.jasper");
        super.putCompiledSubreport("BSNX0POR.SR.POR", qtt);
        super.putDataSource("BSNX0POR.DS.POR", cmd.db.getResultSet(rsName));
        
        super.putParameter(REPORT.SHOW_DEMO, new Boolean(DONGLE.isDemoMode(this)));
        super.putParameter(REPORT.CURCDE, GET.CurCde(this));
        super.putParameter(REPORT.CMPS_SIGN1,
                cmd.global.texts.get(MAP.BSCMPS.PORSIGN1));
        super.putParameter(REPORT.CMPS_SIGN2,
                cmd.global.texts.get(MAP.BSCMPS.PORSIGN2));
    }

    @Override
    public String getFormTitle() {
        return "BSNJ0POR.title";
    }
}
