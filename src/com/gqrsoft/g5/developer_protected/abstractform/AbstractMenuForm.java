/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_secret.abstractform.PrivateAbstractMenuForm;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuTree;
import java.awt.Font;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractMenuForm extends PrivateAbstractMenuForm {

    public AbstractMenuForm() {
        super.menuTree = new MenuTree(this);
    }

    /**
     * setup all menu items.
     */
    public abstract void build();

    /**
     * event when Command type menu items is activated.
     * @param commandTitle
     * @param title 
     * @param description 
     */
    public abstract void commandActivated(String commandTitle, String title, String description);

    public abstract void itemSelected(String title, String description);

    public abstract void itemActivated(String title, String description);

    public abstract void beforeFormExecute(Class<? extends UserFormInterface> form);

    public abstract void afterFormExecute(Class<? extends UserFormInterface> form);

    /**
     * Font to be used in Menu Item for form display.
     * not used for <code>JMenuBar</code> display.
     * @return
     */
    public abstract Font getMenuItemFont();

    /**
     * <code>ImageIcon</code> height for Menu Item in form display.
     * not used for <code>JMenuBar</code> display.
     * @return
     */
    public abstract int getMenuItemHeight();

    /**
     * create a role tree in menu.
     * @param i18nTitle
     * @param i18nDescription
     */
    protected final void openI18nRole(String i18nTitle, String i18nDescription) {
        menuTree.openRole(i18nTitle, i18nDescription);
    }

    protected final void openRole(String title, String description) {
        title = cmd.lang.getString(title);
        description = cmd.lang.getString(description);
        openI18nRole(title, description);
    }

    /**
     * close a role tree in menu
     */
    protected final void closeRole() {
        menuTree.closeRole();
    }

    /**
     * create a category tree in menu.
     * @param i18nTitle
     * @param i18nDescription
     */
    protected final void openI18nCategory(String i18nTitle, String i18nDescription) {
        menuTree.openCategory(i18nTitle, i18nDescription);
    }

    protected final void openCategory(String title, String description) {
        title = cmd.lang.getString(title);
        description = cmd.lang.getString(description);
        openI18nCategory(title, description);
    }

    /**
     * close a category tree in menu.
     */
    protected final void closeCategory() {
        menuTree.closeCategory();
    }

    /**
     * add a userForm based application as menu item of 
     * current category/role tree.
     * @param userForm
     * @param i18nTitle
     * @param i18nDescription
     */
    protected final void addI18nApplication(
            Class<? extends UserFormInterface> userForm,
            String i18nTitle,
            String i18nDescription) {
        addI18nApplication(userForm, i18nTitle, i18nDescription, true);
    }

    protected final void addApplication(
            Class<? extends UserFormInterface> userForm,
            String title,
            String description) {
        addApplication(userForm, title, description, true);
    }

    protected final void addI18nApplication(
            Class<? extends UserFormInterface> userForm,
            String i18nTitle,
            String i18nDescription,
            boolean newThread) {
        menuTree.addApplication(userForm, i18nTitle, i18nDescription, newThread);
    }

    protected final void addApplication(
            Class<? extends UserFormInterface> userForm,
            String title,
            String description,
            boolean newThread) {
        title = cmd.lang.getString(title);
        description = cmd.lang.getString(description);
        addI18nApplication(userForm, title, description, newThread);
    }

    /**
     * add a command typed application as menu item of
     * current category/role tree.
     * @param name
     * @param i18nTitle
     * @param i18nDescription
     */
    protected final void addI18nApplication(
            String name,
            String i18nTitle,
            String i18nDescription) {
        menuTree.addApplication(name, i18nTitle, i18nDescription);
    }

    protected final void addApplication(
            String name,
            String title,
            String description) {
        title = cmd.lang.getString(title);
        description = cmd.lang.getString(description);
        addI18nApplication(name, title, description);
    }

    protected final void addSeparator() {
        menuTree.addSeparator();
    }
    protected final void setIcon(Icon icon){
        menuTree.setIcon(icon);
    }
    protected final void setEnabled(boolean enabled){
        menuTree.setEnabled(enabled);
    }
}
