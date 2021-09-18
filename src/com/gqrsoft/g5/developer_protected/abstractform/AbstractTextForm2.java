/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.publicform.PopupForm_TextEditable;
import com.gqrsoft.g5.developer.publicform.PopupForm_TextReadOnly;
import com.gqrsoft.g5.developer_secret.tools.TextArea2Vector;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractTextForm2 extends BlankForm {

    protected JTextArea textArea;
    private JScrollPane sp;

    /**
     * setup <code>JTextArea</code> component.
     */
    protected abstract void init();

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
        setPopupMenu();
    }

    /**
     * set the text to be display in JTextArea.
     * @param i
     */
    protected final void setText(String i) {
        textArea.setText(i);
        goTop();
    }

    protected final String getText() {
        return textArea.getText();
    }

    protected final Vector<String> getVector() throws BadLocationException {
        return TextArea2Vector.convert(textArea, true);
    }

    private void setPopupMenu() {
        if (textArea.isEditable()) {
            cmd.form.attachPopupMenu(textArea, new PopupForm_TextEditable());
        } else {
            cmd.form.attachPopupMenu(textArea, new PopupForm_TextReadOnly());
        }
    }

    private void goTop(){
//        sp.getVerticalScrollBar().setValue(0);

        textArea.setSelectionStart(0);
        textArea.setSelectionEnd(0);

//        int x = textArea.getPreferredSize().width;
//        sp.getViewport().setViewPosition(new Point(x, 0));

//        sp.getViewport().setViewPosition(new Point(0, 0));

        //sp.scrollRectToVisible(new Rectangle(0, 0, 3, 3));
        
    }
    @Override
    public final void buildBlankForm(JPanel parent) {
        textArea = new JTextArea();
        init();
        textArea.setCaretPosition(0);
        setPopupMenu();
        sp = new JScrollPane(textArea);

        parent.setLayout(new BorderLayout());
        parent.add(sp, BorderLayout.CENTER);
        goTop();
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.POPUPMENU) {
            if (signalCode.equals(PopupForm_TextEditable.MENU_CUT)) {
                textArea.cut();
            } else if (signalCode.equals(PopupForm_TextEditable.MENU_COPY)) {
                textArea.copy();
            } else if (signalCode.equals(PopupForm_TextEditable.MENU_PASTE)) {
                textArea.paste();
            } else if (signalCode.equals(PopupForm_TextEditable.MENU_DELETE)) {
                textArea.replaceSelection("");
            } else if (signalCode.equals(PopupForm_TextEditable.MENU_CLEARALL)) {
                textArea.selectAll();
                textArea.replaceSelection("");
            } else if (signalCode.equals(PopupForm_TextEditable.MENU_SELECTALL)) {
                textArea.selectAll();
            }
        }
    }
}
