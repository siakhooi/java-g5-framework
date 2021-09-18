/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.menu;

import com.gqrsoft.g5.developer.form.MenuForm;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuFormTreeNodeGenerator {

    public DefaultMutableTreeNode generateTreeNode(MenuForm mf, MenuTree mt) {
        DefaultMutableTreeNode rootTreeNode;
        MenuFormMenuItem root = mt.root;
        rootTreeNode = new DefaultMutableTreeNode(root);
        generate(rootTreeNode, root);
        return rootTreeNode;
    }

    private void generate(
            DefaultMutableTreeNode parentNode, MenuFormMenuItem parentItem) {
        for (int i = 0; i < parentItem.children.size(); i++) {
            MenuFormMenuItem c = parentItem.children.elementAt(i);
            if (c.type == MenuItemType.SEPARATOR) {
                continue;
            }
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(c);
            generate(n, c);
            parentNode.add(n);
        }
    }
}
