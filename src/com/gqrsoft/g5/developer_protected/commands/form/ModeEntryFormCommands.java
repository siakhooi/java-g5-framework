/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeSystemButton;
import com.gqrsoft.g5.kernel.framepanel.button.G5JButton;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;

/**
 *
 * @author Ng Siak Hooi
 */
public class ModeEntryFormCommands extends AbstractCommandComponent {

    public void setAction(ModeSystemButton msb, ModeAction ma) {
        if (getFormControl().modeEntryForm != null) {
            G5JButton c = getFormControl().entryPanel.systemButtons.allButtonsByName.get(msb.name);
            boolean enabled = (ma != null);
            c.setEnabled(enabled);
            c.setModeAction(ma);
        }
    }

    public void executeAction(ModeAction ma) {
        if (getFormControl().modeEntryForm != null) {
            getFormControl().modeEntryPanel.executeModeAction(ma);
        }
    }
}
