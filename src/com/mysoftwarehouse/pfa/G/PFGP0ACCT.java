/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.db.ACCT.ACCT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGP0ACCT extends ProcessForm {

    private String file = "";
    private boolean purgeTemp = false;
    private String id = "";

    @Override
    public void init() {
        file = cmd.in.stringValue;
        purgeTemp = cmd.in.booleanValue;
        id = cmd.global.texts.get(USR.PFUSR_ID);

        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFGP0ACCT.Purge", 1);
        addProcess("PFGP0ACCT.OpenFile", 1);
        addProcess("PFGP0ACCT.LoadFile", 8);
    }

    @Override
    public void run() throws ProcessException {
        purgeTemp();
        super.completed();
        openFile();
        super.completed();
        loadFile();
        super.completed();
    }

    private void purgeTemp() throws ProcessException {
        if (purgeTemp) {
            try {
                cmd.db.begin();
                ACCT.delete(this, id);
                cmd.db.commit();
            } catch (SQLException e) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex) {
                }
                cmd.log.severe("PFGP0ACCT.error", e);
                String msg = "PFGP0ACCT.error.purge.{0}";
                msg = cmd.lang.getString(msg, e.getLocalizedMessage());
                super.setI18nMessage(msg);
                super.cancelNow();
            }
        }
    }

    private void openFile() throws ProcessException {
        try {
            cmd.csvreader.load(new File(file));
        } catch (FileNotFoundException ex) {
            cmd.log.severe("PFGP0ACCT.error", ex);
            String msg = "PFGP0ACCT.error.openfile.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    private void loadFile() throws ProcessException {
        try {
            final int FIELDCOUNT = 6;
            String[] value = new String[FIELDCOUNT];
            String[] value2 = new String[FIELDCOUNT];
            while ((value = cmd.csvreader.readNext()) != null) {
                for (int i = 0; i < FIELDCOUNT; i++) {
                    value2[i] = "";
                    if (value.length > i) {
                        value2[i] = cmd.data.unNullString(value[i]);
                    }
                }
                cmd.db.begin();
                ACCT.insert(this, id, value2);
                cmd.db.commit();
            }
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("PFGP0ACCT.error", ex);
            String msg = "PFGP0ACCT.error.sql.add.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        } catch (IOException ex) {
            cmd.log.severe("PFGP0ACCT.error", ex);
            String msg = "PFGP0ACCT.error.file.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFGP0ACCT.title";
    }
}
