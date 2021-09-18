/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.frame;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.developer.form.ButtonForm;
import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.framepanel.BlankFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ButtonForm2FramePanel;
import com.gqrsoft.g5.kernel.framepanel.ButtonFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.EntryFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import com.gqrsoft.g5.kernel.framepanel.ListFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.MenuFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ModeEntryFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ReportEntryFormFramePanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class FramePanelSelector {

    public FramePanelInterface getFramePanel(UserFormInterface userForm) {
        FramePanelInterface panel = null;
        if (userForm instanceof ButtonForm) {
            panel = new ButtonFormFramePanel();
        } else if (userForm instanceof BlankForm) {
            panel = new BlankFormFramePanel();
        } else if (userForm instanceof ButtonForm2) {
            panel = new ButtonForm2FramePanel();
        } else if (userForm instanceof MenuForm) {
            panel = new MenuFormFramePanel();
        } else if (userForm instanceof ProcessForm) {
            panel = new ProcessFormFramePanel();
        } else if (userForm instanceof ListForm) {
            panel = new ListFormFramePanel();
        } else if (userForm instanceof ReportEntryForm) {
            panel = new ReportEntryFormFramePanel();
        } else if (userForm instanceof ModeEntryForm) {
            panel = new ModeEntryFormFramePanel();
        } else if (userForm instanceof EntryForm) {
            panel = new EntryFormFramePanel();
        }
        return panel;
    }
}
