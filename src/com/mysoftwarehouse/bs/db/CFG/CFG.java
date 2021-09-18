/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CFG;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBE0CFG;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CFG {

    public static void insert(BSBS0CMP form, String Cmp) throws SQLException {
        String sql = "INSERT INTO BSCFG (" +
                "Cmp, LstNumQtt, LstNumInv, LstNumRcp, LstNumPor, LstNumPiv, " +
                "NumDgtsQtt, NumDgtsInv, NumDgtsRcp, NumDgtsPor, NumDgtsPiv, " +
                "PrfxQtt, PrfxInv, PrfxRcp, PrfxPor, PrfxPiv, " +
                "DfltTrmQtt, DfltTrmInv, DfltTrmRcp, " +
                "PrtFmtQtt, PrtFmtInv, PrtFmtRcp, PrtFmtPor, PrtFmtPiv" +
                ") VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, " +
                "?, ?, ?, ?, ?)";
        String psName = "CFG.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        int DEFAULT_LST_NUM = 0;
        for (int j = 0; j < 5; j++) {
            form.cmd().db.setParameter(psName, i++, DEFAULT_LST_NUM);
        }
        int DEFAULT_NUM_DGTS = 6;
        for (int j = 0; j < 5; j++) {
            form.cmd().db.setParameter(psName, i++, DEFAULT_NUM_DGTS);
        }
        form.cmd().db.setParameter(psName, i++, "Q");
        form.cmd().db.setParameter(psName, i++, "I");
        form.cmd().db.setParameter(psName, i++, "R");
        form.cmd().db.setParameter(psName, i++, "P");
        form.cmd().db.setParameter(psName, i++, "V");
        String DEFAULT_TRM = null;
        for (int j = 0; j < 3; j++) {
            form.cmd().db.setParameter(psName, i++, DEFAULT_TRM);
        }
        String DEFAULT_PRTFMT = "T";
        for (int j = 0; j < 5; j++) {
            form.cmd().db.setParameter(psName, i++, DEFAULT_PRTFMT);
        }
        form.cmd().db.execUpdate(psName);
        CFGH.createInsert(form, Cmp);
    }

    public static boolean select(UserFormInterface form,
            String rsName, String cmp) throws SQLException {
        String sql = "SELECT * FROM BSCFG WHERE Cmp=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSBE0CFG form, String cmp,
            int LstNumQtt, int LstNumInv, int LstNumRcp, int LstNumPor, int LstNumPiv,
            int NumDgtsQtt, int NumDgtsInv, int NumDgtsRcp, int NumDgtsPor, int NumDgtsPiv,
            String PrfxQtt, String PrfxInv, String PrfxRcp, String PrfxPor, String PrfxPiv,
            String DfltTrmQtt, String DfltTrmInv, String DfltTrmRcp,
            String PrtFmtQtt, String PrtFmtInv, String PrtFmtRcp, String PrtFmtPor, String PrtFmtPiv)
            throws SQLException {

        String sql = "UPDATE BSCFG SET " +
                "LstNumQtt=?, LstNumInv=?, LstNumRcp=?, LstNumPor=?, LstNumPiv=?, " +
                "NumDgtsQtt=?, NumDgtsInv=?, NumDgtsRcp=?, NumDgtsPor=?, NumDgtsPiv=?, " +
                "PrfxQtt=?, PrfxInv=?, PrfxRcp=?, PrfxPor=?, PrfxPiv=?, " +
                "DfltTrmQtt=?, DfltTrmInv=?, DfltTrmRcp=?, " +
                "PrtFmtQtt=?, PrtFmtInv=?, PrtFmtRcp=?, PrtFmtPor=?, PrtFmtPiv=? " +
                "WHERE Cmp=? ";
        String psName = "CFG.U";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, LstNumQtt);
        form.cmd().db.setParameter(psName, i++, LstNumInv);
        form.cmd().db.setParameter(psName, i++, LstNumRcp);
        form.cmd().db.setParameter(psName, i++, LstNumPor);
        form.cmd().db.setParameter(psName, i++, LstNumPiv);
        form.cmd().db.setParameter(psName, i++, NumDgtsQtt);
        form.cmd().db.setParameter(psName, i++, NumDgtsInv);
        form.cmd().db.setParameter(psName, i++, NumDgtsRcp);
        form.cmd().db.setParameter(psName, i++, NumDgtsPor);
        form.cmd().db.setParameter(psName, i++, NumDgtsPiv);
        form.cmd().db.setParameter(psName, i++, PrfxQtt);
        form.cmd().db.setParameter(psName, i++, PrfxInv);
        form.cmd().db.setParameter(psName, i++, PrfxRcp);
        form.cmd().db.setParameter(psName, i++, PrfxPor);
        form.cmd().db.setParameter(psName, i++, PrfxPiv);
        form.cmd().db.setParameter(psName, i++, DfltTrmQtt);
        form.cmd().db.setParameter(psName, i++, DfltTrmInv);
        form.cmd().db.setParameter(psName, i++, DfltTrmRcp);
        form.cmd().db.setParameter(psName, i++, PrtFmtQtt);
        form.cmd().db.setParameter(psName, i++, PrtFmtInv);
        form.cmd().db.setParameter(psName, i++, PrtFmtRcp);
        form.cmd().db.setParameter(psName, i++, PrtFmtPor);
        form.cmd().db.setParameter(psName, i++, PrtFmtPiv);
        form.cmd().db.setParameter(psName, i++, cmp);
        form.cmd().db.execUpdate(psName);
        CFGH.createUpdate(form, cmp);

    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        CFGH.createDelete(form, Cmp);
        String sql = "DELETE FROM BSCFG WHERE Cmp=? ";
        String psName = "CFG.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }
}
