/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.menu;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuTree {

    public MenuFormMenuItem root;
    MenuFormMenuItem current;
    AbstractMenuForm menuForm;
    MenuFormMenuItem currentItem;

    public MenuTree(AbstractMenuForm menuForm) {
        this.menuForm = menuForm;
        root = new MenuFormMenuItem(menuForm);
        root.type = MenuItemType.ROOT;
        root.menuForm = this.menuForm;
        current = root;
    }

    public void openRole(String i18nTitle, String i18nDescription) {
        MenuFormMenuItem i = new MenuFormMenuItem(menuForm);
        i.parent = current;
        i.type = MenuItemType.ROLE;
        i.title = i18nTitle;
        i.description = i18nDescription;
        current.addChild(i);
        current = i;
        currentItem = current;
    }

    public void closeRole() {
        current = current.parent;
        currentItem = current;
    }

    public void openCategory(String i18nTitle, String i18nDescription) {
        MenuFormMenuItem i = new MenuFormMenuItem(menuForm);
        i.parent = current;
        i.type = MenuItemType.CATEGORY;
        i.title = i18nTitle;
        i.description = i18nDescription;
        current.addChild(i);
        current = i;
        currentItem = current;
    }

    public void closeCategory() {
        current = current.parent;
        currentItem = current;
    }

    public void addApplication(Class<? extends UserFormInterface> userForm, String i18nTitle, String i18nDescription, boolean newThread) {
        MenuFormMenuItem i = new MenuFormMenuItem(menuForm);
        i.parent = current;
        i.type = MenuItemType.FORM;
        i.title = i18nTitle;
        i.description = i18nDescription;
        i.userForm = userForm;
        i.newThread = newThread;
        current.addChild(i);
        currentItem = i;
    }

    public void addApplication(String name, String i18nTitle, String i18nDescription) {
        MenuFormMenuItem i = new MenuFormMenuItem(menuForm);
        i.parent = current;
        i.type = MenuItemType.COMMAND;
        i.title = i18nTitle;
        i.description = i18nDescription;
        i.commandTitle = name;
        current.addChild(i);
        currentItem = i;
    }

    public void addSeparator() {
        MenuFormMenuItem i = new MenuFormMenuItem(menuForm);
        i.parent = current;
        i.type = MenuItemType.SEPARATOR;
        i.title = "";
        i.description = "";
        current.addChild(i);
        currentItem = i;
    }

    public void setEnabled(boolean enabled) {
        currentItem.enabled = enabled;
    }

    public void setIcon(Icon icon) {
        currentItem.icon = icon;
    }
}
