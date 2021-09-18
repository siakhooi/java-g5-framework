/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.RESOURCE;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP0DB101 extends ProcessForm {

    private Vector<String> allSql;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSAP0DB101.LoadScript", 1);
        addProcess("BSAP0DB101.CreateDatabase", 9);
    }

    @Override
    public void run() throws ProcessException {
        cmd.in.booleanValue = false;
        loadScript();
        super.completed();
        createDatabase();
        cmd.in.booleanValue = true;
        super.completed();
    }

    @Override
    public void processCancel() throws ProcessException {
    }

    private void loadScript() throws ProcessException {
        allSql = new Vector<String>();
        String files[] = RESOURCE.DATABASE_101_FILES;
        for (String f : files) {
            loadScript(f);
        }
    }

    private void loadScript(String path) throws ProcessException {
        try {
            cmd.lineread.init(BSAP0DB101.class.getResourceAsStream(path));
            String line = "";
            String currentSQL = "";
            line = cmd.lineread.readLine();
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
                line = cmd.lineread.readLine();
            }
            if (currentSQL.length() > 0) {
                allSql.add(currentSQL);
            }
        } catch (IOException ex) {
            cmd.log.severe("BSAP0DB101.error", ex);
            String msg = "BSAP0DB101.error.loadscript.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    private void createDatabase() throws ProcessException {
        try {
            super.setMinorTotalCount(allSql.size());
            String psName = "BSAP0DB101";
            cmd.db.setAutoCommit(false);
            for (String sql : allSql) {
                cmd.db.setStatement(psName, sql);
                cmd.db.execUpdate(psName);
                super.minorCompleted();
            }
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.log.severe("BSAP0DB101.error", ex);
            String msg = "BSAP0DB101.error.createdb.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            //cmd.db.rollback();
            //no need as app will exit?
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSAP0DB101.title";
    }
}
