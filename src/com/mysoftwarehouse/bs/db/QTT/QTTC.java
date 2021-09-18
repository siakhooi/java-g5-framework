/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTC {

    public static void copyFrmCus(BSHS0QTT form, String Cmp, String QttNo, String Cus) throws SQLException {
        String sql = "INSERT INTO BSQTTC (" +
                "Cmp, QttNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSCUSC A, BSCUS B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND Cus=? " +
                "AND A.Cus=B.Cus " +
                "AND B.PriCnt=A.Seq ";
        String psName = "QTTC.cpCus";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTC (" +
                "Cmp, QttNo, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark) " +
                "SELECT Cmp, ?, FstNme, LstNme, Title, Tel, Fax, Mobile, Email, Remark " +
                "FROM BSQTTC " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTC.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTC WHERE Cmp=? ";
        String psName = "QTTC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTC WHERE Cmp=? AND QttNo=?";
        String psName = "QTTC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSHS0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTTC WHERE Cmp=? AND QttNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSHS0QTT form, String Cmp, String QttNo,
            String FstNme, String LstNme, String Title, String Tel, String Fax,
            String Mobile, String Email, String Remark) throws SQLException {
        String sql = "UPDATE BSQTTC SET " +
                "FstNme=?, LstNme=?, Title=?, Tel=?, Fax=?, Mobile=?, Email=?, Remark=? " +
                "WHERE Cmp=? AND QttNo=? ";
        String psName = "QTTC.U";

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
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }
}
