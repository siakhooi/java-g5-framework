/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.form.toolbar;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuFormMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Ng Siak Hooi
 */
public class JToolBarGenerator {

    private class MenuResult {

        public JToolBar menuBar;
        //public JToolBar menu;
        public JButton menuItem;
    }

    public JToolBar generate(MenuForm mf) {
        MenuFormMenuItem root = mf.menuTree.root;
        MenuResult r = generate(root, new MenuResult());
        return r.menuBar;

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

    private MenuResult generate(final MenuFormMenuItem currentItem, MenuResult parent) {
        MenuResult result = new MenuResult();
        result.menuBar = parent.menuBar;
        switch (currentItem.type) {
            case ROOT:
                result.menuBar = new JToolBar();
                for (MenuFormMenuItem c : currentItem.children) {
                    MenuResult r = generate(c, result);
                    switch (c.type) {
                        case ROOT:
                            //should not be.
                            break;
                        case CATEGORY:
                        case ROLE:
                            //result.menuBar.add(r.menu);
                            result.menuBar.addSeparator();
                            break;
                        case COMMAND:
                        case FORM:
                            result.menuBar.add(r.menuItem);
                            break;
                        case SEPARATOR:
                            result.menuBar.addSeparator();
                            break;
                    }
                }
                break;
            case CATEGORY:
            case ROLE:
                //result.menu = new JMenuBar(currentItem.title);
                //result.menu = new JPopupMenu();

                for (MenuFormMenuItem c : currentItem.children) {
                    MenuResult r = generate(c, result);
                    switch (c.type) {
                        case ROOT:
                            //should not be.
                            break;
                        case CATEGORY:
                        case ROLE:
                            //result.menu.add(r.menu);
                            result.menuBar.addSeparator();
                            break;
                        case COMMAND:
                        case FORM:
                            result.menuBar.add(r.menuItem);
                            break;
                        case SEPARATOR:
                            result.menuBar.addSeparator();
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
                                MenuSignal.TOOLBAR,
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
                                MenuSignal.TOOLBAR,
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
