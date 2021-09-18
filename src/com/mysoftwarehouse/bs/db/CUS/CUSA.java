/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CUS;

import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCS0CUS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CUSA {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSCUSA WHERE Cmp=? ";
        String psName = "CUSA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        //CUSAH.createDelete(form, Cmp, Cus);

        String sql = "DELETE FROM BSCUSA WHERE Cmp=? AND Cus=? ";
        String psName = "CUSA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSCS0CUS form, String Cmp, String Cus,
            int SeqAdd, String Add1, String Add2, String City, String ZipCde,
            String Stte, String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "INSERT INTO BSCUSA (" +
                "Cmp, Cus, Seq, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, " +
                "Fax, Remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CUSA.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
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

        CUSAH.createInsert(form, Cmp, Cus, SeqAdd);
    }

    public static boolean select(BSCS0CUS form, String rsName,
            String Cmp, String Cus) throws SQLException {
        String sql = "SELECT * FROM BSCUSA WHERE Cmp=? AND Cus=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSCS0CUS form, String Cmp, String Cus,
            int SeqAdd, String Add1, String Add2, String City, String ZipCde,
            String Stte, String Ctry, String Tel, String Fax, String Remark) throws SQLException {
        String sql = "UPDATE BSCUSA SET " +
                "Add1=?, Add2=?, City=?, ZipCde=?, Stte=?, Ctry=?, Tel=?, Fax=?, " +
                "Remark=? WHERE Cmp=? AND Cus=? AND Seq=? ";
        String psName = "CUSA.U";
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
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, SeqAdd);
        form.cmd().db.execUpdate(psName);

        CUSAH.createUpdate(form, Cmp, Cus, SeqAdd);
    }
}
