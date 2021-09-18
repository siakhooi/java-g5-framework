/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.kernel.framepanel.menu.MenuFormTreeNodeGenerator;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuFormMenuItem;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuItemCellRenderer;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuTree;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.form.MenuForm;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class MenuFormFramePanel extends AbstractFramePanel {

    private JTree tree;
    private JScrollPane treeView;
    private JLabel lblStatus;

    @Override
    public void initPanel() {
        lblStatus = new JLabel(" ");
    }

    @Override
    public void onInEnter() {
        MenuForm mf = (MenuForm) (getFormControl().userForm);
        mf.build();
        MenuTree mt = mf.menuTree;
        MenuFormTreeNodeGenerator treeNodeGenerator =
                new MenuFormTreeNodeGenerator();
        DefaultMutableTreeNode treeNode =
                treeNodeGenerator.generateTreeNode(mf, mt);

        //some setup here
        //TODO: default expand
        tree = new JTree(treeNode);
        tree.setRowHeight(mf.getMenuItemHeight());
        tree.setRootVisible(false);

        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setCellRenderer(new MenuItemCellRenderer(mf));
        tree.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    DefaultMutableTreeNode n =
                            (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (n == null) {
                        return;
                    }
                    MenuFormMenuItem r = (MenuFormMenuItem) n.getUserObject();
                    menuItemActivated(r);
                }
            }
        });
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
//                    DefaultMutableTreeNode n =
//                            (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//                    if (n == null) {
//                        return;
//                    }
//                    MenuItem r = (MenuItem) n.getUserObject();
                    TreePath tp = tree.getPathForLocation(e.getX(), e.getY());
                    if (tp == null) {
                        return;
                    }
                    DefaultMutableTreeNode n1 =
                            (DefaultMutableTreeNode) tp.getLastPathComponent();
                    if (n1 == null) {
                        return;
                    }
                    MenuFormMenuItem r = (MenuFormMenuItem) n1.getUserObject();
                    setStatus(r.description);
                    menuItemSelected(r);

                } else if (e.getClickCount() == 2) {
//                    DefaultMutableTreeNode n =
//                            (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//                    if (n == null) {
//                        return;
//                    }
//                    MenuItem r = (MenuItem) n.getUserObject();
                    TreePath tp = tree.getPathForLocation(e.getX(), e.getY());
                    if (tp == null) {
                        return;
                    }
                    DefaultMutableTreeNode n1 =
                            (DefaultMutableTreeNode) tp.getLastPathComponent();
                    if (n1 == null) {
                        return;
                    }
                    MenuFormMenuItem r = (MenuFormMenuItem) n1.getUserObject();
                    menuItemActivated(r);
                }
            }
        });

        treeView = new JScrollPane(tree);
        getPanel().setLayout(new BorderLayout());
        getPanel().add(treeView, BorderLayout.CENTER);
        getPanel().add(lblStatus, BorderLayout.PAGE_END);
        expandFirstLevel();
    }

    private void expandFirstLevel() {
        int n = tree.getRowCount();
        for (int i = n - 1; i >= 0; i--) {
            tree.expandRow(i);
        }
    }

    private void menuItemSelected(MenuFormMenuItem r) {
        r.menuForm.itemSelected(r.title, r.description);
    }

    private void menuItemActivated(MenuFormMenuItem r) {
        switch (r.type) {
            case ROOT:
            case ROLE:
            case CATEGORY:
            case SEPARATOR:
                break;
            case FORM:
                UserFormInterface i =
                        getFormControl().core.form.create(r.userForm);
                r.menuForm.itemActivated(r.title, r.description);
                r.menuForm.beforeFormExecute(r.userForm);
                if (r.newThread) {
                    getFormControl().cmd.form.executeInNewThread(i);
                } else {
                    getFormControl().cmd.form.execute(i);
                }
                r.menuForm.afterFormExecute(r.userForm);
                break;
            case COMMAND:
                r.menuForm.itemActivated(r.title, r.description);
                r.menuForm.commandActivated(r.commandTitle, r.title, r.description);
                break;
            }
    }

    private void setStatus(String s) {
        lblStatus.setText(s);
    }

    @Override
    public void onEscapeKeyPressed() {
        getFormControl().userForm.onEscapeKeyPressed();
    }

    @Override
    public void onOutExit() {
    }

    @Override
    public void onInExit() {
    }

    @Override
    public void onOutEnter() {
    }
}
