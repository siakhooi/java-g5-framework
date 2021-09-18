/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.frame;

import com.gqrsoft.g5.kernel.config.DefaultStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 *
 * @author Ng Siak Hooi
 */
public class G5Frame implements FrameInterface {

    private FormControl formControl;
    private JFrame window;

    @Override
    public FormControl getFormControl() {
        return this.formControl;
    }

    @Override
    public void setFormControl(FormControl value) {
        this.formControl = value;
    }

    @Override
    public void initStep40(
            FramePanelInterface left,
            FramePanelInterface right,
            FramePanelInterface top,
            FramePanelInterface bottom) {
        if (left != null) {
            window.getContentPane().add(left.getPanel(), BorderLayout.LINE_START);
        }
        if (right != null) {
            window.getContentPane().add(right.getPanel(), BorderLayout.LINE_END);
        }
        if (top != null) {
            window.getContentPane().add(top.getPanel(), BorderLayout.PAGE_START);
        }
        if (bottom != null) {
            window.getContentPane().add(bottom.getPanel(), BorderLayout.PAGE_END);
        }
    }

    @Override
    public void initStep30(boolean modal) {
        String windowTitle =
                WindowTitleGenerator.getWindowTitle(
                getFormControl().core,
                getFormControl().userForm);
        FrameInterface parent = formControl.parentFrame;

        window = new JFrame();
        window.setTitle(windowTitle);
        window.setIconImage(
                formControl.core.info().getApplicationIcon().getImage());
        if (formControl.menu != null) {
            window.setJMenuBar(formControl.menu);
        }
        window.setBackground(
                DefaultStyle.getWindowBackgroundColor());
        window.getContentPane().setBackground(
                DefaultStyle.getWindowBackgroundColor());
////////////////
//        window.getContentPane().add(formControl.panel.getPanel());
        window.getContentPane().setLayout(new BorderLayout());
        window.getContentPane().add(formControl.panel.getPanel(), BorderLayout.CENTER);

////////////////
        formControl.core.win.addFrame(this);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        formControl.panel.onEscapeKeyPressed();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                    //formControl.core.win.closeFrame(formControl.frame);
                    }
                });

        WindowKeySetter.setupEscapeKey(this, window.getRootPane());
    }

    @Override
    public void show() {
        window.pack();
        WindowPositionSetter.setPosition(window, formControl.parentFrame);
        window.setVisible(true);
    }

    @Override
    public void close() {
        if (!formControl.core.win.confirmExitFrame(this)) {
            return;
        }
        getFormControl().panel.onInExit();
        getFormControl().userForm.onInExit();
        getFormControl().userForm.exitThread();
        //standalone, exit won't back to parent
        //getFormControl().parentFrame.getFormControl().panel.onOutEnter();
        //getFormControl().parentFrame.getFormControl().userForm.onOutEnter();
        formControl.core.win.closeFrame(this);
        window.setVisible(false);
        //window.removeAll();
        window.dispose();
    }

    @Override
    public boolean isJFrame() {
        return true;
    }

    @Override
    public boolean isJDialog() {
        return false;
    }

    @Override
    public JFrame getJFrame() {
        return window;
    }

    @Override
    public JDialog getJDialog() {
        return null;
    }

    @Override
    public Window getWindow() {
        return window;
    }

    @Override
    public void initStep41(JToolBar toolbar) {
        if (toolbar != null) {
            window.getContentPane().add(toolbar, BorderLayout.PAGE_START);
        }
    }
}
