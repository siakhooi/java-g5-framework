/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.kernel.framepanel.process.ProcessEnum;
import com.gqrsoft.g5.kernel.framepanel.process.control.ProcessOutputControl;
import com.gqrsoft.g5.kernel.framepanel.process.control.ProcessProgressControl;
import com.gqrsoft.g5.kernel.framepanel.process.control.ProcessThread;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaButtons;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaFormTitle;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaInformationIcon;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaLog;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaMessage;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormAreaProgressBar;
import com.gqrsoft.g5.kernel.framepanel.process.gui.ProcessFormGuiInitializer;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormFramePanel extends AbstractFramePanel {

    public ProcessFormAreaFormTitle areaFormTitle;
    public ProcessFormAreaInformationIcon areaInformationIcon;
    public ProcessFormAreaMessage areaMessage;
    public ProcessFormAreaButtons areaButtons;
    public ProcessFormAreaProgressBar areaProgressBar;
    public ProcessFormAreaLog areaLog;
    private ProcessEnum.StatusType status = ProcessEnum.StatusType.NOT_STARTED;
    private ProcessThread processThread;
    public ProcessOutputControl processOutput;
    public ProcessProgressControl processProgress;

    @Override
    public void initPanel() {
        getFormControl().processForm.init();

        if (getFormControl().processForm.showThisForm()) {
            ProcessFormGuiInitializer p = new ProcessFormGuiInitializer();
            p.setFormControl(getFormControl());
            p.run();
        }

        processOutput = new ProcessOutputControl();
        processOutput.setFormControl(getFormControl());
        processProgress = new ProcessProgressControl();
        processProgress.setFormControl(getFormControl());
        processProgress.init();
        processOutput.refreshProgressBar();
    }

    @Override
    public void onInEnter() {
    }

    @Override
    public void eventAfterVisible() {
//        CONSOLE.println("eventAfterVisible:"+getFormControl().processForm.getFormI18nTitle());
//        CONSOLE.println("manual: "+getFormControl().processForm.isUserManualStart());
//        CONSOLE.println("showForm: "+getFormControl().processForm.showThisForm());
        if (getFormControl().processForm.processTree.isUserManualStart &&
                getFormControl().processForm.showThisForm()) {

            String t = "ProcessPanel.Click.To.Start";
            t = getFormControl().cmd.lang.getSystemString(t);
            areaMessage.setTitle(t);
            areaMessage.setMessage(" ");
            areaButtons.setEnable(true, false, true);
        } else {
            startTask();
        }
    }

    @Override
    public void onEscapeKeyPressed() {
        switch (status) {
            case COMPLETED:
            case NOT_STARTED:
            case PROCESS_CANCELLED:
            case USER_CANCELLED:
                //getFormControl().cmd.form.closeForm();
                userClickedClose();
                break;
            case RUN:
                userClickedCancel();
                break;
            case CRITICAL_RUNNING:
                break;
        }
    }

    public void userClickedClose() {
        switch (status) {
            case COMPLETED:
            case NOT_STARTED:
            case PROCESS_CANCELLED:
            case USER_CANCELLED:
                getFormControl().cmd.form.closeForm();
                break;
            case RUN:
            case CRITICAL_RUNNING:
                CONSOLE.error("Impossible stage: Close Button enabled? Please report.");
                break;
        }
    }

    public void userClickedStart() {
        if (status == status.NOT_STARTED) {
            startTask();
        } else {
            CONSOLE.error("Impossible stage: Start Button enabled? Please report.");
        }
    }

    public void userClickedCancel() {
        if (status == status.RUN) {
            status = status.USER_CANCELLING;
            areaButtons.setEnable(false, false, false);
        } else {
            CONSOLE.error("Impossible stage: Cancel Button enabled? Please report.");
        }
    }

    private void startTask() {
        status = status.RUN;
        if (getFormControl().processForm.showThisForm()) {
            areaButtons.setEnable(false,
                    getFormControl().processForm.processTree.isUserAllowCancel,
                    false);
        }
        String b = "ProcessPanel.Process.Started";
        b = getFormControl().cmd.lang.getSystemString(b);
        processOutput.addLog(b);

        processProgress.start();
        processOutput.refreshProgressBar();

        processThread = new ProcessThread(getFormControl());
        processThread.start();
    }

    public void taskCancelled(ProcessException ex) {
        String t = ex.userCancelled
                ? "ProcessPanel.Tasks.Cancelled.By.User"
                : "ProcessPanel.Tasks.Cancelled.By.Process";

        t = getFormControl().cmd.lang.getSystemString(t);
        processOutput.setTitle(t);
        processOutput.setMessage(" ");
        processOutput.addLog(t);
        if (ex.userCancelled) {
            status = status.USER_CANCELLED;
            try {
                getFormControl().processForm.userCancel();
            } catch (ProcessException ex1) {
                CONSOLE.error("P1: Should not throw!", ex1);
            }
        } else {
            status = status.PROCESS_CANCELLED;
            try {
                getFormControl().processForm.processCancel();
            } catch (ProcessException ex2) {
                CONSOLE.error("P2: Should not throw!", ex2);
            }
        }

        if (getFormControl().processForm.processTree.isUserManualClose &&
                getFormControl().processForm.showThisForm()) {
            areaButtons.setEnable(false, false, true);
        } else {
            getFormControl().cmd.form.closeForm();
        }
    }

    public void taskCompleted() {
        status = status.COMPLETED;
        String t = "ProcessPanel.Tasks.Completed";
        t = getFormControl().cmd.lang.getSystemString(t);

        processOutput.setTitle(t);
        processOutput.setMessage(" ");
        processOutput.addLog(t);
        processProgress.completeProcess();
        processOutput.refreshProgressBar();


        if (getFormControl().processForm.processTree.isUserManualClose &&
                getFormControl().processForm.showThisForm()) {
            areaButtons.setEnable(false, false, true);
        } else {
            getFormControl().cmd.form.closeForm();
        }
    }

    public void disableCancellation() {
        status = status.CRITICAL_RUNNING;
        if (getFormControl().processForm.showThisForm()) {
            areaButtons.setEnable(false, false, false);
        }
    }

    private void checkStatus() throws ProcessException {
        if (status == status.PROCESS_CANCELLING) {
            throw new ProcessException(false);
        } else if (status == status.USER_CANCELLING) {
            throw new ProcessException(true);
        }
//        if (status == status.PROCESS_CANCELLED) {
//            throw new ProcessException(false);
//        } else if (status == status.USER_CANCELLED) {
//            throw new ProcessException(true);
//        }
    }

    public void completed() throws ProcessException {
        checkStatus();
        String t = processProgress.currentProcessTitle;
        String b = "ProcessPanel.Process.{0}.Completed";
        b = getFormControl().cmd.lang.getSystemString(b, t);
        processOutput.addLog(b);
        processProgress.nextProcess();
        processOutput.refreshProgressBar();
    }

    public void setMinorTotalCount(int i) throws ProcessException {
        checkStatus();
        processProgress.setMinorTotalCount(i);
        processOutput.refreshProgressBar();
    }

    public void minorCompleted(int i) throws ProcessException {
        checkStatus();
        processProgress.minorCompleted(i);
        processOutput.refreshProgressBar();
    }

    public void cancelNow() throws ProcessException {
        status = status.PROCESS_CANCELLING;
        throw new ProcessException(false);
    }

    public void setI18nLogMessage(String s) throws ProcessException {
        checkStatus();
        processOutput.addLog(s);
    }

    public void setI18nProgressMessage(String s) throws ProcessException {
        checkStatus();
        processOutput.setMessage(s);
    }

    @Override
    public void onOutExit() {
    }

    @Override
    public void onInExit() {
    }

    @Override
    public void onOutEnter() {
    }
}
