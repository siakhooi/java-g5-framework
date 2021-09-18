/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.control;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.core.Core;
import com.gqrsoft.g5.kernel.frame.FrameInterface;
import com.gqrsoft.g5.kernel.framepanel.EntryFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import com.gqrsoft.g5.kernel.framepanel.ListFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ModeEntryFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;
import com.gqrsoft.g5.kernel.framepanel.ReportEntryFormFramePanel;
import com.gqrsoft.g5.developer_protected.commands.Commands;
import com.gqrsoft.g5.developer_protected.parameters.FormParameters;
import java.util.Vector;
import javax.swing.JMenuBar;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormControl {

    public Core core;
    public FrameInterface frame;
    public FrameInterface parentFrame;
    public FramePanelInterface panel;
    public UserFormInterface userForm;
    public ThreadControl threadControl;
    //
    public Commands cmd;
    public FormParameters in;
    public FormParameters out;
    public FormParameters local;
    public JMenuBar menu;
    public String uniqueSessionId;
    public Vector<UserFormInterface> allEmbedded; //include the main form
    //===
    public ProcessFormFramePanel processPanel;
    public ListFormFramePanel listPanel;
    public EntryFormFramePanel entryPanel;
    public ReportEntryFormFramePanel reportEntryPanel;
    public ModeEntryFormFramePanel modeEntryPanel;
    //
    public ProcessForm processForm;
    public ListForm listForm;
    public EntryForm entryForm;
    public ReportEntryForm reportEntryForm;
    public ModeEntryForm modeEntryForm;
}
