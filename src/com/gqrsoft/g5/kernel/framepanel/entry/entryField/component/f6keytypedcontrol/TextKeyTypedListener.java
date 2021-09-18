/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f6keytypedcontrol;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.TextDataEntryInterface;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextKeyTypedListener extends AbstractKeyTypedListener {

    private TextDataEntryInterface textField;

    public TextKeyTypedListener(TextDataEntryInterface textField) {
        this.textField = textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_BACK_SPACE) {
            return;
        }
        char ec = e.getKeyChar();
        //check maxlength
        int width = entryField.field.maximumLength;
        int valueLength = textField.getCurrentValueLength();
        int selectedValueLength = textField.getSelectedValueLength();
        if (valueLength >= width && width > 0 && selectedValueLength == 0) {
            e.consume();
            return; //no more use
        }
        //check allowchars
        String allowChars = entryField.field.allowCharacters;
        if (allowChars != null) {
            if (allowChars.length() != 0) {
                if (allowChars.indexOf((int) ec) == -1) {
                    e.consume();
                    return; // no more use
                }
            }
        }
        //check casetype
        switch (entryField.field.textCaseType) {
            case LOWER:
                e.setKeyChar(toLower(ec));
                break;
            case UPPER:
                e.setKeyChar(toUpper(ec));
                break;
            case MIX:
                break;
        }
    }

    private char toLower(char ec) {
        return Character.toLowerCase(ec);
    }

    private char toUpper(char ec) {
        return Character.toUpperCase(ec);
    }
}
