/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m80;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class GenerateDataProcessForm extends ProcessForm {

    @Override
    public void init() {
        super.addI18nProcess("Add Member Table", 1);
        super.addI18nProcess("Add Member Data", 20);
    }

    @Override
    public void run() throws ProcessException {
        try {
            addMemberTable();
            completed();
            addMemberData();
            completed();
        } catch (SQLException ex) {
            cmd.debug.error("Error Create Data", ex);
            cmd.log.log(Level.SEVERE, "Error Create Data", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "GenerateData.title";
    }

    private void addMemberData() throws ProcessException, SQLException {
        int n = 150;

        super.setMinorTotalCount(n);
        String sql = "INSERT INTO member(" +
                "member, firstname, lastname," +
                "pass, regdate, favcolor, " +
                "pwd, age, amount, sex, membertype) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "add";
        cmd.db.begin();
        cmd.db.setStatement(psName, sql);
        for (int i = 0; i < n; i++) {
            int m = 1;
            cmd.db.setParameter(psName, m++, "0000" + i);
            cmd.db.setParameter(psName, m++, "SH-" + i);
            cmd.db.setParameter(psName, m++, "NG-" + i);
            cmd.db.setParameter(psName, m++, "Y");
            cmd.db.setParameter(psName, m++, cmd.cal.getNow());
            cmd.db.setParameter(psName, m++,
                    cmd.data.Color2long(new Color(
                    cmd.random.getInt(0, 255),
                    cmd.random.getInt(0, 255),
                    cmd.random.getInt(0, 255))));
            cmd.db.setParameter(psName, m++, "12345678");
            cmd.db.setParameter(psName, m++, 300);
            cmd.db.setParameter(psName, m++, 45.91);
            cmd.db.setParameter(psName, m++,
                    cmd.data.selectText(i % 2, "M", "F"));
            cmd.db.setParameter(psName, m++,
                    cmd.data.selectText(i % 6, "A", "B", "C",
                    "D", "E", "F"));
            cmd.db.execUpdate(psName);
            super.minorCompleted();
        }
        cmd.db.commit();
    }

    private void addMemberTable() throws SQLException {
        String sql;
        sql = "DROP TABLE member IF EXISTS";
        cmd.db.setStatement("dropTable", sql);
        cmd.db.execUpdate("dropTable");
        sql = "create cached table member( " +
                "member CHAR(10) not null, " +
                "firstname varchar(30), " +
                "lastname varchar(30)," +
                "pass char(1)," +
                "regdate DATETIME," +
                "favcolor BIGINT," +
                "pwd varchar(30)," +
                "age int," +
                "amount decimal(10, 4)," +
                "sex CHAR(1)," +
                "membertype CHAR(1)," +
                "primary key(member))";
        cmd.db.setStatement("createTable", sql);
        cmd.db.execUpdate("createTable");
    }
}
