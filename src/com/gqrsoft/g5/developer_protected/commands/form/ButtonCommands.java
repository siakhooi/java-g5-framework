/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer.publicobject.ReportEntryFormEnum;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonCommands extends AbstractCommandComponent {

    private void setEnabled(ButtonsBuilder sb, String id, boolean value) {
        sb.allButtonsByName.get(id).setEnabled(value);
    }

    public void setEnabled(String id, boolean value) {
        if (getFormControl().listForm != null) {
            setEnabled(getFormControl().listForm.buttons, id, value);
        } else if (getFormControl().modeEntryForm != null) {
            setEnabled(getFormControl().modeEntryForm.buttons, id, value);
        } else if (getFormControl().reportEntryForm != null) {
            setEnabled(getFormControl().reportEntryForm.buttons, id, value);
        } else if (getFormControl().entryForm != null) {
            setEnabled(getFormControl().entryForm.buttons, id, value);
        }
    }

    public void setEnabled(ListFormEnum.ListSystemButton sb, boolean value) {
        if (getFormControl().listPanel != null) {
            setEnabled(getFormControl().listPanel.systemButtons, sb.name, value);
        }
    }

    public void setEnabled(ReportEntryFormEnum.ReportSystemButton sb, boolean value) {
        if (getFormControl().reportEntryForm != null) {
            setEnabled(getFormControl().entryPanel.systemButtons, sb.name, value);
        }
    }

    public void setEnabled(ModeEntryFormEnum.ModeSystemButton sb, boolean value) {
        if (getFormControl().modeEntryForm != null) {
            setEnabled(getFormControl().entryPanel.systemButtons, sb.name, value);
        }
    }
}
