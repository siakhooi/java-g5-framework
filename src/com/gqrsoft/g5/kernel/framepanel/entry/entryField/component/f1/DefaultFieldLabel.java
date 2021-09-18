/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1;

import com.gqrsoft.g5.kernel.config.EntryFieldStyle;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultFieldLabel extends AbstractFieldLabel {

    @Override
    public void init() {
        String label = "EntryField.label.{0}";
        label = super.formControl.cmd.lang.getSystemString(
                label, super.entryField.field.i18nLabel);

        super.setText(label);
        super.setHorizontalAlignment(JLabel.TRAILING);
    }

    @Override
    public void refreshLook() {
        super.setFont(EntryFieldStyle.getLabelFont(
                entryField.display.mandatory));
        super.setBackground(
                EntryFieldStyle.getLabelBackgroundColor(
                entryField.display.mandatory));
        super.setVisible(entryField.display.visible);
    }
}
