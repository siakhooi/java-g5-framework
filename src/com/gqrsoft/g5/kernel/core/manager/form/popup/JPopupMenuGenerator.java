/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.form.popup;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuFormMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class JPopupMenuGenerator {

    public JPopupMenu generate(MenuForm mf) {
        MenuFormMenuItem root = mf.menuTree.root;
        MenuResult r = generate(root);
        return r.menuBar;
    }

    private JMenuItem getJMenuItem(MenuFormMenuItem currentItem) {
        JMenuItem itm = new JMenuItem(currentItem.title);
        itm.setIcon(currentItem.icon);
        itm.setEnabled(currentItem.enabled);
        itm.setToolTipText(currentItem.description);
        itm.setName(currentItem.commandTitle);
        return itm;
    }

    private class MenuResult {

        public JPopupMenu menuBar;
        public JMenu menu;
        public JMenuItem menuItem;
    }

    private MenuResult generate(final MenuFormMenuItem currentItem) {
        MenuResult result = new MenuResult();
        switch (currentItem.type) {
            case ROOT:
                result.menuBar = new JPopupMenu();
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
                            result.menuBar.addSeparator();
                            break;
                    }
                }
                break;
            case CATEGORY:
            case ROLE:
                result.menu = new JMenu(currentItem.title);
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
                            result.menu.addSeparator();
                            break;
                    }
                }
                break;
            case COMMAND:
                result.menuItem = getJMenuItem(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentItem.menuForm.cmd().form.broadcastSystemSignal(
                                MenuSignal.POPUPMENU,
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
                result.menuItem = getJMenuItem(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserFormInterface i =
                                currentItem.menuForm.cmd.form.create(
                                currentItem.userForm);
                        currentItem.menuForm.cmd().form.broadcastSystemSignal(
                                MenuSignal.POPUPMENU,
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
