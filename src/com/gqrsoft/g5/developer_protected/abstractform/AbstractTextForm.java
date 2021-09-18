/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.ButtonForm;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public abstract class AbstractTextForm extends ButtonForm {

    protected JTextArea textArea;
    private JScrollPane sp;

    /**
     * setup <code>JTextArea</code> component.
     */
    protected abstract void init();

    protected abstract void initButton();

    /**
     * set the number of rows in JTextArea to be visible.
     * @param n
     */
    protected final void setRows(int n) {
        textArea.setRows(n);
    }

    /**
     * set the number of columns in JTextArea to be visible.
     * @param n
     */
    protected final void setColumns(int n) {
        textArea.setColumns(n);
    }

    /**
     * wrap line if longer than the <code>Columns</code>.
     * @param i
     */
    protected final void setLineWrap(boolean i) {
        textArea.setLineWrap(i);
    }

    /**
     * set style of line wrap.
     * @param i
     */
    protected final void setWrapStyleWord(boolean i) {
        textArea.setWrapStyleWord(i);
    }

    /**
     * allow user to edit the text in JTextArea.
     * @param i
     */
    protected final void setEditable(boolean i) {
        textArea.setEditable(i);
        textArea.setOpaque(i);
    }

    /**
     * set the text to be display in JTextArea.
     * @param i
     */
    protected final void setText(String i) {
        textArea.setText(i);
    }

    @Override
    public final void buildButtonForm(JPanel parent) {
        textArea = new JTextArea();
        init();
        textArea.setCaretPosition(0);
        sp = new JScrollPane(textArea);

        initButton();

        parent.setLayout(new BorderLayout());
        parent.add(sp, BorderLayout.CENTER);
    }

}
