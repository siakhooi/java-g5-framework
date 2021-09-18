/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class ContextMenuMouseListener implements MouseListener {

    private JPopupMenu popMenu;

    public ContextMenuMouseListener(JPopupMenu value) {
        this.popMenu = value;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
