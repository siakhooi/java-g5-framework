/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.button;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuTree;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonPanelGenerator {

    private FormControl formControl;
    private ButtonsBuilder applicationButtons;
    private ButtonEventInterface applicationEventInterface;
    private ButtonsBuilder systemButtons;
    private ButtonSystemEventInterface systemEventInterface;
    private JPanel buttonPanel;

    public JPanel generate(
            FormControl formControl,
            ButtonsBuilder applicationButtons,
            ButtonEventInterface applicationEventInterface,
            ButtonsBuilder systemButtons,
            ButtonSystemEventInterface systemEventInterface) {
        this.formControl = formControl;
        this.applicationButtons = applicationButtons;
        this.applicationEventInterface = applicationEventInterface;
        this.systemButtons = systemButtons;
        this.systemEventInterface = systemEventInterface;

        createPanel();
        boolean hasSystemButtons = false;
        boolean hasApplicationButtons = false;
        if (systemButtons != null) {
            if (systemButtons.allComponents.size() > 0) {
                hasSystemButtons = true;
            }
        }
        if (applicationButtons != null) {
            if (applicationButtons.allComponents.size() > 0) {
                hasApplicationButtons = true;
            }
        }
        if (hasSystemButtons) {
            createSystemButtons();
        }
        if (hasSystemButtons && hasApplicationButtons) {
            buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        }
        if (hasApplicationButtons) {
            createApplicationButtons();
        }
        return buttonPanel;

    }

    private void createApplicationButtons() {
        for (String name : applicationButtons.allButtonsByName.keySet()) {
            final JButton jb = applicationButtons.allButtonsByName.get(name);
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    applicationEventInterface.buttonClick(jb.getName());
                }
                });

            jb.getActionMap().put(jb.getName(), new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    applicationEventInterface.buttonClick(jb.getName());
                }
                });
        }

        for (int i = 0; i < applicationButtons.allComponents.size(); i++) {
            Component jc = applicationButtons.allComponents.elementAt(i);
            buttonPanel.add(jc);
        }

        if (applicationButtons.defaultButton != null) {
            formControl.cmd.frame.setDefaultButton(applicationButtons.defaultButton);
        }
    }

    private void createPanel() {
        buttonPanel = new JPanel();
        FlowLayout f1 = new FlowLayout(
                applicationButtons.horizontalLocation.flowLayoutValue);
//        BoxLayout f2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);

        buttonPanel.setLayout(f1);
    }

    private void createSystemButtons() {
        for (String name : systemButtons.allButtonsByName.keySet()) {
            final JButton jb = systemButtons.allButtonsByName.get(name);
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    systemEventInterface.systemButtonClick(jb.getName());
                }
                });

            jb.getActionMap().put(jb.getName(), new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    systemEventInterface.systemButtonClick(jb.getName());
                }
                });
        }
        for (int i = 0; i < systemButtons.allComponents.size(); i++) {
            Component jc = systemButtons.allComponents.elementAt(i);
            buttonPanel.add(jc);
        }
        if (systemButtons.defaultButton != null) {
            formControl.cmd.frame.setDefaultButton(systemButtons.defaultButton);
        }
    }
}
