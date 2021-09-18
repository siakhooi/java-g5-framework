/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaProgressBar extends AbstractProcessFormArea {

    private JProgressBar pbProgressBar;
    private JPanel jpProgressBar = new JPanel();

    @Override
    public Component getComponent() {
        return jpProgressBar;
    }

    @Override
    public void init() {

        pbProgressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        pbProgressBar.setMinimum(0);
        pbProgressBar.setValue(0);
        pbProgressBar.setStringPainted(true);
        pbProgressBar.setOrientation(JProgressBar.HORIZONTAL);

        jpProgressBar.setLayout(new BorderLayout());
        jpProgressBar.add(Box.createHorizontalStrut(5),
                BorderLayout.LINE_START);
        jpProgressBar.add(pbProgressBar,
                BorderLayout.CENTER);
        jpProgressBar.add(Box.createHorizontalStrut(5),
                BorderLayout.LINE_END);
    }

    public void setMaximumValue(int i) {
        pbProgressBar.setMaximum(i);
    }

    public void setValue(int i) {
        pbProgressBar.setValue(i);
    }

    public void setIndeterminate(boolean value) {
        pbProgressBar.setIndeterminate(value);
    }

    public void setText(String text) {
        pbProgressBar.setString(text);
    }
}
