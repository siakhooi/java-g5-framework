/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import com.gqrsoft.g5.kernel.framepanel.process.list.ProcessLogTableModel;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaLog extends AbstractProcessFormArea {

    private JScrollPane jsp;
    private JTable logTable;
    private ProcessLogTableModel tm;

    @Override
    public Component getComponent() {
        return jsp;
    }

    @Override
    public void init() {
        String dateTitle = "ProcessPanel.Log.Title.Date";
        String messageTitle = "ProcessPanel.Log.Title.Message";
        dateTitle = getFormControl().cmd.lang.getSystemString(dateTitle);
        messageTitle = getFormControl().cmd.lang.getSystemString(messageTitle);

        tm = new ProcessLogTableModel(dateTitle, messageTitle);
        logTable = new JTable(tm);

        logTable.setRowSelectionAllowed(true);
        logTable.setColumnSelectionAllowed(false);
        logTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jsp = new JScrollPane(logTable);
    }

    public void addLog(String date, String s) {
        //TODO: 
        tm.addRow(date, s);
        int i = tm.getRowCount() - 1;
        logTable.setRowSelectionInterval(i, i);
        Rectangle rect = logTable.getCellRect(i, 0, true);
        logTable.repaint();
        //     logTable.scrollRectToVisible(rect);
        //jsp.getViewport().setViewPosition(rect.getLocation());
        jsp.getViewport().scrollRectToVisible(rect);

    }
}
