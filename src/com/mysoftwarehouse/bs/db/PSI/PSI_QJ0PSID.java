/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PSI;

import com.mysoftwarehouse.bs.KM.Q.BSQJ0PSID;
import com.mysoftwarehouse.bs.data.PsiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PSI_QJ0PSID {

    public static void select(BSQJ0PSID form, String rsName, String Cmp,
            String FrmSpcInst, String ToSpcInst, boolean active, boolean inactive)
            throws SQLException {
        String sql = "SELECT * FROM BSPSI A, BSPSID B ";
        boolean hasFrmSpcInst = !form.cmd().data.isNull(FrmSpcInst);
        boolean hasToSpcInst = !form.cmd().data.isNull(ToSpcInst);
        sql += "WHERE A.Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.SpcInst=B.SpcInst ";

        if (hasFrmSpcInst) {
            sql += "AND A.SpcInst>=? ";
        }
        if (hasToSpcInst) {
            sql += "AND A.SpcInst<=? ";
        }
        boolean checkStatus = false;
        if (active && !inactive) {
            sql += "AND A.Status=? ";
            checkStatus = true;
        }
        if (!active && inactive) {
            sql += "AND A.Status<>? ";
            checkStatus = true;
        }
        sql += "ORDER BY A.Cmp, A.SpcInst, B.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmSpcInst) {
            form.cmd().db.setParameter(rsName, i++, FrmSpcInst);
        }
        if (hasToSpcInst) {
            form.cmd().db.setParameter(rsName, i++, ToSpcInst);
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, PsiEnum.Status.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
