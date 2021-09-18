/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaButtons extends AbstractProcessFormArea {

    private JButton cmdStart;
    private JButton cmdCancel;
    private JButton cmdClose;
    private JPanel jpButtons;

    @Override
    public Component getComponent() {
        return jpButtons;
    }

    @Override
    public void init() {
        String t, d;
        t = "ProcessPanel.button.Start.Alt.S.label";
        t = getFormControl().cmd.lang.getSystemString(t);
        d = "ProcessPanel.button.Start.tooltip";
        d = getFormControl().cmd.lang.getSystemString(d);
        cmdStart = new JButton(t);
        cmdStart.setMnemonic(KeyEvent.VK_S);
        cmdStart.setToolTipText(d);

        t = "ProcessPanel.button.Close.Alt.E.label";
        t = getFormControl().cmd.lang.getSystemString(t);
        d = "ProcessPanel.button.Close.tooltip";
        d = getFormControl().cmd.lang.getSystemString(d);
        cmdClose = new JButton(t);
        cmdClose.setMnemonic(KeyEvent.VK_E);
        cmdClose.setToolTipText(d);

        t = "ProcessPanel.button.Cancel.Alt.C.label";
        t = getFormControl().cmd.lang.getSystemString(t);
        d = "ProcessPanel.button.Cancel.tooltip";
        d = getFormControl().cmd.lang.getSystemString(d);
        cmdCancel = new JButton(t);
        cmdCancel.setMnemonic(KeyEvent.VK_C);
        cmdCancel.setToolTipText(d);

        JPanel jpInner = new JPanel();
        jpInner.setLayout(new BoxLayout(jpInner, BoxLayout.LINE_AXIS));
        jpInner.add(cmdStart);
        jpInner.add(Box.createHorizontalStrut(5));
        jpInner.add(cmdCancel);
        jpInner.add(Box.createHorizontalStrut(5));
        jpInner.add(Box.createGlue());
        jpInner.add(cmdClose);

        jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        jpButtons.add(Box.createHorizontalStrut(5), BorderLayout.LINE_START);
        jpButtons.add(jpInner, BorderLayout.CENTER);
        jpButtons.add(Box.createHorizontalStrut(5), BorderLayout.LINE_END);

        cmdStart.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getFormControl().processPanel.userClickedStart();
                    }
                });
        cmdClose.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getFormControl().processPanel.userClickedClose();
                    }
                });
        cmdCancel.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getFormControl().processPanel.userClickedCancel();
                    }
                });
    }

    public void setEnable(boolean start, boolean cancel, boolean close) {
        cmdStart.setEnabled(start);
        cmdCancel.setEnabled(cancel);
        cmdClose.setEnabled(close);
        if (start) {
            getFormControl().cmd.frame.setDefaultButton(cmdStart);
        } else if (close) {
            getFormControl().cmd.frame.setDefaultButton(cmdClose);
        } else {
            getFormControl().cmd.frame.setDefaultButton(null);
        }
    }
}
