/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCY;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCY2 {

    public static void adjust(
            UserFormInterface form,
            String id, String acc, BigDecimal amt,
            BigDecimal dbt, BigDecimal crt, int txnCntAdjustment, int y) 
            throws SQLException {
        ACCY1.prepare(form, id, acc, y);
        String psName = "ACCY2";
        String sql = "UPDATE PFACCY SET " +
                "TxnCnt=TxnCnt+?, " +
                "TtlDbt=TtlDbt+?, " +
                "TtlCrt=TtlCrt+?, " +
                "TtlAmt=TtlAmt+? WHERE Id=? AND Acc=? AND Year=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, txnCntAdjustment);
        form.cmd().db.setParameter(psName, i++, dbt);
        form.cmd().db.setParameter(psName, i++, crt);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.execUpdate(psName);
    }
}
