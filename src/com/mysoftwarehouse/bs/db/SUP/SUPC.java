/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SUP;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.K.BSKP0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKS0SUP;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SUPC {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSUPC WHERE Cmp=? ";
        String psName = "SUPC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSKP0SUP form, String Cmp, String Sup) throws SQLException {
        //CUSCH.createDelete(form, Cmp, Cus);

        String sql = "DELETE FROM BSSUPC WHERE Cmp=? AND Sup=? ";
        String psName = "SUPC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSKS0SUP form, String rsName,
            String Cmp, String Sup) throws SQLException {
        String sql = "SELECT * FROM BSSUPC WHERE Cmp=? AND Sup=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSKS0SUP form, String Cmp, String Sup, int seqCnt,
            String FstNme, String LstNme, String Title, String Tel, String Fax,
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "INSERT INTO BSSUPC (" +
                "Cmp, Sup, Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark) VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "SUPC.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
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

        SUPCH.createInsert(form, Cmp, Sup, seqCnt);
    }

    public static void update(BSKS0SUP form, String Cmp, String Sup, int seqCnt, 
            String FstNme, String LstNme, String Title, String Tel, String Fax, 
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSSUPC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, " +
                "Email=?, Remark=? " +
                "WHERE Cmp=? AND Sup=? AND Seq=? ";
        String psName = "SUPC.U";
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
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, seqCnt);
        form.cmd().db.execUpdate(psName);

        SUPCH.createUpdate(form, Cmp, Sup, seqCnt);
    }
}
