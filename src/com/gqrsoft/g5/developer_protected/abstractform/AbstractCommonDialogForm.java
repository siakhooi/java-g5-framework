/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractCommonDialogForm extends BlankForm {

    /**
     * 
     */
    protected JOptionPane optionPane;

    /**
     * 
     */
    protected abstract void init();

    /**
     * 
     * @param result
     */
    protected abstract void userAction(int result);

    /**
     * 
     * @param messageType
     */
    protected final void setMessageType(FormEnum.DialogMessageType messageType) {
        optionPane.setMessageType(messageType.messageType);
    }

    /**
     * 
     * @param i18nMessage
     */
    protected final void setMessage(String i18nMessage) {
        optionPane.setMessage(i18nMessage);
    }

    /**
     * 
     * @param dialogOptionType
     */
    protected final void setOptionType(FormEnum.DialogOptionType dialogOptionType) {
        optionPane.setOptionType(dialogOptionType.optionType);
    }

    @Override
    public final void buildBlankForm(JPanel parent) {
        optionPane = new JOptionPane();
        init();
        optionPane.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent e) {
                if (e.getSource() == optionPane) {
                    String prop = e.getPropertyName();
                    if (prop.equals(JOptionPane.VALUE_PROPERTY)) {
                        Object o = e.getNewValue();
                        //DEBUG.println("classtype:" + e.getNewValue().getClass());
                        if (o instanceof Integer) {
                            Integer i = (Integer) o;
                            int result = i.intValue();
                            userAction(result);
                        }
                    }
                }
            }
        });
        parent.setLayout(new BorderLayout());
        parent.add(optionPane, BorderLayout.CENTER);
    }
}
