/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.blank;

import com.gqrsoft.g5.developer.form.BlankForm;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloWorld extends BlankForm {

    @Override
    public String getFormTitle() {
        return "HelloWorld.title";
    }

    @Override
    public void buildBlankForm(JPanel parent) {
        parent.setLayout(new BorderLayout());

        JLabel lblMain = new JLabel("Hello World!");
        lblMain.setHorizontalAlignment(JLabel.CENTER);
        JButton jb = new JButton("Close");
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cmd.form.closeForm();
            }
        });
        JLabel lblHeading = new JLabel("Hello World! is a BlankForm demo.");
        lblHeading.setHorizontalAlignment(JLabel.CENTER);

        parent.add(lblMain, BorderLayout.CENTER);
        parent.add(lblHeading, BorderLayout.PAGE_START);
        parent.add(jb, BorderLayout.PAGE_END);
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
