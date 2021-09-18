/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import com.gqrsoft.g5.developer.publicobject.EntryFormEnum;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalStatusOutputInterface;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFormStatusBar extends JLabel implements EntryFieldExternalStatusOutputInterface {

    private FormControl formControl;
    private ModeEntryFormEnum.Mode mode = null;
    private String i18nMessage;
    private EntryFormEnum.StatusType statusType = EntryFormEnum.StatusType.INFO;

    public EntryFormStatusBar(FormControl formControl) {
        super(" ");
        this.formControl = formControl;
    }

    private void refresh() {
        String message = this.i18nMessage;
        if (NULL.isNull(this.i18nMessage)) {
            message = " ";
        }

        if (mode == null) {
            super.setText(message);
        } else {
            String fullMessage = "EntryForm.StatusBar.format.Mode{0}Message{1}";
            String m = mode.statusBarText;
            m = formControl.cmd.lang.getSystemString(m);
            fullMessage = formControl.cmd.lang.getSystemString(
                    fullMessage, m, message);
            super.setText(fullMessage);
        }
        super.setOpaque(true);
        super.setForeground(statusType.fg);
        super.setBackground(statusType.bg);
    }

    public void setMode(ModeEntryFormEnum.Mode mode) {
        this.mode = mode;
        refresh();
    }

    public void setStatus(String i18nMessage) {
        setStatus(EntryFormEnum.StatusType.INFO, i18nMessage);
    }

    public void setStatus(EntryFormEnum.StatusType type, String i18nMessage) {
        this.i18nMessage = i18nMessage;
        this.statusType = type;
        refresh();
    }
}
