/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormGuiInitializer {

    private FormControl fc;

    public void setFormControl(FormControl fc) {
        this.fc = fc;
    }

    private FormControl getFormControl() {
        return fc;
    }

    private ProcessFormFramePanel panel() {
        return getFormControl().processPanel;
    }

    public void run() {
        init();
        place();
    }

    private void init() {
        panel().areaFormTitle = new ProcessFormAreaFormTitle();
        panel().areaFormTitle.setFormControl(getFormControl());
        panel().areaFormTitle.init();

        panel().areaInformationIcon = new ProcessFormAreaInformationIcon();
        panel().areaInformationIcon.setFormControl(getFormControl());
        panel().areaInformationIcon.init();

        panel().areaMessage = new ProcessFormAreaMessage();
        panel().areaMessage.setFormControl(getFormControl());
        panel().areaMessage.init();

        panel().areaButtons = new ProcessFormAreaButtons();
        panel().areaButtons.setFormControl(getFormControl());
        panel().areaButtons.init();

        panel().areaProgressBar = new ProcessFormAreaProgressBar();
        panel().areaProgressBar.setFormControl(getFormControl());
        panel().areaProgressBar.init();

        panel().areaLog = new ProcessFormAreaLog();
        panel().areaLog.setFormControl(getFormControl());
        panel().areaLog.init();
    }

    private void place() {
        JPanel jpMessage = new JPanel();
        jpMessage.setLayout(new BorderLayout());
        jpMessage.add(
                panel().areaInformationIcon.getComponent(),
                BorderLayout.LINE_START);

        jpMessage.add(
                panel().areaMessage.getComponent(),
                BorderLayout.CENTER);

        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.PAGE_AXIS));
//        //jpMain.getPanel().setBorder(new EmptyBorder(5, 20, 5, 20));
        jpMain.add(panel().areaFormTitle.getComponent());
        jpMain.add(jpMessage);
        jpMain.add(panel().areaProgressBar.getComponent());
        jpMain.add(Box.createVerticalStrut(5));
        jpMain.add(panel().areaButtons.getComponent());
        jpMain.add(Box.createVerticalStrut(5));

        JPanel jpBase = new JPanel();
        jpBase.setLayout(new BorderLayout());
        jpBase.add(jpMain, BorderLayout.PAGE_START);
        if (fc.processForm.processTree.isShowLogList) {
            jpBase.add(panel().areaLog.getComponent(),
                    BorderLayout.CENTER);
        }

        panel().getPanel().setLayout(new BorderLayout());
        panel().getPanel().add(jpBase, BorderLayout.CENTER);
    }
}
