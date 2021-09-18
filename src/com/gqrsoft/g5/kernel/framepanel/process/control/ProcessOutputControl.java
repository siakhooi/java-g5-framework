/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.control;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessOutputControl extends AbstractProcessControl {

    public void addLog(String b) {
        String d = form().cmd.lang.formatCalendar(
                form().cmd.cal.getNow(),
                EngineConfiguration.Process.LOG_DATETIME_FORMAT);

        if (form().showThisForm()) {
            panel().areaLog.addLog(d, b);
        } else {
            CONSOLE.println(d + ": " + b);
        }
        //form().cmd.log.log(Level.SEVERE, b);
    }

    public void setTitle(String t) {
        if (form().showThisForm()) {
            panel().areaMessage.setTitle(t);
        } else {
        }
    }

    public void setMessage(String t) {
        if (form().showThisForm()) {
            panel().areaMessage.setMessage(t);
        } else {
        }
    }

    public void refreshProgressBar() {
        if (form().showThisForm()) {
            String textPadding = "      ";
            panel().areaMessage.setTitle(
                    panel().processProgress.currentProcessTitle);
            panel().areaProgressBar.setMaximumValue(
                    panel().processProgress.progressBarMaximumValue);
            panel().areaProgressBar.setValue(
                    panel().processProgress.progressBarCurrentValue);
            panel().areaProgressBar.setIndeterminate(
                    panel().processProgress.progressBarIndeterminate);
            panel().areaProgressBar.setText(
                    textPadding +
                    panel().processProgress.progressBarText +
                    textPadding);
        } else {
        }
    }
}

