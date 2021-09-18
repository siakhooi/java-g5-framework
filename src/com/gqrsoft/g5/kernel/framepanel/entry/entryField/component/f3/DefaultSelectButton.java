/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f3;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultSelectButton extends AbstractSelectButton {

    @Override
    public void init() {
        ImageIcon ic = formControl.cmd.image.resize(
                ImageIconResource.getEntryFieldLookupButton(),
                EngineConfiguration.Entry.DEFAULT_SELECT_BUTTON_IMAGE_HEIGHT);
        super.setIcon(ic);
        super.setFocusable(false);
        //selectField.setIconTextGap(0);
        super.setMargin(new Insets(1, 3, 3, 1));
        super.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                entryField.executeAction(FieldAction.SHOWSELECT);
            }
        });
    }

    @Override
    public void refreshLook() {
        super.setEnabled(entryField.display.editable);

        boolean isEditable = entryField.display.editable;
        boolean hasViewForm = entryField.field.viewFormInterface != null;
        boolean hasSelectForm = entryField.field.selectFormInterface != null;

        boolean editable = isEditable ? hasSelectForm : hasViewForm;
        super.setVisible(editable);
        super.setEnabled(editable);
        if (!entryField.display.visible) {
            super.setVisible(false);
        }

    }
}
