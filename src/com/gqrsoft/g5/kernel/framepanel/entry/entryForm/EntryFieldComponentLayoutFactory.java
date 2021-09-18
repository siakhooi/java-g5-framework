/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldComponentLayoutFactory {

    public static void layoutLabel(JPanel panel, JComponent labelField) {
        if (panel == null) {
            return;
        }
        if (labelField == null) {
            return;
        }
        panel.setLayout(new BorderLayout());
        panel.add(labelField, BorderLayout.LINE_END);
    }

    public static void layoutDataField(JPanel panel, JComponent dataField, JComponent selectButton) {
//        dataArea.getPanel().setLayout(new BorderLayout());
//        dataArea.getPanel().add(dataField, BorderLayout.LINE_START);
//        dataArea.getPanel().add(selectButton, BorderLayout.LINE_END);

        if (panel == null) {
            return;
        }
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        if (dataField != null) {
            panel.add(dataField);
        }
        if (selectButton != null) {
            panel.add(selectButton);
        }
    }
}
