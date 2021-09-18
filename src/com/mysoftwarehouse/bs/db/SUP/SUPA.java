/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SUP;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.K.BSKP0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKS0SUP;
import com.mysoftwarehouse.bs.db.CUS.*;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SUPA {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSUPA WHERE Cmp=? ";
        String psName = "SUPA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSKP0SUP form, String Cmp, String Sup) throws SQLException {
        //SUPAH.createDelete(form, Cmp, Cus);

        String sql = "DELETE FROM BSSUPA WHERE Cmp=? AND Sup=? ";
        String psName = "SUPA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSKS0SUP form, String rsName,
            String Cmp, String Sup) throws SQLException {
        String sql = "SELECT * FROM BSSUPA WHERE Cmp=? AND Sup=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSKS0SUP form, String Cmp, String Sup, int SeqAdd,
            String Add1, String Add2, String City, String ZipCde, String Stte,
            String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "INSERT INTO BSSUPA (" +
                "Cmp, Sup, Seq, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, " +
                "Fax, Remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "SUPA.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, SeqAdd);
        form.cmd().db.setParameter(psName, i++, Add1);
        form.cmd().db.setParameter(psName, i++, Add2);
        form.cmd().db.setParameter(psName, i++, City);
        form.cmd().db.setParameter(psName, i++, ZipCde);
        form.cmd().db.setParameter(psName, i++, Stte);
        form.cmd().db.setParameter(psName, i++, Ctry);
        form.cmd().db.setParameter(psName, i++, Tel);
        form.cmd().db.setParameter(psName, i++, Fax);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        SUPAH.createInsert(form, Cmp, Sup, SeqAdd);
    }

    public static void update(BSKS0SUP form, String Cmp, String Sup, int SeqAdd,
            String Add1, String Add2, String City, String ZipCde, String Stte,
            String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "UPDATE BSSUPA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, " +
                "Remark=? WHERE Cmp=? AND Sup=? AND Seq=? ";
        String psName = "SUPA.U";
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
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, SeqAdd);
        form.cmd().db.execUpdate(psName);

        SUPAH.createUpdate(form, Cmp, Sup, SeqAdd);
    }
}
