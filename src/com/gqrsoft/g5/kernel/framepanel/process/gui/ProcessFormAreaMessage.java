/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaMessage extends AbstractProcessFormArea {

    private JLabel lblProcessTitle;
    private JLabel lblProcessMessage;
    private JPanel jpMessage;

    @Override
    public Component getComponent() {
        return jpMessage;
    }

    @Override
    public void init() {
        jpMessage = new JPanel();
        lblProcessTitle = new JLabel(" ");
        lblProcessMessage = new JLabel(" ");
        lblProcessTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblProcessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpMessage.setLayout(new BorderLayout());
        jpMessage.add(lblProcessTitle, BorderLayout.PAGE_START);
        jpMessage.add(lblProcessMessage, BorderLayout.CENTER);
    }

    public void setTitle(String rawText) {
        lblProcessTitle.setText(rawText);
    }

    public void setMessage(String rawText) {
        lblProcessMessage.setText(rawText);
    }
}
