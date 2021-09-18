/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.B;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTBP0 extends ProcessForm {

    int fileRenamedCount = 0;
    int directoryRemovedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTBP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doRootFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done",  "Done: Directori(es) removed: " + directoryRemovedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTBP0.error";
    }

    private void doRootFile(File root) throws ProcessException {
        //dun touch root
        if (!root.isDirectory()) {
            //should not proceed
            return;
        }
        File[] children = root.listFiles();
        for (File child : children) {
            if (child.isDirectory()) {
                doFile(child);
                setMessage("remove " + child.getAbsolutePath());
                child.delete();
                directoryRemovedCount++;
            }
        }
    }

    private void doFile(File parent) throws ProcessException {
        if (parent.isDirectory()) {

            File[] allChild = parent.listFiles();
            for (File child : allChild) {
                if (child.isDirectory()) {
                    doFile(child);
                    setI18nMessage("remove " + child.getAbsolutePath());
                    child.delete();
                    directoryRemovedCount++;
                }
            }
            allChild = parent.listFiles();
            for (File child : allChild) {
                if (!child.isDirectory()) {
                    String parentFilePath = parent.getAbsolutePath();
                    String newFilePath = parentFilePath + "." + child.getName();

                    setI18nMessage("rename " + child.getAbsolutePath() + " to " + newFilePath);
                    child.renameTo(new File(newFilePath));
                    fileRenamedCount++;
                }
            }
        }
    }
}
