/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry;

import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import java.awt.Color;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColorFormattedTextField extends AbstractFormattedTextField {

    @Override
    public void refreshLook() {
        super.refreshLook();
//        setBackground(Color.WHITE);
        if (entryField.display.valid) {
            try {
                Color c = entryField.value.getColor();
                if (c != null) {
                    setForeground(c);
                }
            } catch (EntryFieldWrongDataTypeException ex) {
            }
        } else {

        }
    }
}
