/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry;

import com.gqrsoft.g5.kernel.config.EntryFieldStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextRadioBox extends JPanel implements GuiComponentInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;
    private Vector<JRadioButton> allRadio;
    private ButtonGroup group = new ButtonGroup();

    public String getValue() {
        for (JRadioButton jr : allRadio) {
            if (jr.isSelected()) {
                return jr.getActionCommand();
            }
        }
        return null;
    }

    public int getValueIndex() {
        for (int i = 0; i < allRadio.size(); i++) {
            if (allRadio.elementAt(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    public boolean isNull() {
        return getValueIndex() == -1;
    }

    public void setValue(int value) {
        if (value < allRadio.size()) {
            allRadio.elementAt(value).setSelected(true);
        }
    }

    public void setValue(String value) {
        for (int i = 0; i < allRadio.size(); i++) {
            String a = allRadio.elementAt(i).getActionCommand();

            if (a.equals(value)) {
                allRadio.elementAt(i).setSelected(true);
                return;
            }
        }
    }

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    @Override
    public void init() {
        super.setLayout(new FlowLayout());
        int optionCount = entryField.field.optionI18nLabels.size();
        int m = entryField.field.optionValues.size();
        if (m < optionCount) {
            optionCount = m;
        } //use smallest
        allRadio = new Vector<JRadioButton>();

        for (int i = 0; i < optionCount; i++) {
            String i18nLabel = entryField.field.optionI18nLabels.elementAt(i);
            String value = entryField.field.optionValues.elementAt(i);

            JRadioButton r = new JRadioButton(i18nLabel);
            //r.setName(value);
            r.addFocusListener(entryField.valueListener);
            r.addMouseListener(new ContextMenuMouseListener(
                    entryField.contextMenu));
            r.addKeyListener(entryField.functionKey);

            //TODO: to clean
            r.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    JRadioButton r = (JRadioButton) e.getComponent();
                    //CONSOLE.println("mouseclick:"+r.getName() + ":" + r.isSelected());
                    entryField.executeAction(FieldAction.VERIFYVALUE);
                }
            });

            r.setActionCommand(value);
            if (i == 0) {
                r.setSelected(true);
            }
            r.setFont(EntryFieldStyle.getLabelFont(
                    entryField.display.mandatory));
            group.add(r);
            allRadio.add(r);
            super.add(r);
        }
    }

    @Override
    public void refreshLook() {
        super.setVisible(entryField.display.visible);

        for (int i = 0; i < allRadio.size(); i++) {
            JRadioButton r = allRadio.elementAt(i);
            r.setEnabled(entryField.display.editable);
            r.setVisible(entryField.display.visible);
//            r.setBackground(
//                    EntryFieldStyle.getFieldBackgroundColor(
//                    entryField.display.valid));
            //r.setOpaque(entryField.display.editable);
        }
    }
}
