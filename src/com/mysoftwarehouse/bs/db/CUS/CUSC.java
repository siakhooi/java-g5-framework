/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CUS;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCS0CUS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CUSC {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSCUSC WHERE Cmp=? ";
        String psName = "CUSC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        //CUSCH.createDelete(form, Cmp, Cus);

        String sql = "DELETE FROM BSCUSC WHERE Cmp=? AND Cus=? ";
        String psName = "CUSC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSCS0CUS form, String Cmp, String Cus, int seqCnt,
            String FstNme, String LstNme, String Title, String Tel, String Fax,
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "INSERT INTO BSCUSC (" +
                "Cmp, Cus, Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark) VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CUSC.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
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

        CUSCH.createInsert(form, Cmp, Cus, seqCnt);
    }

    public static boolean select(BSCS0CUS form, String rsName,
            String Cmp, String Cus) throws SQLException {
        String sql = "SELECT * FROM BSCUSC WHERE Cmp=? AND Cus=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSCS0CUS form, String Cmp, String Cus, int seqCnt,
            String FstNme, String LstNme, String Title, String Tel, String Fax,
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSCUSC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, " +
                "Email=?, Remark=? " +
                "WHERE Cmp=? AND Cus=? AND Seq=? ";
        String psName = "CUSC.U";
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
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, seqCnt);
        form.cmd().db.execUpdate(psName);

        CUSCH.createUpdate(form, Cmp, Cus, seqCnt);
    }
}
