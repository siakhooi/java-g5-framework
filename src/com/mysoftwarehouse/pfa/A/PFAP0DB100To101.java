/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import com.mysoftwarehouse.pfa.util.Sql2Vector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAP0DB100To101 extends ProcessForm {

    private Vector<String> allSql;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFAP0DB100To101.LoadScript", 1);
        addProcess("PFAP0DB100To101.CreateDatabase", 9);
    }

    @Override
    public void run() throws ProcessException {
        loadScript();
        super.completed();
        createDatabase();
        cmd.in.booleanValue=true;
        super.completed();
    }

    private void loadScript() throws ProcessException {
        try {
            allSql = new Vector<String>();
            Sql2Vector.add(this, allSql, 
                    PFAP0DB100To101.class.getResourceAsStream(RESOURCE.DATABASE_101_FILE));
//            allSql = new Vector<String>();
//            cmd.lineread.init(PFAP0DB100To101.class.getResourceAsStream(RESOURCE.DATABASE_100_FILE));
//            String line = "";
//            String currentSQL = "";
//            line = cmd.lineread.readLine();
//            while (line != null) {
//                line = line.trim();
//                if (line.length() == 0) {
//                    allSql.add(currentSQL);
//                    currentSQL = "";
//                } else if (line.startsWith("--")) {
//                //skip comment line
//                } else {
//                    currentSQL += " ";
//                    currentSQL += line;
//                }
//                line = cmd.lineread.readLine();
//            }
//            if (currentSQL.length() > 0) {
//                allSql.add(currentSQL);
//            }
        } catch (IOException ex) {
            cmd.log.severe("PFAP0DB100To101.error", ex);
            String msg = "PFAP0DB100To101.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    private void createDatabase() throws ProcessException {
        try {
            super.setMinorTotalCount(allSql.size());
            String psName = "PFAP0DB100To101";
            cmd.db.smartStart();
            //cmd.db.setAutoCommit(false);
            for (String sql : allSql) {
                cmd.db.setStatement(psName, sql);
                cmd.db.execUpdate(psName);
                super.minorCompleted();
            }
            cmd.db.smartComplete();
            //cmd.db.commit();
        } catch (SQLException ex) {
            cmd.log.severe("PFAP0DB100To101.error", ex);
            String msg = "PFAP0DB100To101.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            cmd.common.showI18nMessage(DialogMessageType.ERROR, msg, msg);
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "PFAP0DB100To101.title";
    }
}
