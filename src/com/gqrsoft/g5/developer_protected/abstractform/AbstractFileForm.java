/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractFileForm extends BlankForm {

    private JFileChooser fileChooser;
    private FileFilter firstFilter = null;

    /**
     * 
     * @return
     */
    protected abstract boolean hideHiddenFile();

    /**
     * 
     * @return
     */
    protected abstract boolean allowAllFileFilter();

    /**
     * 
     */
    protected abstract void init();

    /**
     * 
     * @param openType
     */
    protected final void setDialogOpenType(boolean openType) {
        fileChooser.setDialogType(
                openType
                ? JFileChooser.OPEN_DIALOG
                : JFileChooser.SAVE_DIALOG);
    }

    /**
     * 
     * @param b
     */
    protected final void allowMultiSelection(boolean b) {
        fileChooser.setMultiSelectionEnabled(b);
    }

    /**
     * 
     * @param t
     */
    protected final void setSelectionMode(FormEnum.FileSelectType t) {
        fileChooser.setFileSelectionMode(t.fileSelectType);
    }

    /**
     * 
     * @param filter
     */
    protected final void addFileFilter(FileFilter filter) {
        fileChooser.addChoosableFileFilter(filter);
        if (firstFilter == null) {
            firstFilter = filter;
        }
    }

    /**
     * 
     * @param desc
     * @param exts
     */
    protected final void addFileFilter(String desc, String[] exts) {
        desc = cmd.lang.getString(desc);
        FileNameExtensionFilter f = new FileNameExtensionFilter(desc, exts);
        addFileFilter(f);
    }

    /**
     * 
     * @param desc
     * @param ext
     */
    protected final void addFileFilter(String desc, String ext) {
        desc = cmd.lang.getString(desc);
        FileNameExtensionFilter f = new FileNameExtensionFilter(desc, ext);
        addFileFilter(f);
    }

    private File getCurrentDirectory() {
        if (cmd.in.fileValue != null) {
            return cmd.in.fileValue;
        }
        if (!cmd.data.isNull(cmd.in.stringValue)) {
            return new File(cmd.in.stringValue);
        }
        return new File(cmd.sysprop.userDirectory());
    }

    @Override
    public final void buildBlankForm(JPanel parent) {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(getCurrentDirectory());
        fileChooser.resetChoosableFileFilters();
        firstFilter = null;
        init();

        boolean b = hideHiddenFile();
        fileChooser.setFileHidingEnabled(b);

        b = allowAllFileFilter();
        fileChooser.setAcceptAllFileFilterUsed(b);

        if (firstFilter != null) {
            fileChooser.setFileFilter(firstFilter);
        }

        fileChooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String a = e.getActionCommand();
                if (a.equals(JFileChooser.APPROVE_SELECTION)) {
                    cmd.in.booleanValue = true;
                    cmd.in.fileValue = fileChooser.getSelectedFile();
                    cmd.in.fileValues = fileChooser.getSelectedFiles();
                    cmd.in.stringValue = cmd.in.fileValue.getAbsolutePath();
                    cmd.in.stringValues = new String[cmd.in.fileValues.length];
                    for (int i = 0; i < cmd.in.fileValues.length; i++) {
                        cmd.in.stringValues[i] = cmd.in.fileValues[i].getAbsolutePath();
                    }
                    cmd.form.closeForm();
                } else {
                    cmd.in.booleanValue = false;
                    cmd.in.fileValue = null;
                    cmd.in.fileValues = null;
                    cmd.form.closeForm();
                }
            }
        });
        parent.setLayout(new BorderLayout());
        parent.add(fileChooser, BorderLayout.CENTER);
    }

    @Override
    public final void onEscapeKeyPressed() {
        cmd.in.booleanValue = false;
        cmd.in.fileValue = null;
        cmd.in.fileValues = null;
        cmd.form.closeForm();
    }
}
