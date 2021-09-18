/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TXN;

import com.mysoftwarehouse.is.G.ISGE_TXN;
import com.mysoftwarehouse.is.conf.APP;
import com.mysoftwarehouse.is.data.CstEnum;
import com.mysoftwarehouse.is.data.CstEnum.CstTyp;
import com.mysoftwarehouse.is.data.TcdEnum;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;
import com.mysoftwarehouse.is.data.TcdEnum.TxnTyp;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.ITM.ITMB;
import com.mysoftwarehouse.is.db.WHS.WHS;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN_C {

    public static void create(ISGE_TXN form, String Typ, String Whs, String Tcd,
            String Itm, String FrmLoc, String ToLoc,
            BigDecimal TxnQty, BigDecimal TxnCst, String Remark) throws SQLException {
//  get csttyp
        String rsName = "TXN_C.Whs";
        WHS.select(form, rsName, Whs);
        String CstTyp = form.cmd().db.getString(rsName, "CstTyp");

        CstEnum.CstTyp CstTyp1 = CstEnum.CstTyp.getType(CstTyp);
        TcdEnum.TxnTyp TxnTyp1 = TcdEnum.TxnTyp.getType(Typ);

//  get stdcst, waccst
        rsName = "TXN_C.Itm";
        ITM.select(form, rsName, Whs, Itm);
        BigDecimal StdCst = form.cmd().db.getBigDecimal(rsName, "StdCst");
        BigDecimal WacCst = form.cmd().db.getBigDecimal(rsName, "WacCst");
        BigDecimal Qty = form.cmd().db.getBigDecimal(rsName, "Qty");

        //calculate TxnDte
        Calendar TxnDte = form.cmd().cal.getNow();

        //calculate txncst
        BigDecimal TxnCst1 = calcTxnCst(TxnTyp1, CstTyp1, StdCst, WacCst, TxnCst);

        //calculate new waccst  
        BigDecimal NewWacCst = calcNewWacCst(TxnTyp1, WacCst, Qty, TxnCst, TxnQty);

        // calculate new qty
        BigDecimal NewQty = calcNewQty(TxnTyp1, Qty, TxnQty);

        //calculate new cost
        BigDecimal NewCst = calcNewCst(CstTyp1, StdCst, NewWacCst);

        int Seq = genLstSeq(form, Whs);

        //insert txn
        TXN.insert(form, Whs, Seq, TxnDte, Tcd, Itm, FrmLoc, ToLoc,
                TxnQty, TxnCst1, Qty, NewQty, NewCst, Remark);

        checkITMBRecords(form, TxnTyp1, Whs, Itm, FrmLoc, ToLoc);
        //        insert txna
        addTXNARecords(form, TxnTyp1, Whs, Seq);

        //        update itmb * 2
        updateITMBRecords(form, TxnTyp1, Whs, Seq, Itm, FrmLoc, ToLoc, TxnQty);

        //        update itm & itmh
        ITM.updateTxn(form, Whs, Itm, NewQty, NewWacCst);
    }

    private static void checkITMBRecords(ISGE_TXN form, TxnTyp TxnTyp,
            String Whs, String Itm, String FrmLoc, String ToLoc) throws SQLException {
        switch (TxnTyp) {
            case A:
                ITMB.check(form, Whs, Itm, ToLoc);
                break;
            case I:
                ITMB.check(form, Whs, Itm, ToLoc);
                break;
            case O:
                ITMB.check(form, Whs, Itm, FrmLoc);
                break;
            case X:
                ITMB.check(form, Whs, Itm, FrmLoc);
                ITMB.check(form, Whs, Itm, ToLoc);
                break;
        }
    }

    private static void updateITMBRecords(ISGE_TXN form,
            TxnTyp TxnTyp, String Whs, int Seq, String Itm,
            String FrmLoc, String ToLoc, BigDecimal TxnQty) throws SQLException {
        switch (TxnTyp) {
            case A:
                ITMB.updateIn(form, Whs, Itm, ToLoc, TxnQty);
                break;
            case I:
                ITMB.updateIn(form, Whs, Itm, ToLoc, TxnQty);
                break;
            case O:
                ITMB.updateOut(form, Whs, Itm, FrmLoc, TxnQty);
                break;
            case X:
                ITMB.updateIn(form, Whs, Itm, ToLoc, TxnQty);
                ITMB.updateOut(form, Whs, Itm, FrmLoc, TxnQty);
                break;
        }
    }

    private static void addTXNARecords(ISGE_TXN form, TxnTyp TxnTyp,
            String Whs, int Seq) throws SQLException {
        switch (TxnTyp) {
            case I:
                TXNA.insertIn(form, Whs, Seq, TxnTyp);
                break;
            case O:
                TXNA.insertOut(form, Whs, Seq, TxnTyp);
                break;
            case A:
                TXNA.insertIn(form, Whs, Seq, TxnTyp);
                break;
            case X:
                TXNA.insertIn(form, Whs, Seq, TxnTyp);
                TXNA.insertOut(form, Whs, Seq, TxnTyp);
                break;
        }
    }

    private static BigDecimal calcNewCst(CstTyp CstTyp, BigDecimal StdCst, BigDecimal NewWacCst) {
        switch (CstTyp) {
            case S:
                return StdCst;
            case W:
                return NewWacCst;
        }
        return null;
    }

    private static BigDecimal calcNewQty(TxnTyp TxnTyp, BigDecimal Qty, BigDecimal TxnQty) {
        switch (TxnTyp) {
            case A:
                return Qty.add(TxnQty);
            case I:
                return Qty.add(TxnQty);
            case O:
                return Qty.subtract(TxnQty);
            case X:
                return Qty;
        }
        return null;
    }

    private static BigDecimal calcNewWacCst(TxnTyp TxnTyp,
            BigDecimal WacCst, BigDecimal Qty,
            BigDecimal TxnCst, BigDecimal TxnQty) {
        switch (TxnTyp) {
            case I:
                BigDecimal newQty = Qty.add(TxnQty);
                if (newQty.compareTo(BigDecimal.ZERO) < 1) {
                    return TxnCst;
                }
                if (Qty.compareTo(BigDecimal.ZERO) < 1) {
                    return TxnCst;
                }
                BigDecimal v = Qty.multiply(WacCst).add(TxnQty.multiply(TxnCst));
                return v.divide(newQty, APP.ISTXN_WACCST_DECIMAL, RoundingMode.HALF_UP);
            case A:
            case O:
            case X:
        }
        return WacCst; //unchanged
    }

    private static BigDecimal calcTxnCst(TxnTyp TxnTyp, CstTyp CstTyp,
            BigDecimal StdCst, BigDecimal WacCst, BigDecimal TxnCst) {
        switch (CstTyp) {
            case S:
                switch (TxnTyp) {
                    case A:
                        return StdCst;
                    case I:
                        return TxnCst;
                    case O:
                        return StdCst;
                    case X:
                        return StdCst;
                }
                break;
            case W:
                switch (TxnTyp) {
                    case A:
                        return WacCst;
                    case I:
                        return TxnCst;
                    case O:
                        return WacCst;
                    case X:
                        return WacCst;
                }
        }
        return TxnCst;
    }

    private static int genLstSeq(ISGE_TXN form, String Whs) throws SQLException {
        String sql = "UPDATE ISWHS SET " +
                "LstTxnSeq=NEXT VALUE FOR ISWHSLSTTXNSEQ " +
                "WHERE Whs=?";
        String psName = "TXN_C.WHS";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);

        String rsName = "TXN_C.WHS2";
        WHS.select(form, rsName, Whs);
        return form.cmd().db.getInteger(rsName, "LstTxnSeq");
    }
}
