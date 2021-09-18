/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PORC {

    public static void copyFrmPor(BSNS0POR form, String Cmp, String ToPorNo, String FrmPorNo) throws SQLException {
        String sql = "INSERT INTO BSPORC (" +
                "Cmp, PorNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSPORC " +
                "WHERE Cmp=? " +
                "AND PorNo=? ";
        String psName = "PORC.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSup(BSNS0POR form, String Cmp, String PorNo, String Sup) throws SQLException {
        String sql = "INSERT INTO BSPORC (" +
                "Cmp, PorNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSSUPC A, BSSUP B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Sup=? " +
                "AND A.Sup=B.Sup " +
                "AND B.PriCnt=A.Seq ";
        String psName = "PORC.cpSup";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPORC WHERE Cmp=? ";
        String psName = "PORC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String sql = "DELETE FROM BSPORC WHERE Cmp=? AND PorNo=?";
        String psName = "PORC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSNS0POR form, String rsName, String Cmp, String PorNo) throws SQLException {
        String sql = "SELECT * FROM BSPORC WHERE Cmp=? AND PorNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSNS0POR form, String Cmp, String PorNo,
            String FstNme, String LstNme, String Title, String Tel,
            String Fax, String Mobile, String Email, String Remark) throws SQLException {

        String sql = "UPDATE BSPORC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, Email=?, Remark=? " +
                "WHERE Cmp=? AND PorNo=? ";
        String psName = "PORC.U";

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
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }
}
