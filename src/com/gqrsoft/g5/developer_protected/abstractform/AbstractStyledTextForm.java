/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

/**
 * show documents using JEditorPane.
 * @author Ng Siak Hooi
 */
public abstract class AbstractStyledTextForm extends BlankForm {

    private JEditorPane je;
    private JLabel status;

    /**
     * 
     * @return
     */
    public abstract String getInitialText();

    /**
     * 
     * @return
     */
    public abstract FormEnum.StyledTextType getType();

    /**
     * 
     * @param hrefName
     */
    public abstract void hyperlinkClick(String hrefName);

    /**
     * 
     * @return
     */
    protected boolean showStatus() {
        return true;
    }

    /**
     * 
     * @return
     */
    protected boolean useScrollPane() {
        return false;
    }

    /**
     * 
     * @param newText
     */
    protected void setText(String newText) {
        je.setText(newText);
    }

    @Override
    public final void buildBlankForm(JPanel parent) {
        status = new JLabel(" ");
        je = new JEditorPane();
        je.setContentType(getType().styledTextType);
        je.setEditable(false);
        je.setFocusable(true);
        je.setText(getInitialText());
        je.addHyperlinkListener(new HyperlinkListener() {

            public void hyperlinkUpdate(HyperlinkEvent e) {
                String desc = e.getDescription();
                if (e.getEventType() == EventType.ACTIVATED) {
                    hyperlinkClick(desc);
                } else if (e.getEventType() == EventType.ENTERED) {
                    setI18nStatus(desc);
                } else if (e.getEventType() == EventType.EXITED) {
                    setI18nStatus("");
                }
            }
        });
        setStatus("");
        parent.setLayout(new BorderLayout());
        if (useScrollPane()) {
            JScrollPane jsp = new JScrollPane(je);
            //jsp.getViewport().scrollRectToVisible(new Rectangle(0, 0, 1, 1));
            parent.add(jsp, BorderLayout.CENTER);
        } else {
            parent.add(je, BorderLayout.CENTER);
        }
        if (showStatus()) {
            parent.add(status, BorderLayout.PAGE_END);
        }
    }

    /**
     * 
     * @param i1nMessage
     */
    protected final void setI18nStatus(String i1nMessage) {
        if (cmd.data.isNull(i1nMessage)) {
            status.setText(" ");
        } else {
            status.setText(i1nMessage);
        }
    }

    /**
     * 
     * @param message
     */
    protected final void setStatus(String message) {
        if (cmd.data.isNull(message)) {
            status.setText(" ");
        } else {
            message = cmd.lang.getString(message);
            status.setText(message);
        }
    }
}
