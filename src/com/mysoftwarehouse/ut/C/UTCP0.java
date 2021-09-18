/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.C;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTCP0 extends ProcessForm {

    int directoryRemovedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTCP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done", "Done: Directori(es) removed: " + directoryRemovedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTCP0.error";
    }

    private void doFile(File file) throws ProcessException {
        if (file.isDirectory()) {
            File[] allChild = file.listFiles();
            //int fileCount = allChild.length;
            for (File a : allChild) {
                if (a.isDirectory()) {
                    doFile(a);
                }
            }
            File[] again = file.listFiles();
            if (again.length == 0) {
                setI18nMessage("remove:" + file.getAbsolutePath());
                file.delete();
                directoryRemovedCount++;
            }
        } else {
            return;
        }
    }
}
