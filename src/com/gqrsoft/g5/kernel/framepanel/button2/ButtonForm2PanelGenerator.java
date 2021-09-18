/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.button2;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.kernel.framepanel.menu.*;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonForm2PanelGenerator {

    public JPanel generate(ButtonForm2 mf) {
        MenuFormMenuItem root = mf.menuTree.root;
        MenuResult r = generate(root);
        return r.menuBar;
    }

    private JPanel getNewPanel(String title) {
        JPanel jp = new JPanel();
//        jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
        //jp.setAlignmentX(Component.CENTER_ALIGNMENT);

        jp.setLayout(new FlowLayout(FlowLayout.CENTER));


        return jp;
    }

    private class MenuResult {

        public JPanel menuBar;
        public JPanel menu;
        public JButton menuItem;
    }

    private JButton getNewButton(MenuFormMenuItem currentItem) {
        JButton jb = new JButton(currentItem.title);
        jb.setVerticalTextPosition(JButton.BOTTOM);
        jb.setHorizontalTextPosition(JButton.CENTER);
        jb.setName(currentItem.commandTitle);
        jb.setToolTipText(currentItem.description);
        jb.setIcon(currentItem.icon);
        jb.setEnabled(currentItem.enabled);
        return jb;
    }

    private MenuResult generate(final MenuFormMenuItem currentItem) {
        MenuResult result = new MenuResult();
        switch (currentItem.type) {
            case ROOT:
                result.menuBar = getNewPanel(currentItem.title);
                for (MenuFormMenuItem c : currentItem.children) {
                    MenuResult r = generate(c);
                    switch (c.type) {
                        case ROOT:
                            //should not be.
                            break;
                        case CATEGORY:
                        case ROLE:
                            result.menuBar.add(r.menu);
                            break;
                        case COMMAND:
                        case FORM:
                            result.menuBar.add(r.menuItem);
                            break;
                        case SEPARATOR:
                            result.menuBar.add(new JSeparator(SwingConstants.VERTICAL));
                            break;
                    }
                }
                break;
            case CATEGORY:
            case ROLE:
                result.menu = getNewPanel(currentItem.title);
                for (MenuFormMenuItem c : currentItem.children) {
                    MenuResult r = generate(c);
                    switch (c.type) {
                        case ROOT:
                            //should not be.
                            break;
                        case CATEGORY:
                        case ROLE:
                            result.menu.add(r.menu);
                            break;
                        case COMMAND:
                        case FORM:
                            result.menu.add(r.menuItem);
                            break;
                        case SEPARATOR:
                            result.menu.add(new JSeparator(SwingConstants.VERTICAL));
                            break;
                    }
                }
                break;
            case COMMAND:
                result.menuItem = getNewButton(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentItem.menuForm.cmd().form.broadcastSystemSignal(
                                MenuSignal.BUTTON,
                                currentItem.commandTitle);
                        currentItem.menuForm.itemActivated(
                                currentItem.title, currentItem.description);
                        currentItem.menuForm.commandActivated(
                                currentItem.commandTitle,
                                currentItem.title,
                                currentItem.description);
                    }
                });
                break;
            case SEPARATOR:
                //nothing to do.
                break;
            case FORM:
                result.menuItem = getNewButton(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserFormInterface i =
                                currentItem.menuForm.cmd.form.create(
                                currentItem.userForm);
                        currentItem.menuForm.cmd().form.broadcastSystemSignal(
                                MenuSignal.BUTTON,
                                currentItem.title);
                        currentItem.menuForm.itemActivated(
                                currentItem.title, currentItem.description);
                        currentItem.menuForm.beforeFormExecute(currentItem.userForm);
                        if (currentItem.newThread) {
                            currentItem.menuForm.cmd.form.executeInNewThread(i);
                        } else {
                            currentItem.menuForm.cmd.form.execute(i);
                        }
                        currentItem.menuForm.afterFormExecute(currentItem.userForm);
                    }
                });

                break;
        }
        return result;
    }
}
