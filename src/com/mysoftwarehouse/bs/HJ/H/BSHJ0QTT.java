/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.DONGLE;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.QttEnum;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.QTT.QTT_HJ0QTT;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHJ0QTT extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String fmt = cmd.in.map.texts.get(MAP.BSCFG.QTTFORMAT);
        String r;
        InputStream in;
        switch (QttEnum.Format.get(fmt)) {
            case B:
                r = REPORT.REPORT_PATH + "BSHX0QTT_HB.jasper";
                in = BSHJ0QTT.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case T:
                r = REPORT.REPORT_PATH + "BSHX0QTT_HT.jasper";
                in = BSHJ0QTT.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case I:
                r = REPORT.REPORT_PATH + "BSHX0QTT_HI.jasper";
                in = BSHJ0QTT.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
        }
    }
    String QttNo = "";
    String rsNameQttj = "BSHJ0QTT.rsQttj";
    boolean hasQttj = false;
    String rsNameQttr = "BSHJ0QTT.rsQttr";
    boolean hasQttr = false;
    String rsName = "BSHJ0QTT.qtt";
    String rsNameCmp = "BSHJ0QTT.rsCmp";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            QttNo = cmd.in.map.texts.get(MAP.BSQTT.FROM);
            String Cmp = GET.Cmp(this);
            QTT_HJ0QTT.select(this, rsName, Cmp, QttNo);

            QTT_HJ0QTT.selectQttj(this, rsNameQttj, Cmp, QttNo);
            if (cmd.db.next(rsNameQttj)) {
                hasQttj = true;
                cmd.db.beforeFirst(rsNameQttj);
            }
            QTT_HJ0QTT.selectQttr(this, rsNameQttr, Cmp, QttNo);
            if (cmd.db.next(rsNameQttr)) {
                hasQttr = true;
                cmd.db.beforeFirst(rsNameQttr);
            }

            QTT_HJ0QTT.selectCmp(this, rsNameCmp, Cmp);
            super.useResultSetDataSource(rsNameCmp);

        } catch (SQLException ex) {
            cmd.log.severe("BSHJ0QTT.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);

        String fmt = cmd.in.map.texts.get(MAP.BSCFG.QTTFORMAT);
        switch (QttEnum.Format.get(fmt)) {
            case B:
                break;
            case T:
                break;
            case I:
                super.putParameter("BSHX0QTT.IMG.HEAD", CFGI.getLetterHead(this, Cmp));
                break;
        }

        InputStream qttj = BSHJ0QTT.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSHX0QTT_J.jasper");
        super.putCompiledSubreport("BSHX0QTT.SR.QTTJ", qttj);
        super.putDataSource("BSHX0QTT.DS.QTTJ", cmd.db.getResultSet(rsNameQttj));
        super.putParameter("BSHX0QTT.HasQTTJ", new Boolean(hasQttj));

        InputStream qttr = BSHJ0QTT.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSHX0QTT_R.jasper");
        super.putCompiledSubreport("BSHX0QTT.SR.QTTR", qttr);
        super.putDataSource("BSHX0QTT.DS.QTTR", cmd.db.getResultSet(rsNameQttr));
        super.putParameter("BSHX0QTT.HasQTTR", new Boolean(hasQttr));

        InputStream qtt = BSHJ0QTT.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSHX0QTT.jasper");
        super.putCompiledSubreport("BSHX0QTT.SR.QTT", qtt);
        super.putDataSource("BSHX0QTT.DS.QTT", cmd.db.getResultSet(rsName));

        super.putParameter(REPORT.SHOW_DEMO, new Boolean(DONGLE.isDemoMode(this)));
        super.putParameter(REPORT.CURCDE, GET.CurCde(this));
        super.putParameter(REPORT.CMPS_SIGN1,
                cmd.global.texts.get(MAP.BSCMPS.QTTSIGN1));
        super.putParameter(REPORT.CMPS_SIGN2,
                cmd.global.texts.get(MAP.BSCMPS.QTTSIGN2));
    }

    @Override
    public String getFormTitle() {
        return "BSHJ0QTT.title";
    }
}
