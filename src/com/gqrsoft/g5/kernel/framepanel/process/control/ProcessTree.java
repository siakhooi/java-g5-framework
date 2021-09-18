/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.control;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessTree {

    public boolean isUserManualStart = true;
    public boolean isUserManualClose = true;
    public boolean isUserAllowCancel = true;
    public boolean isShowLogList = false;
    //
    private Vector<Process> all = new Vector<Process>();
    private int totalProgressWeight = 0;

    public int getTotalWeight() {
        return this.totalProgressWeight;
    }

    public void add(String i18nTitle, int weight) {
        totalProgressWeight +=
                (weight * EngineConfiguration.Process.TOTAL_MINOR_COUNT_PER_PROCESS);
        Process p = new Process(i18nTitle, weight);
        all.add(p);
    }

    public int getTotalProcessCount() {
        return all.size();
    }

    public Process get(int index) {
        if (index < 0 || index >= all.size()) {
            return null;
        } else {
            return all.elementAt(index);
        }
//        if (index <= 0) {
//            return null;
//        }
//        return all.elementAt(index - 1);
    }
}
