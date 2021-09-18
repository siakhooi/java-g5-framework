/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer_protected.tools.ButtonConfig;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractModeEntryForm extends EntryForm {
//formInit Mode
    public ModeEntryFormEnum.ModeAction enteringAction =
            ModeEntryFormEnum.ModeAction.SearchMode;
    public ModeEntryFormEnum.Mode currentMode;
    //during actions
    public boolean fieldVerification;
    public String i18nConfirmationText;
    public ModeEntryFormEnum.NextModeType nextModeType;
    //public ModeEntryFormEnum.ButtonConfiguration buttonConfiguration;
    public ButtonConfig buttonConfig;
    public ModeEntryFormEnum.ModeSaveProcess nextModeSaveProcess;
    public ModeEntryFormEnum.ModeLoadProcess nextModeLoadProcess;

    @Override
    public void initValue() {
    }

    /**
     * not in used.
     */
    @Override
    public final void onEscapeKeyPressed() {
    }
}
