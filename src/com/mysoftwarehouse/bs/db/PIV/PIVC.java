/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.O.BSOP0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIV;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIVC {

    public static void copyFrmPiv(BSOS0PIV form, String Cmp, String ToPivNo, String FrmPivNo) throws SQLException {
        String sql = "INSERT INTO BSPIVC (" +
                "Cmp, PivNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSPIVC " +
                "WHERE Cmp=? " +
                "AND PivNo=? ";
        String psName = "PIVC.cpPiv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSup(BSOS0PIV form, String Cmp, String PivNo, String Sup) throws SQLException {
        String sql = "INSERT INTO BSPIVC (" +
                "Cmp, PivNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSSUPC A, BSSUP B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Sup=? " +
                "AND A.Sup=B.Sup " +
                "AND B.PriCnt=A.Seq ";
        String psName = "PIVC.cpSup";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPor(BSOP0PIV form, String Cmp, String PivNo, String PorNo) throws SQLException {
        String sql = "INSERT INTO BSPIVC (" +
                "Cmp, PivNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSPORC WHERE Cmp=? AND PorNo=? ";
        String psName = "PIVC.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIVC WHERE Cmp=? ";
        String psName = "PIVC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo) throws SQLException {
        String sql = "DELETE FROM BSPIVC WHERE Cmp=? AND PivNo=?";
        String psName = "PIVC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSOS0PIV form, String rsName, String Cmp, String PivNo) throws SQLException {
        String sql = "SELECT * FROM BSPIVC WHERE Cmp=? AND PivNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSOS0PIV form, String Cmp, String PivNo,
            String FstNme, String LstNme, String Title, String Tel, String Fax,
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSPIVC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, Email=?, Remark=? " +
                "WHERE Cmp=? AND PivNo=? ";
        String psName = "PIVC.U";

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
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.execUpdate(psName);
    }
}
