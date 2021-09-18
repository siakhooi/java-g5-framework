/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTGP0 extends ProcessForm {

    private final String THUMBSDB = "Thumbs.db";
    int fileRemovedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTGP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done",
                "Done: File(s) removed: " + fileRemovedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTGP0.error";
    }

    private void doFile(File file) throws ProcessException {
        if (file.isDirectory()) {
            File thumbsdb = new File(file, THUMBSDB);
            if (thumbsdb.exists()) {
                setI18nMessage("remove: " + thumbsdb.getAbsolutePath());
                thumbsdb.delete();
                fileRemovedCount++;
            }

            File[] allChild = file.listFiles();
            //int fileCount = allChild.length;
            for (File a : allChild) {
                if (a.isDirectory()) {
                    doFile(a);
                }
            }
        } else {
            return;
        }
    }
}
