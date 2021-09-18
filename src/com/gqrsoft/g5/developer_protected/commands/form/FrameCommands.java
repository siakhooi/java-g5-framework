/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import javax.swing.JButton;

/**
 *
 * @author Ng Siak Hooi
 */
public class FrameCommands extends AbstractCommandComponent {

    public void setDefaultButton(JButton jb) {
//        CONSOLE.println("isnull: " + jb);
//        CONSOLE.println("isnull: " + getFormControl());
//        CONSOLE.println("isnull: " + getFormControl().frame);
//        CONSOLE.println("isnull: " + getFormControl().frame.getJDialog());
//        CONSOLE.println("isnull: " + getFormControl().frame.getJFrame());
        if (getFormControl().frame.isJDialog()) {
            try {
                getFormControl().frame.getJDialog().getRootPane().setDefaultButton(jb);
            } catch (Exception e) {
            }
        }
        if (getFormControl().frame.isJFrame()) {
            try {
                getFormControl().frame.getJFrame().getRootPane().setDefaultButton(jb);
            } catch (Exception e) {
            }
        }
    }

    public int getWidth() {
        return getFormControl().frame.getWindow().getSize().width;
    }

    public int getHeight() {
        return getFormControl().frame.getWindow().getSize().height;
    }

    public int getX() {
        return getFormControl().frame.getWindow().getLocation().x;
    }

    public int getY() {
        return getFormControl().frame.getWindow().getLocation().y;
    }

    public void setLocation(int x, int y) {
        getFormControl().frame.getWindow().setLocation(x, y);
    }

    public void setSize(int w, int h) {
        getFormControl().frame.getWindow().setSize(w, h);
    }

    public void setState(FormEnum.FrameState frameState) {
        if (getFormControl().frame.isJFrame()) {
            getFormControl().frame.getJFrame().setExtendedState(frameState.frameState);
        }
    }

    public void pack() {
        getFormControl().frame.getWindow().pack();
    }
}
