/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVC {

    public static void copyFrmCus(BSIS0INV form, String Cmp, String InvNo, String Cus) throws SQLException {
        String sql = "INSERT INTO BSINVC (" +
                "Cmp, InvNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSCUSC A, BSCUS B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Cus=? " +
                "AND A.Cus=B.Cus " +
                "AND B.PriCnt=A.Seq ";
        String psName = "INVC.cpCus";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVC (" +
                "Cmp, InvNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSINVC " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVC.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINVC (" +
                "Cmp, InvNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSQTTC WHERE Cmp=? AND QttNo=? ";
        String psName = "INVC.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVC WHERE Cmp=? ";
        String psName = "INVC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVC WHERE Cmp=? AND InvNo=?";
        String psName = "INVC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSIS0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINVC WHERE Cmp=? AND InvNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSIS0INV form, String Cmp, String InvNo,
            String FstNme, String LstNme, String Title, String Tel,
            String Fax, String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSINVC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, Email=?, Remark=? " +
                "WHERE Cmp=? AND InvNo=? ";
        String psName = "INVC.U";

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
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }
}
