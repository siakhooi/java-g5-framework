/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.E;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTEP0 extends ProcessForm {

    int fileRenamedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(true);

        addProcess("UTEP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done",
                "Done: File(s) renamed: " + fileRenamedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTEP0.error";
    }

    private void doFile(File parent) throws ProcessException {
        if (parent.isDirectory()) {
            int fileCount = 0;
            File[] allChild = parent.listFiles();
            for (File child : allChild) {
                if (child.isDirectory()) {
                    doFile(child);
                } else {
                    fileCount++;
                }
            }
            if (fileCount > 0) {
                int sequenceLength = (fileCount + "").length();
                int currentSeq = 0;
                allChild = parent.listFiles();
                for (File child : allChild) {
                    if (!child.isDirectory()) {
                        currentSeq++;

                        String fileName = child.getAbsolutePath();
                        int i = fileName.lastIndexOf(".");
                        String fExt = "";
                        if (i != -1) {
                            fExt = fileName.substring(i + 1);
                        }
                        String c = "000000000000000000" + currentSeq;
                        c = c.substring(c.length() - sequenceLength);

                        if (fExt.length() > 0) {
                            c = c + "." + fExt;
                        }

                        String newFilePath = parent.getAbsolutePath() + File.separator + c;
                        setI18nMessage(
                                "rename " + child.getAbsolutePath() + " to " + newFilePath);
                        child.renameTo(new File(newFilePath));
                        fileRenamedCount++;
                    }
                }
            }
        }
    }
}
