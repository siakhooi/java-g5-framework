/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.O.BSOP0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIVA {

    public static void copyFrmPiv(BSOS0PIV form, String Cmp, String ToPivNo, String FrmPivNo) throws SQLException {
        String sql = "INSERT INTO BSPIVA (" +
                "Cmp, PivNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSPIVA " +
                "WHERE Cmp=? " +
                "AND PivNo=? ";
        String psName = "PIVA.cpPiv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSup(BSOS0PIV form, String Cmp, String PivNo, String Sup) throws SQLException {
        String sql = "INSERT INTO BSPIVA (" +
                "Cmp, PivNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSSUPA A, BSSUP B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Sup=? " +
                "AND A.Sup=B.Sup " +
                "AND B.PriAdd=A.Seq ";
        String psName = "PIVA.cpSup";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPor(BSOP0PIV form, String Cmp, String PivNo, String PorNo) throws SQLException {
        String sql = "INSERT INTO BSPIVA (" +
                "Cmp, PivNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSPORA WHERE Cmp=? AND PorNo=? ";

        String psName = "PIVA.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIVA WHERE Cmp=? ";
        String psName = "PIVA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo) throws SQLException {
        String sql = "DELETE FROM BSPIVA WHERE Cmp=? AND PivNo=?";
        String psName = "PIVA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSOS0PIV form, String rsName, String Cmp, String PivNo) throws SQLException {
        String sql = "SELECT * FROM BSPIVA WHERE Cmp=? AND PivNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSOS0PIV form, String Cmp, String PivNo,
            String Add1, String Add2, String City, String ZipCde, String Stte,
            String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "UPDATE BSPIVA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, Remark=? " +
                "WHERE Cmp=? AND PivNo=? ";
        String psName = "PIVA.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Add1);
        form.cmd().db.setParameter(psName, i++, Add2);
        form.cmd().db.setParameter(psName, i++, City);
        form.cmd().db.setParameter(psName, i++, ZipCde);
        form.cmd().db.setParameter(psName, i++, Stte);
        form.cmd().db.setParameter(psName, i++, Ctry);
        form.cmd().db.setParameter(psName, i++, Tel);
        form.cmd().db.setParameter(psName, i++, Fax);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.execUpdate(psName);
    }
}
