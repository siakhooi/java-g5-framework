/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.publicform.ActionForm_OKCancel;
import com.gqrsoft.g5.developer_secret.form.SelectionFormButtonForm;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractSelectionForm extends BlankForm {

    private Vector<String> selectedValues;
    private Map<String, String> allValues;
    private Map<String, JToggleButton> allButtons;
    private JPanel selectionPanel;
    private ButtonGroup group = new ButtonGroup();

    protected abstract void userClick(String name);

    protected abstract boolean isMultiSelect();

    protected abstract int getColumn();

    protected abstract void buildSelection();

    protected abstract void userCancelled();

    protected abstract void userSelected(Vector<String> selectedValues);

    protected final void use(
            Map<String, String> allValues,
            Vector<String> selectedValues) {
        this.allValues = allValues;
        this.selectedValues = selectedValues;
    }

    private class ButtonActionListener implements ActionListener {

        public String name;

        @Override
        public void actionPerformed(ActionEvent e) {
            userClick(name);
        }
    }

    protected final boolean getSelected(String name) {
        if (allButtons.containsKey(name)) {
            return allButtons.get(name).isSelected();
        } else {
            return false;
        }
    }

    private void addComponent(String value, String label) {
        if (isMultiSelect()) {

            JCheckBox cb = new JCheckBox(label);
            cb.setName(value);
            cb.setSelected(selectedValues.contains(value));
            ButtonActionListener ba = new ButtonActionListener();
            ba.name = value;
            cb.addActionListener(ba);

            allButtons.put(value, cb);
            selectionPanel.add(cb);
        } else {
            JRadioButton rb = new JRadioButton(label);
            rb.setName(value);
            rb.setSelected(selectedValues.contains(value));
            ButtonActionListener ba = new ButtonActionListener();
            ba.name = value;
            rb.addActionListener(ba);

            group.add(rb);
            allButtons.put(value, rb);
            selectionPanel.add(rb);
        }
    }

    private void buildSelectionForm() {
        int m = getColumn();
        int n = allValues.size() / m;
        if (n * m < allValues.size()) {
            n++;
        }
        selectionPanel.setLayout(new GridLayout(n, m));
        for (String value : allValues.keySet()) {
            String label = allValues.get(value);
            addComponent(value, label);
        }
    }

//    @Override
//    public final Class<? extends UserFormInterface> getBottomForm() {
//        return ActionForm_OKCancel.class;
//    }
    @Override
    public void buildBlankForm(JPanel parent) {
        buildSelection();
        selectionPanel = new JPanel();
        allButtons = new HashMap<String, JToggleButton>();
        buildSelectionForm();
        SelectionFormButtonForm f = new SelectionFormButtonForm();
        f.enabledSelectAll = isMultiSelect();
        f.enabledSelectNone = isMultiSelect();
        JPanel b = cmd.form.createEmbedded(f);
        parent.setLayout(new BorderLayout());
        parent.add(selectionPanel, BorderLayout.PAGE_START);
        parent.add(b, BorderLayout.PAGE_END);

//        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
//        String label = "SelectionForm.ok.label";
//        super.buttons.addButton("ok", label, icon, true);
//        icon = cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight());
//        label = "SelectionForm.cancel.label";
//        super.buttons.addButton("cancel", label, icon, false);
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.BUTTON) {
            selectedValues = new Vector<String>();
            if (signalCode.equals(SelectionFormButtonForm.BUTTON_OK)) {
                for (JToggleButton jb : allButtons.values()) {
                    if (jb.isSelected()) {
                        selectedValues.add(jb.getName());
                    }
                }
                userSelected(selectedValues);
            } else if (signalCode.equals(SelectionFormButtonForm.BUTTON_CANCEL)) {
                userCancelled();
            } else if (signalCode.equals(SelectionFormButtonForm.BUTTON_SELECTALL)) {
                for (JToggleButton tb : allButtons.values()) {
                    tb.setSelected(true);
                }
            } else if (signalCode.equals(SelectionFormButtonForm.BUTTON_SELECTNONE)) {
                for (JToggleButton tb : allButtons.values()) {
                    tb.setSelected(false);
                }
            }
        }
    }

    @Override
    public void onEscapeKeyPressed() {
        userCancelled();
    }
}
