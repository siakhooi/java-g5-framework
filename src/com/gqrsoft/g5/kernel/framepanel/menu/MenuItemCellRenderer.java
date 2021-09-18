/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.menu;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuItemCellRenderer extends DefaultTreeCellRenderer {

    ImageIcon imgRoleOpen;
    ImageIcon imgRoleClose;
    ImageIcon imgCatOpen;
    ImageIcon imgCatClose;
    ImageIcon imgRoot;
    ImageIcon imgApp;
    ImageIcon imgCommand;
    private MenuForm menuForm;

    public MenuItemCellRenderer(MenuForm menuForm) {
        this.menuForm = menuForm;
        this.imgRoleOpen = ImageIconResource.getMenuRoleOpenIcon();
        this.imgRoleClose = ImageIconResource.getMenuRoleCloseIcon();
        this.imgApp = ImageIconResource.getMenuApplicationIcon();
        this.imgRoot = ImageIconResource.getMenuRootIcon();
        this.imgCommand = ImageIconResource.getMenuCommandIcon();
        this.imgCatOpen = ImageIconResource.getMenuCategoryOpenIcon();
        this.imgCatClose = ImageIconResource.getMenuCategoryCloseIcon();

        this.imgRoleOpen = IMAGE.resize(imgRoleOpen, menuForm.getMenuItemHeight());
        this.imgRoleClose = IMAGE.resize(imgRoleClose, menuForm.getMenuItemHeight());
        this.imgApp = IMAGE.resize(imgApp, menuForm.getMenuItemHeight());
        this.imgRoot = IMAGE.resize(imgRoot, menuForm.getMenuItemHeight());
        this.imgCommand = IMAGE.resize(imgCommand, menuForm.getMenuItemHeight());
        this.imgCatOpen = IMAGE.resize(imgCatOpen, menuForm.getMenuItemHeight());
        this.imgCatClose = IMAGE.resize(imgCatClose, menuForm.getMenuItemHeight());
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);
        final MenuFormMenuItem i = (MenuFormMenuItem) ((DefaultMutableTreeNode) value).getUserObject();
        switch (i.type) {
            case ROOT:
                setIcon(imgRoot);
                break;
            case ROLE:
                setIcon(expanded ? imgRoleOpen : imgRoleClose);
                break;
            case CATEGORY:
                setIcon(expanded ? imgCatOpen : imgCatClose);
                break;
            case FORM:
                setIcon(imgApp);
                break;
            case COMMAND:
                setIcon(imgCommand);
                break;
            case SEPARATOR:
                return this;
        }
        setOpaque(false);
        setToolTipText(i.description);
        Font f = menuForm.getMenuItemFont();
        if (f != null) {
            setFont(f);
        }
        return this;
    }
}
