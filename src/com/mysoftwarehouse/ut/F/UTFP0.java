/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.F;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTFP0 extends ProcessForm {

    int directoryRenamedCount = 0;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTFP0.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        File file = new File(cmd.in.stringValue);
        doFile(file);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, "Done",
                "Done: Directori(es) renamed: " + directoryRenamedCount);

        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "UTFP0.error";
    }

    private void doFile(File parent) throws ProcessException {
        if (parent.isDirectory()) {
            int directoryCount = 0;
            File[] allChild = parent.listFiles();
            for (File child : allChild) {
                if (child.isDirectory()) {
                    doFile(child);
//                } else {
                    directoryCount++;
                }
            }
            if (directoryCount > 0) {
                int sequenceLength = (directoryCount + "").length();
                int currentSeq = 0;
                allChild = parent.listFiles();
                for (File child : allChild) {
                    //if (!child.isDirectory()) {
                    if (child.isDirectory()) {
                        currentSeq++;

                        String fileName = child.getAbsolutePath();
//                        int i = fileName.lastIndexOf(".");
//                        String fExt = "";
//                        if (i != -1) {
//                            fExt = fileName.substring(i + 1);
//                        }
                        String c = "000000000000000000" + currentSeq;
                        c = c.substring(c.length() - sequenceLength);

//                        if (fExt.length() > 0) {
//                            c = c + "." + fExt;
//                        }

                        String newFilePath = parent.getAbsolutePath() + File.separator + c;
                        setI18nMessage(
                                "rename " + child.getAbsolutePath() + " to " + newFilePath);
                        child.renameTo(new File(newFilePath));
                        directoryRenamedCount++;
                    }
                }
            }
        }
    }
}
