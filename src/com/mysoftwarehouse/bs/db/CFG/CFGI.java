/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CFG;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBE0CFGI;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CFGI {

    public static void delete(UserFormInterface form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSCFGI WHERE Cmp=? ";
        String psName = "CFGI.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSBE0CFGI form, String Cmp, byte[] LetterHead) throws SQLException {
        delete(form, Cmp);
//        String sql = "DELETE FROM BSCFGI WHERE Cmp=? ";
//        String psName = "CFGI.D";
//        form.cmd().db.setStatement(psName, sql);
//        int i = 1;
//        form.cmd().db.setParameter(psName, i++, Cmp);
//        form.cmd().db.execUpdate(psName);
        String sql = "INSERT INTO BSCFGI (Cmp, LetterHead) VALUES(?, ?) ";
        String psName = "CFGI.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.getPreparedStatement(psName).setBytes(i++, LetterHead);
        form.cmd().db.execUpdate(psName);
    }

    public static InputStream getLetterHead(UserFormInterface form, String Cmp) {
        try {
            String sql = "SELECT * FROM BSCFGI WHERE Cmp=? ";
            String psName = "CFGI.S";
            form.cmd().db.setStatement(psName, sql);
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.execQuery(psName, psName);
            if (form.cmd().db.next(psName)) {
                byte[] img = form.cmd().db.getResultSet(psName).getBytes("LetterHead");
                if (img == null) {
                    return null;
                }
                return new ByteArrayInputStream(img);
            }
        } catch (SQLException ex) {
            form.cmd().log.severe("CFGI.error", ex);
        }
        return null;
    }
}
