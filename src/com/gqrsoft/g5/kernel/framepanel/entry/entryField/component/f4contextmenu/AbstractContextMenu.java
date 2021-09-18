/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractContextMenu
        extends JPopupMenu implements GuiComponentInterface {

    private JMenuItem mnuHelp;
    private JMenuItem mnuValueSelector;
    private JMenuItem mnuCut;
    private JMenuItem mnuCopy;
    private JMenuItem mnuPaste;
    private JMenuItem mnuDelete;
    private JMenuItem mnuClearAll;
    private JMenuItem mnuSelectAll;
    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
        initContextMenu();
    }

    public void setEnability(
            boolean isHelp,
            boolean isValueSelector,
            boolean isCut,
            boolean isCopy,
            boolean isPaste,
            boolean isDelete,
            boolean isClearAll,
            boolean isSelectAll) {
        mnuHelp.setEnabled(isHelp);
        mnuValueSelector.setEnabled(isValueSelector);
        mnuCut.setEnabled(isCut);
        mnuCopy.setEnabled(isCopy);
        mnuPaste.setEnabled(isPaste);
        mnuDelete.setEnabled(isDelete);
        mnuClearAll.setEnabled(isClearAll);
        mnuSelectAll.setEnabled(isSelectAll);
    }

    private void initContextMenu() {
        String label = "EntryField.ContextMenu.label";
        label = formControl.cmd.lang.getSystemString(label);
        super.setLabel(label);
        initMenuHelp();
        initMenuValueSelector();
        initMenuCut();
        initMenuCopy();
        initMenuPaste();
        initMenuDelete();
        initMenuClearAll();
        initMenuSelectAll();

        super.add(mnuHelp);
        super.add(mnuValueSelector);
        super.addSeparator();
        super.add(mnuCut);
        super.add(mnuCopy);
        super.add(mnuPaste);
        super.add(mnuDelete);
        super.addSeparator();
        super.add(mnuClearAll);
        super.add(mnuSelectAll);

        setEnability(false, false, false, false,
                false, false, false, false);
    }

    private JMenuItem createMenuItem(
            String label, ImageIcon icon,
            int keycode, int modifiers,
            final EntryFieldEnum.FieldAction action) {
        label = formControl.cmd.lang.getSystemString(label);
        JMenuItem m = new JMenuItem(label);
        icon = IMAGE.resize(icon,
                EngineConfiguration.Entry.DEFAULT_CONTEXT_MENU_IMAGE_HEIGHT);

        m.setIcon(icon);
        if (keycode != 0 || modifiers != 0) {
            m.setAccelerator(KeyStroke.getKeyStroke(keycode, modifiers));
        }
        m.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                entryField.executeAction(action);
            }
        });
        return m;
    }

    private void initMenuHelp() {
        mnuHelp = createMenuItem(
                "EntryField.ContextMenu.HelpMessage",
                ImageIconResource.getContextMenuHelpIcon(),
                KeyEvent.VK_F1, 0,
                EntryFieldEnum.FieldAction.SHOWHELP);
    }

    private void initMenuValueSelector() {
        mnuValueSelector = createMenuItem(
                "EntryField.ContextMenu.SelectValue",
                ImageIconResource.getContextMenuLookupIcon(),
                KeyEvent.VK_F2, 0,
                EntryFieldEnum.FieldAction.SHOWSELECT);
    }

    private void initMenuCut() {
        mnuCut = createMenuItem(
                "EntryField.ContextMenu.Cut",
                ImageIconResource.getContextMenuCutIcon(),
                KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK,
                EntryFieldEnum.FieldAction.CUT);
    }

    private void initMenuCopy() {
        mnuCopy = createMenuItem(
                "EntryField.ContextMenu.Copy",
                ImageIconResource.getContextMenuCopyIcon(),
                KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK,
                EntryFieldEnum.FieldAction.COPY);
    }

    private void initMenuPaste() {
        mnuPaste = createMenuItem(
                "EntryField.ContextMenu.Paste",
                ImageIconResource.getContextMenuPasteIcon(),
                KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK,
                EntryFieldEnum.FieldAction.PASTE);
    }

    private void initMenuDelete() {
        mnuDelete = createMenuItem(
                "EntryField.ContextMenu.Delete",
                ImageIconResource.getContextMenuDeleteIcon(),
                KeyEvent.VK_DELETE, 0,
                EntryFieldEnum.FieldAction.DELETE);
    }

    private void initMenuClearAll() {
        mnuClearAll = createMenuItem(
                "EntryField.ContextMenu.ClearAll",
                ImageIconResource.getContextMenuClearAllIcon(),
                0, 0,
                EntryFieldEnum.FieldAction.CLEARALL);
    }

    private void initMenuSelectAll() {
        mnuSelectAll = createMenuItem(
                "EntryField.ContextMenu.SelectAll",
                ImageIconResource.getContextMenuSelectAllIcon(),
                KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK,
                EntryFieldEnum.FieldAction.SELECTALL);
    }
}
