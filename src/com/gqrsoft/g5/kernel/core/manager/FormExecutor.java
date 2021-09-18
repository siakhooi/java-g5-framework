/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.form.FormHideable;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.control.ThreadControl;
import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.core.manager.frame.FormInitializer;
import com.gqrsoft.g5.kernel.core.manager.frame.FramePanelSelector;
import com.gqrsoft.g5.kernel.core.manager.frame.FrameSelector;
import com.gqrsoft.g5.kernel.frame.FrameInterface;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import com.gqrsoft.g5.kernel.framepanel.menu.JMenuBarGenerator;
import com.gqrsoft.g5.developer_protected.parameters.FormParameters;
import com.gqrsoft.g5.kernel.core.manager.form.popup.JPopupMenuGenerator;
import com.gqrsoft.g5.kernel.core.manager.form.toolbar.JToolBarGenerator;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormExecutor extends AbstractCoreComponent {

    FramePanelSelector ps = new FramePanelSelector();
    FrameSelector fs = new FrameSelector();

    public FramePanelInterface createEmbedded(
            FormControl parentFc, UserFormInterface userForm) {

        //set Parent
        FormControl childFc = new FormControl();
        childFc.in = parentFc.in;
        childFc.out = parentFc.out;
        childFc.local = parentFc.local;
        childFc.frame = parentFc.frame;
        childFc.allEmbedded = parentFc.allEmbedded;
        FormInitializer.initFormControl(childFc,
                super.core(), parentFc.parentFrame,
                userForm, parentFc.threadControl);

        FormInitializer.initCommand(childFc);

        parentFc.allEmbedded.add(userForm);

        FramePanelInterface panel = null;
        panel = ps.getFramePanel(userForm);
        FormInitializer.initPanel(childFc, panel);

        panel.initPanel0();
        userForm.initThread(false);
        userForm.initForm();
        panel.initPanel();
        panel.onInEnter();
        userForm.onInEnter();
        panel.eventBeforeVisible();
        userForm.eventBeforeVisible();
        panel.eventAfterVisible();
        userForm.eventAfterVisible();
        return panel;
    }

    public void execute(
            FrameInterface parent, UserFormInterface userForm,
            boolean modal, boolean newThread) {

        if (parent != null) {
            parent.getFormControl().panel.onOutExit();
            parent.getFormControl().userForm.onOutExit();
        }

        boolean showForm = true;
        if (userForm instanceof FormHideable) {
            showForm = ((FormHideable) userForm).showThisForm();
        }

        ThreadControl tc;
        if (newThread) {
            tc = new ThreadControl();
        } else {
            tc = parent.getFormControl().threadControl;
        }

        //set Parent
        FormControl fc = new FormControl();
        if (parent != null) {
            fc.in = parent.getFormControl().out;
        } else {
            fc.in = new FormParameters();
        }
        fc.out = new FormParameters();
        fc.local = new FormParameters(); //GlobalParameters();

        FormInitializer.initFormControl(fc,
                super.core(), parent, userForm, tc);

        FormInitializer.initCommand(fc);

        fc.allEmbedded = new Vector<UserFormInterface>();
        fc.allEmbedded.add(userForm);

        FramePanelInterface panel = null;
        FrameInterface frame = null;
        panel = ps.getFramePanel(userForm);
        FormInitializer.initPanel(fc, panel);

        if (showForm) {
            frame = fs.getFrame(parent != null, newThread);
            FormInitializer.initFrame(fc, frame, newThread);
        }

        generateMenu(fc, userForm);
        panel.initPanel0();
        userForm.initThread(newThread);
        userForm.initForm();
        panel.initPanel();
        if (showForm) {
            frame.initStep30(modal);
        }
        panel.initPanel30();

        FramePanelInterface right = null;
        FramePanelInterface left = null;
        FramePanelInterface top = null;
        FramePanelInterface bottom = null;

        if (userForm.getRightForm() != null) {
            right = createEmbedded(fc, create(userForm.getRightForm()));
        }
        if (userForm.getLeftForm() != null) {
            left = createEmbedded(fc, create(userForm.getLeftForm()));
        }
        if (userForm.getTopForm() != null) {
            top = createEmbedded(fc, create(userForm.getTopForm()));
        }
        if (userForm.getBottomForm() != null) {
            bottom = createEmbedded(fc, create(userForm.getBottomForm()));
        }
        JToolBar toolbar = generateToolbar(fc, userForm.getMenuFormForToolbar());

        if (showForm) {
            frame.initStep40(left, right, top, bottom);
            frame.initStep41(toolbar);
        }
        panel.onInEnter();
        userForm.onInEnter();

        if (showForm) {
            panel.eventBeforeVisible();
            userForm.eventBeforeVisible();

            if (modal) {
                panel.eventAfterVisible();
                userForm.eventAfterVisible();
            }
            frame.show();
            if (!modal) {
                panel.eventAfterVisible();
                userForm.eventAfterVisible();
            }
        }
    }

    private JToolBar generateToolbar(FormControl fc, Class<? extends MenuForm> mf1) {
        if (mf1 == null) {
            return null;
        }
        MenuForm mf = (MenuForm) create(mf1);
        FormControl mfc = new FormControl();
//set Parent
        FormInitializer.initFormControl(mfc, super.core(),
                fc.parentFrame, mf, fc.threadControl);

        mfc.cmd = fc.cmd;
        mf.setCommands(mfc.cmd);
        mfc.uniqueSessionId = fc.cmd.random.getAlphaNumericString(
                EngineConfiguration.Form.FORM_UNIQUE_SESSION_ID_LENGTH);

        mfc.allEmbedded = fc.allEmbedded;
        mfc.allEmbedded.add(mf);

        mfc.in = fc.in;
        mfc.out = new FormParameters();

        mf.initThread(false);
        mf.initForm();
        mf.build();
        mf.onInEnter();

        JToolBarGenerator jToolBarGenerator = new JToolBarGenerator();

        return jToolBarGenerator.generate(mf);
    }

    private void generateMenu(FormControl fc, UserFormInterface userForm) {
        Class<? extends MenuForm> mf1 = userForm.getMenuForm();
        if (mf1 == null) {
            fc.menu = null;
            return;
        }
        MenuForm mf = (MenuForm) create(mf1);
        FormControl mfc = new FormControl();
//set Parent
        FormInitializer.initFormControl(mfc, super.core(),
                fc.parentFrame, mf, fc.threadControl);

        mfc.cmd = fc.cmd;
        mf.setCommands(mfc.cmd);
        mfc.uniqueSessionId = fc.cmd.random.getAlphaNumericString(
                EngineConfiguration.Form.FORM_UNIQUE_SESSION_ID_LENGTH);

        mfc.allEmbedded = fc.allEmbedded;
        mfc.allEmbedded.add(mf);

        mfc.in = fc.in;
        mfc.out = new FormParameters();

        mf.initThread(false);
        mf.initForm();
        mf.build();
        mf.onInEnter();

        JMenuBarGenerator jMenuBarGenerator = new JMenuBarGenerator();

        fc.menu = jMenuBarGenerator.generate(mf);
    }

    public UserFormInterface create(Class<? extends UserFormInterface> form) {
        try {
            return form.newInstance();
        } catch (InstantiationException ex) {
            String msg = "FormExecutor.create.error.InstantiationException";
            msg = core().lang.getSystemString(msg);
            core().log.getEngineLogger().log(Level.SEVERE, msg, ex);
        } catch (IllegalAccessException ex) {
            String msg = "FormExecutor.create.error.IllegalAccessException";
            msg = core().lang.getSystemString(msg);
            core().log.getEngineLogger().log(Level.SEVERE, msg, ex);
        }
        return null;
    }
    public JPopupMenu generatePopupMenu(FormControl fc, MenuForm mf) {
        if (mf == null) {
            return null;
        }
        FormControl mfc = new FormControl();
//set Parent
        FormInitializer.initFormControl(mfc, super.core(),
                fc.parentFrame, mf, fc.threadControl);

        mfc.cmd = fc.cmd;
        mf.setCommands(mfc.cmd);
        mfc.uniqueSessionId = fc.cmd.random.getAlphaNumericString(
                EngineConfiguration.Form.FORM_UNIQUE_SESSION_ID_LENGTH);

        mfc.allEmbedded = fc.allEmbedded;
        mfc.allEmbedded.add(mf);

        mfc.in = fc.in;
        mfc.out = new FormParameters();

        mf.initThread(false);
        mf.initForm();
        mf.build();
        mf.onInEnter();

        JPopupMenuGenerator generator = new JPopupMenuGenerator();

        return generator.generate(mf);
    }    
}
