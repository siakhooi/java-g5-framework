/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.list.gui.table.ColumnWidthSetter;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListTablePanel extends JPanel {

    private FormControl formControl;
    public JScrollPane jScrollPane;

    public void init(FormControl formControl) {
        this.formControl = formControl;

        ColumnWidthSetter.init(formControl);

        jScrollPane = new JScrollPane(formControl.listPanel.table);

        super.setLayout(new BorderLayout());
        super.add(jScrollPane, BorderLayout.CENTER);
    }
}
