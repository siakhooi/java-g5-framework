/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.systray;

import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuFormMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ng Siak Hooi
 */
public class SysTrayPopupMenuGenerator {

    public PopupMenu generate(SystemTrayMenuForm userForm) {
        MenuFormMenuItem root = userForm.menuTree.root;
        MenuResult r = generate(root);
        return r.popupMenu;

    }

    private MenuItem getMenuItem(MenuFormMenuItem currentItem) {
        MenuItem itm = new MenuItem(currentItem.title);
        //itm.setIcon(currentItem.icon);
        itm.setEnabled(currentItem.enabled);
        //itm.setToolTipText(currentItem.description);
        itm.setName(currentItem.commandTitle);
        return itm;
    }

    private class MenuResult {
//
        public java.awt.PopupMenu popupMenu;
        public java.awt.Menu menu;
        public java.awt.MenuItem menuItem;
    }

    private MenuResult generate(final MenuFormMenuItem currentItem) {
        MenuResult result = new MenuResult();
        switch (currentItem.type) {
            case ROOT:
                result.popupMenu = new PopupMenu();
                for (MenuFormMenuItem c : currentItem.children) {
                    MenuResult r = generate(c);
                    switch (c.type) {
                        case ROOT:
                            //should not be.
                            break;
                        case CATEGORY:
                        case ROLE:
                            result.popupMenu.add(r.menu);
                            break;
                        case COMMAND:
                        case FORM:
                            result.popupMenu.add(r.menuItem);
                            break;
                        case SEPARATOR:
                            result.popupMenu.addSeparator();
                            break;
                    }
                }
                break;
            case CATEGORY:
            case ROLE:
                result.menu = new Menu(currentItem.title);
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
                result.menuItem = getMenuItem(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
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
                break;
            case FORM:
                result.menuItem = getMenuItem(currentItem);
                result.menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserFormInterface i =
                                currentItem.menuForm.cmd.form.create(
                                currentItem.userForm);
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
