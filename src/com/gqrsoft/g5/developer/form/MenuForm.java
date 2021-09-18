/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractMenuForm;
import java.awt.Font;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class MenuForm extends AbstractMenuForm {

    @Override
    public Font getMenuItemFont() {
        return null;
    }

    @Override
    public int getMenuItemHeight() {
        return 16;
    }

    @Override
    public abstract void build();

    @Override
    public void beforeFormExecute(Class<? extends UserFormInterface> form) {
    }

    @Override
    public void afterFormExecute(Class<? extends UserFormInterface> form) {
    }

    @Override
    public void commandActivated(String name, String title, String description) {
    }

    @Override
    public void itemSelected(String title, String description) {
    }

    @Override
    public void itemActivated(String title, String description) {
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    public void onEscapeKeyPressed() {
    }
}
