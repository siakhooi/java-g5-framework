/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.db.TXNT.TXNT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDP0TXNT extends ProcessForm {

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

        addProcess("PFDP0TXNT.Purge", 1);
        addProcess("PFDP0TXNT.OpenFile", 1);
        addProcess("PFDP0TXNT.LoadFile", 8);
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
                TXNT.delete(this, id);
                cmd.db.commit();
            } catch (SQLException e) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
                cmd.log.severe("PFDP0TXNT.error", e);
                String title = "PFDP0TXNT.error.purge.{0}";
                title = cmd.lang.getString(title, e.getLocalizedMessage());
                super.setI18nMessage(title);
                super.cancelNow();
            }
        }
    }

    private void openFile() throws ProcessException {
        try {
            cmd.csvreader.load(new File(file));
        } catch (FileNotFoundException ex) {
            cmd.log.severe("PFDP0TXNT.error", ex);
            String title = "PFDP0TXNT.error.openfile.{0}";
            title = cmd.lang.getString(title, ex.getLocalizedMessage());
            super.setI18nMessage(title);
            super.cancelNow();
        }
    }

    private void loadFile() throws ProcessException {
        try {
            final int FIELDCOUNT = 8;
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
                TXNT.insert(this, id, value2);
                cmd.db.commit();
            }
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFDP0TXNT.error", ex);
            String title = "PFDP0TXNT.error.sql.add.{0}";
            title = cmd.lang.getString(title, ex.getLocalizedMessage());
            super.setI18nMessage(title);
        } catch (IOException ex) {
            cmd.log.severe("PFDP0TXNT.error", ex);
            String title = "PFDP0TXNT.error.file.{0}";
            title = cmd.lang.getString(title, ex.getLocalizedMessage());
            super.setI18nMessage(title);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFDP0TXNT.title";
    }
}
