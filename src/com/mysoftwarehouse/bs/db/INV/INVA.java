/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVA {

    public static void copyFrmCus(BSIS0INV form, String Cmp, String InvNo, String Cus) throws SQLException {
        String sql = "INSERT INTO BSINVA (" +
                "Cmp, InvNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSCUSA A, BSCUS B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Cus=? " +
                "AND A.Cus=B.Cus " +
                "AND B.PriAdd=A.Seq ";
        String psName = "INVA.cpCus";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVA (" +
                "Cmp, InvNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSINVA " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVA.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINVA (" +
                "Cmp, InvNo, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark) " +
                "SELECT Cmp, ?, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, Fax, Remark " +
                "FROM BSQTTA WHERE Cmp=? AND QttNo=? ";

        String psName = "INVA.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVA WHERE Cmp=? ";
        String psName = "INVA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVA WHERE Cmp=? AND InvNo=?";
        String psName = "INVA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSIS0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINVA WHERE Cmp=? AND InvNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSIS0INV form, String Cmp, String InvNo,
            String Add1, String Add2, String City, String ZipCde,
            String Stte, String Ctry, String Tel, String Fax, String Remark)
            throws SQLException {
        String sql = "UPDATE BSINVA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, Remark=? " +
                "WHERE Cmp=? AND InvNo=? ";
        String psName = "INVA.U";

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
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }
}
