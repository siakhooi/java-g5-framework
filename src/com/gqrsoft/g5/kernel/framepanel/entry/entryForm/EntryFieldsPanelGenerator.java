/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalStatusOutputInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalEventInterface;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldsPanelGenerator {

    public static void executeEmbedded(
            FormControl formControl, EntryFieldGroup efg) {
        JPanel jp = formControl.cmd.form.createEmbedded(
                formControl.cmd.form.create(efg.form));
        efg.generatedPanel = jp;
    }

    public static void execute(FormControl formControl,
            EntryFieldGroup efg,
            EntryFieldExternalStatusOutputInterface status,
            EntryFieldExternalEventInterface externalverifier) {
        JPanel parent = new JPanel();
        if (efg.allFieldsByIndex.size() == 0) {
            return;
        } //nothing to do;
        int maxColumnCount = efg.columns;
        efg.allEntryFieldsByName.clear();
        efg.allEntryFieldsByIndex.clear();

        int colCounter = 0;
        parent.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 4;
        gbc.ipady = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

//        for (String fieldName : efg.allFieldsByName.keySet()) {
//            Field uf = efg.allFieldsByName.get(fieldName);
        for (Field uf : efg.allFieldsByIndex) {
            String fieldName = uf.fieldName;
            AbstractEntryField comp = EntryFieldFactory.createEntryField(formControl, uf);
            if (comp == null) {
                continue;
            }
            efg.allEntryFieldsByName.put(fieldName, comp);
            efg.allEntryFieldsByIndex.add(comp);
            comp.externalStatusOutput = status;
            comp.externalEvent = externalverifier;

            JComponent labelField = comp.label;
            JComponent dataField = comp.dataComponent;
            JComponent selectButton = comp.selectButton;

            JPanel labelPanel = new JPanel();
            JPanel dataPanel = new JPanel();
            EntryFieldComponentLayoutFactory.layoutLabel(
                    labelPanel, labelField);
            EntryFieldComponentLayoutFactory.layoutDataField(
                    dataPanel, dataField, selectButton);
            if (!uf.visible) {
                continue;
            }

            int colSpan = uf.entryFormColumnSpan;
            gbc.gridwidth = 1;
            parent.add(labelPanel, gbc);
            gbc.gridx++;
            if (colSpan > 1) {
                gbc.gridwidth = colSpan * 2 - 1;
            }
            parent.add(dataPanel, gbc);
            if (colSpan > 1) {
                gbc.gridx += (colSpan * 2 - 1);
                colCounter += colSpan;
            } else {
                gbc.gridx++;
                colCounter++;
            }
            if (colCounter >= maxColumnCount) {
                colCounter = 0;
                gbc.gridy++;
                gbc.gridx = 0;
            }
        }
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout(FlowLayout.LEADING));
        jp1.add(parent);
        efg.generatedPanel = jp1;
//getPanel().setLayout(new FlowLayout(FlowLayout.LEADING));
    //getPanel().add(parent); 

//        efg.generatedPanel = parent;
    }
}
