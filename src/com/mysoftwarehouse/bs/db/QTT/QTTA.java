/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTA {

    public static void copyFrmCus(BSHS0QTT form, String Cmp, String QttNo, String Cus) throws SQLException {
        String sql = "INSERT INTO BSQTTA (" +
                "Cmp, QttNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSCUSA A, BSCUS B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Cus=? " +
                "AND A.Cus=B.Cus " +
                "AND B.PriAdd=A.Seq ";
        String psName = "QTTA.cpCus";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTA (" +
                "Cmp, QttNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSQTTA " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTA.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTA WHERE Cmp=? ";
        String psName = "QTTA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTA WHERE Cmp=? AND QttNo=?";
        String psName = "QTTA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSHS0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTTA WHERE Cmp=? AND QttNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSHS0QTT form, String Cmp, String QttNo,
            String Add1, String Add2, String City, String ZipCde, String Stte,
            String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "UPDATE BSQTTA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, Remark=? " +
                "WHERE Cmp=? AND QttNo=? ";
        String psName = "QTTA.U";

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
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }
}
