/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPC {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        //CMPAH.createDelete(form, Cmp);
        String sql = "DELETE FROM BSCMPC WHERE Cmp=? ";
        String psName = "CMPC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSBS0CMP form, String Cmp,
            int seqCnt, String FstNme, String LstNme, String Title, String Tel,
            String Fax, String Mobile, String Email, String Remark)
            throws SQLException {

        String sql = "INSERT INTO BSCMPC (" +
                "Cmp, Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark) VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CMPC.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, seqCnt);
        form.cmd().db.setParameter(psName, i++, FstNme);
        form.cmd().db.setParameter(psName, i++, LstNme);
        form.cmd().db.setParameter(psName, i++, Title);
        form.cmd().db.setParameter(psName, i++, Tel);
        form.cmd().db.setParameter(psName, i++, Fax);
        form.cmd().db.setParameter(psName, i++, Mobile);
        form.cmd().db.setParameter(psName, i++, Email);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        CMPCH.createInsert(form, Cmp, seqCnt);
    }

    public static boolean select(BSBS0CMP form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMPC WHERE Cmp=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSBS0CMP form, String Cmp,
            int seqCnt, String FstNme, String LstNme, String Title, String Tel,
            String Fax, String Mobile, String Email, String Remark)
            throws SQLException {

        String sql = "UPDATE BSCMPC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, " +
                "Email=?, Remark=? " +
                "WHERE Cmp=? AND Seq=? ";
        String psName = "CMPC.U";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, FstNme);
        form.cmd().db.setParameter(psName, i++, LstNme);
        form.cmd().db.setParameter(psName, i++, Title);
        form.cmd().db.setParameter(psName, i++, Tel);
        form.cmd().db.setParameter(psName, i++, Fax);
        form.cmd().db.setParameter(psName, i++, Mobile);
        form.cmd().db.setParameter(psName, i++, Email);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, seqCnt);
        form.cmd().db.execUpdate(psName);
        CMPCH.createUpdate(form, Cmp, seqCnt);
    }
}
