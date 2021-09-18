/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPA {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        //CMPAH.createDelete(form, Cmp);
        String sql = "DELETE FROM BSCMPA WHERE Cmp=? ";
        String psName = "CMPA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSBS0CMP form, String Cmp,
            int SeqAdd, String Add1, String Add2, String City,
            String ZipCde, String Stte, String Ctry, String Tel,
            String Fax, String Remark) throws SQLException {
        String sql = "INSERT INTO BSCMPA (" +
                "Cmp, Seq, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, " +
                "Fax, Remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CMPA.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
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

        CMPAH.createInsert(form, Cmp, SeqAdd);
    }

    public static boolean select(BSBS0CMP form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMPA WHERE Cmp=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSBS0CMP form, String Cmp,
            int SeqAdd, String Add1, String Add2, String City,
            String ZipCde, String Stte, String Ctry, String Tel,
            String Fax, String Remark) throws SQLException {

        String sql = "UPDATE BSCMPA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, " +
                "Fax=?, Remark=? WHERE Cmp=? AND Seq=? ";
        String psName = "CMPA.U";
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
        form.cmd().db.setParameter(psName, i++, SeqAdd);
        form.cmd().db.execUpdate(psName);
        CMPAH.createUpdate(form, Cmp, SeqAdd);
    }
}
