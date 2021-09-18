/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.D;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTDP0 extends ProcessForm {

    int directoryRemovedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTDP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doRootFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done", "Done: Directori(es) removed: " + directoryRemovedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTDP0.error";
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
            }
        }
    }

    private void doFile(File parent) throws ProcessException {
        File[] allChildren = parent.listFiles();
        int fileCount = 0;
        for (File child : allChildren) {
            if (child.isDirectory()) {
                doFile(child);
            } else {
                fileCount++;
            }
        }
        if (fileCount == 0) {
            allChildren = parent.listFiles();
            //rename all child?
            for (File child : allChildren) {
                if (child.isDirectory()) {
                    //no file, rename the child to be same level with parent
                    String newFileName = parent.getAbsolutePath() +
                            "." + child.getName();
                    File newFile = new File(newFileName);
                    setI18nMessage("rename: " + child.getAbsolutePath() + " to " + newFile.getAbsolutePath());
                    child.renameTo(newFile);
                }
            }
            setI18nMessage("remove: " + parent.getAbsolutePath());
            parent.delete();
            directoryRemovedCount++;
        }
    }
}
