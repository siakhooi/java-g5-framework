/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.table;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.BooleanRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.CalendarRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.ColorRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.NumericRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.PasswordRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.TextRenderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultReadOnlyTable extends JTable {

    private FormControl formControl;

    public void init(final FormControl formControl) {
        this.formControl = formControl;
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setSelectionMode(formControl.listForm.getSelectionType().type);
        setAutoResizeMode(AUTO_RESIZE_OFF);

        setDefaultRenderer(BooleanRenderer.class, new BooleanRenderer());
        setDefaultRenderer(TextRenderer.class, new TextRenderer());
        setDefaultRenderer(CalendarRenderer.class, new CalendarRenderer());
        setDefaultRenderer(NumericRenderer.class, new NumericRenderer());
        setDefaultRenderer(PasswordRenderer.class, new PasswordRenderer());
        setDefaultRenderer(ColorRenderer.class, new ColorRenderer());
//        setDefaultRenderer(FileRenderer.class, new FileRenderer());
//        setDefaultRenderer(ImageRenderer.class, new ImageRenderer());

        super.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = formControl.listPanel.table.getSelectedRow();
                    int col = formControl.listPanel.table.getSelectedColumn();
                    row = formControl.listPanel.table.convertRowIndexToModel(row);
                    col = formControl.listPanel.table.convertColumnIndexToModel(col);
                    formControl.listPanel.executeDoubleClick(row, col);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}
