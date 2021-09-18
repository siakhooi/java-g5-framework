/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TXN;

import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.C.ISCP0ITM;
import com.mysoftwarehouse.is.E.ISEP0LOC;
import com.mysoftwarehouse.is.G.ISGE_TXN;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNA {

    static void insertIn(ISGE_TXN form, String Whs, int Seq, TxnTyp TxnTyp) throws SQLException {
        String sql = "INSERT INTO ISTXNA ( " +
                "Whs, Seq, TxnDte, TxnTme, Tcd, Typ, Itm, Loc, TxnQty, OpnBal, NewBal) " +
                "SELECT A.Whs, A.Seq, A.TxnDte, A.TxnTme, A.Tcd, ?, A.Itm, " +
                "A.ToLoc, A.TxnQty, B.Qty, B.Qty+A.TxnQty " +
                "FROM ISTXN A, ISITMB B " +
                "WHERE A.Whs=? AND A.Seq=? " +
                "AND A.Whs=B.Whs " +
                "AND A.Itm=B.Itm " +
                "AND A.ToLoc=B.Loc ";
        String psName = "TXNA.II";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, TxnTyp.typ);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }

    static void insertOut(ISGE_TXN form, String Whs, int Seq, TxnTyp TxnTyp) throws SQLException {
        String sql = "INSERT INTO ISTXNA ( " +
                "Whs, Seq, TxnDte, TxnTme, Tcd, Typ, Itm, Loc, TxnQty, OpnBal, NewBal) " +
                "SELECT A.Whs, A.Seq, A.TxnDte, A.TxnTme, A.Tcd, ?, A.Itm, " +
                "A.FrmLoc, -A.TxnQty, B.Qty, B.Qty-A.TxnQty FROM " +
                "ISTXN A, ISITMB B " +
                "WHERE A.Whs=? AND A.Seq=? " +
                "AND A.Whs=B.Whs " +
                "AND A.Itm=B.Itm " +
                "AND A.FrmLoc=B.Loc ";
        String psName = "TXNA.II";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, TxnTyp.typ);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }

//    public static void insert(UserFormInterface form, String Whs, 
//            int Seq, Calendar TxnDte, String Tcd, String Typ, String Itm,
//            String Loc, BigDecimal TxnQty) throws SQLException{
//        String sql = "INSERT INTO ISTXNA ( " +
//                "Whs, Seq, TxnDte, Tcd, Typ, Itm, Loc, TxnQty) " +
//                "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
//        String psName = "TXNA.I";
//        form.cmd().db.setStatement(psName, sql);
//        int i = 1;
//        form.cmd().db.setParameter(psName, i++, Whs);
//        form.cmd().db.setParameter(psName, i++, Seq);
//        form.cmd().db.setParameter(psName, i++, TxnDte);
//        form.cmd().db.setParameter(psName, i++, Tcd);
//        form.cmd().db.setParameter(psName, i++, Typ);
//        form.cmd().db.setParameter(psName, i++, Itm);
//        form.cmd().db.setParameter(psName, i++, Loc);
//        form.cmd().db.setParameter(psName, i++, TxnQty);
//        form.cmd().db.execUpdate(psName);                
//    }
    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISTXNA WHERE Whs=? ";
        String psName = "TXNA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useITM(ISCP0ITM form, String Whs, String Itm) throws SQLException {
        String rsName = "TXNA.useItm";
        String sql = "SELECT * FROM ISTXNA WHERE Whs=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean useLoc(ISEP0LOC form, String Whs, String Loc) throws SQLException {
        String rsName = "TXNA.useLoc";
        String sql = "SELECT * FROM ISTXNA WHERE Whs=? AND Loc=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Loc);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
