/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.frame;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.core.Core;
import com.gqrsoft.g5.kernel.frame.FrameInterface;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import com.gqrsoft.g5.kernel.framepanel.ListFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;
import com.gqrsoft.g5.developer_protected.commands.Commands;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.kernel.framepanel.EntryFormFramePanel;
import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer_secret.commands.CommandComponentFormControlSetter;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.control.ThreadControl;
import com.gqrsoft.g5.kernel.framepanel.ModeEntryFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ReportEntryFormFramePanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormInitializer {

    public static void initCommand(FormControl fc) {
        Commands cmd = new Commands();
        //cmd.setFormControl(fc);
        CommandComponentFormControlSetter.setFormControl(cmd, fc);
        fc.cmd = cmd;
        fc.userForm.setCommands(cmd);

        fc.uniqueSessionId = cmd.random.getAlphaNumericString(
                EngineConfiguration.Form.FORM_UNIQUE_SESSION_ID_LENGTH);
    }

    public static void initFormControl(FormControl fc,
            Core core, FrameInterface parent,
            UserFormInterface userForm, ThreadControl tc) {
        fc.parentFrame = parent;
        fc.core = core;
        fc.userForm = userForm;
        if (userForm instanceof ProcessForm) {
            fc.processForm = (ProcessForm) userForm;
        }
        if (userForm instanceof ListForm) {
            fc.listForm = (ListForm) userForm;
        }
        if (userForm instanceof EntryForm) {
            fc.entryForm = (EntryForm) userForm;
        }
        if (userForm instanceof ReportEntryForm) {
            fc.reportEntryForm = (ReportEntryForm) userForm;
        }
        if (userForm instanceof ModeEntryForm) {
            fc.modeEntryForm = (ModeEntryForm) userForm;
        }
        fc.threadControl = tc;
    }

    public static void initFrame(FormControl fc, FrameInterface frame, boolean newThread) {
        frame.setFormControl(fc);
        fc.frame = frame;
        if (newThread) {
            fc.threadControl.root = frame;
        }
    }

    public static void initPanel(FormControl fc, FramePanelInterface panel) {
        panel.setFormControl(fc);
        fc.panel = panel;
        if (fc.userForm instanceof ProcessForm) {
            fc.processPanel = (ProcessFormFramePanel) panel;
        }
        if (fc.userForm instanceof ListForm) {
            fc.listPanel = (ListFormFramePanel) panel;
        }
        if (fc.userForm instanceof EntryForm) {
            fc.entryPanel = (EntryFormFramePanel) panel;
        }
        if (fc.userForm instanceof ReportEntryForm) {
            fc.reportEntryPanel = (ReportEntryFormFramePanel) panel;
        }
        if (fc.userForm instanceof ModeEntryForm) {
            fc.modeEntryPanel = (ModeEntryFormFramePanel) panel;
        }
    }
}
