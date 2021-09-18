/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.control;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessProgressControl extends AbstractProcessControl {

    public String progressBarText;
    public String currentProcessTitle;
    public int progressBarMaximumValue;
    public int progressBarCurrentValue;
    public boolean progressBarIndeterminate;
    public double progressBarPercentage;
    private double previousProgressBarPercentage;
    //working variable
    private String abstractProgressBarText;
    private int currentProcessWeight = 0;
    private int currentProcessNumber = -1;
    private int currentAccumulatedProcessWeight = 0;
    private int totalMinorCount = 0;
    private int currentMinorValue = 0;
    private ProcessTree processTree;

    public void init() {
        String title =
                "ProcessPanel.%n.of.%m.Process.Completed.%p.Percent";
        abstractProgressBarText =
                getFormControl().cmd.lang.getSystemString(title);
        processTree = getFormControl().processForm.processTree;
        int maxWeight = processTree.getTotalWeight();
        progressBarMaximumValue = maxWeight;
        recalculate();
    }

    public void completeProcess() {
        currentAccumulatedProcessWeight = progressBarMaximumValue;
        currentProcessNumber = processTree.getTotalProcessCount();
        currentProcessWeight = 0;
        totalMinorCount = 0;
        currentMinorValue = 0;
        recalculate();
    }

    public void start() {
        currentAccumulatedProcessWeight = 0;
        currentProcessNumber = 0;
        currentProcessWeight = processTree.get(currentProcessNumber).weight;
        totalMinorCount = 0;
        currentMinorValue = 0;

        recalculate();
    }

    public void nextProcess() {
        currentAccumulatedProcessWeight +=
                (currentProcessWeight *
                EngineConfiguration.Process.TOTAL_MINOR_COUNT_PER_PROCESS);

        currentProcessNumber++;
        if (currentProcessNumber >= processTree.getTotalProcessCount()) {
            //completed
            currentProcessWeight = 0;
        } else {
            currentProcessWeight = processTree.get(currentProcessNumber).weight;
        }
        totalMinorCount = 0;
        currentMinorValue = 0;
        recalculate();
    }

    public void setMinorTotalCount(int i) {
        totalMinorCount = i;
        recalculate();
    }

    public void minorCompleted(int i) {
        currentMinorValue += i;
        recalculate();
    }

    private void recalculate() {
        calculateIndeterminate();
        calculateCurrentValueAndPercentage();
        calculateProgressBarText();
        calculateProcessTitle();
    //CONSOLE.println("minor: "+currentMinorValue+"/"+totalMinorCount);
    }

    private void calculateCurrentValueAndPercentage() {
        if (totalMinorCount == 0) {
            progressBarCurrentValue = currentAccumulatedProcessWeight;
        } else {
            progressBarCurrentValue =
                    currentAccumulatedProcessWeight +
                    (currentProcessWeight *
                    EngineConfiguration.Process.TOTAL_MINOR_COUNT_PER_PROCESS *
                    currentMinorValue / totalMinorCount);
        }
        double t = processTree.getTotalWeight();
        if (progressBarIndeterminate) {
            progressBarPercentage = previousProgressBarPercentage;
            progressBarCurrentValue = (int) (progressBarPercentage * t);

        } else {
            previousProgressBarPercentage = progressBarPercentage;
            if (t == 0.0) {
                progressBarPercentage = 0.0;
            } else {
                progressBarPercentage = progressBarCurrentValue / t;
            }
        }
    }

    private void calculateIndeterminate() {
        progressBarIndeterminate =
                currentMinorValue < 0 ||
                currentMinorValue > totalMinorCount;
    }

    private void calculateProgressBarText() {
        String n;
        if (currentProcessNumber < 0) {
            n = getFormControl().cmd.data.int2String(0);

        } else {
            n = getFormControl().cmd.data.int2String(currentProcessNumber);
        }
        String m =
                getFormControl().cmd.data.int2String(processTree.getTotalProcessCount());
        String p =
                getFormControl().cmd.lang.formatDouble(progressBarPercentage, "#0.00%");
        String s =
                abstractProgressBarText;
        s = s.replaceAll("%n", n);
        s = s.replaceAll("%m", m);
        s = s.replaceAll("%p", p);
        progressBarText = s;
    }

    private void calculateProcessTitle() {
        if (currentProcessNumber > processTree.getTotalProcessCount()) {
            currentProcessTitle = "";
        } else {

            Process p = processTree.get(currentProcessNumber);
            if (p == null) {
                currentProcessTitle = "";
            } else {
                currentProcessTitle = p.title;
            }
        }
    }
}
