/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PORA {

    public static void copyFrmPor(BSNS0POR form, String Cmp, String ToPorNo, String FrmPorNo) throws SQLException {
        String sql = "INSERT INTO BSPORA (" +
                "Cmp, PorNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSPORA " +
                "WHERE Cmp=? " +
                "AND PorNo=? ";
        String psName = "PORA.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSup(BSNS0POR form, String Cmp, String PorNo, String Sup) throws SQLException {
        String sql = "INSERT INTO BSPORA (" +
                "Cmp, PorNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSSUPA A, BSSUP B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Sup=? " +
                "AND A.Sup=B.Sup " +
                "AND B.PriAdd=A.Seq ";
        String psName = "PORA.cpSup";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPORA WHERE Cmp=? ";
        String psName = "PORA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String sql = "DELETE FROM BSPORA WHERE Cmp=? AND PorNo=?";
        String psName = "PORA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSNS0POR form, String rsName, String Cmp, String PorNo) throws SQLException {
        String sql = "SELECT * FROM BSPORA WHERE Cmp=? AND PorNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSNS0POR form, String Cmp, String PorNo,
            String Add1, String Add2, String City, String ZipCde,
            String Stte, String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "UPDATE BSPORA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, Remark=? " +
                "WHERE Cmp=? AND PorNo=? ";
        String psName = "PORA.U";

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
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }
}
