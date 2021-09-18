/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.J.BSJP0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPC {

    public static void copyFrmCus(BSJS0RCP form, String Cmp, String RcpNo, String Cus) throws SQLException {
        String sql = "INSERT INTO BSRCPC (" +
                "Cmp, RcpNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSCUSC A, BSCUS B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Cus=? " +
                "AND A.Cus=B.Cus " +
                "AND B.PriCnt=A.Seq ";
        String psName = "RCPC.cpCus";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmInv(BSJP0RCP form, String Cmp, String RcpNo, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSRCPC (" +
                "Cmp, RcpNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSINVC WHERE Cmp=? AND InvNo=? ";
        String psName = "RCPC.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmRcp(BSJS0RCP form, String Cmp, String ToRcpNo, String FrmRcpNo) throws SQLException {
        String sql = "INSERT INTO BSRCPC (" +
                "Cmp, RcpNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSRCPC " +
                "WHERE Cmp=? " +
                "AND RcpNo=? ";
        String psName = "RCPC.cpRcp";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToRcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmRcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCPC WHERE Cmp=? ";
        String psName = "RCPC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCPC WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSJS0RCP form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT * FROM BSRCPC WHERE Cmp=? AND RcpNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSJS0RCP form, String Cmp, String RcpNo,
            String FstNme, String LstNme, String Title, String Tel,
            String Fax, String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSRCPC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, Email=?, Remark=? " +
                "WHERE Cmp=? AND RcpNo=? ";
        String psName = "RCPC.U";

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
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.execUpdate(psName);
    }
}
