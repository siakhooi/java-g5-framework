/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.util;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class Sql2Vector {

    public static void add(UserFormInterface form, Vector<String> allSql,
            InputStream resourceAsStream) throws IOException {
        form.cmd().lineread.init(resourceAsStream);
        String line = "";
        String currentSQL = "";
        line = form.cmd().lineread.readLine();
        while (line != null) {
            line = line.trim();
            if (line.length() == 0) {
                allSql.add(currentSQL);
                currentSQL = "";
            } else if (line.startsWith("--")) {
            //skip comment line
            } else {
                currentSQL += " ";
                currentSQL += line;
            }
            line = form.cmd().lineread.readLine();
        }
        if (currentSQL.length() > 0) {
            allSql.add(currentSQL);
        }
    }
}
