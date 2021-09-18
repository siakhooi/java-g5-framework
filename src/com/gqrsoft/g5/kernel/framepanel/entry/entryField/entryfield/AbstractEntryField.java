/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.AbstractContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.AbstractAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f6keytypedcontrol.AbstractKeyTypedListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.AbstractValueListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.DefaultExternalVerifier;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.DefaultStatusOutput;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalEventInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.EntryFieldExternalStatusOutputInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.VerifyResult;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.util.ShowHelpFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.ValueInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.AbstractValueVerifier;
import java.util.Vector;
import javax.swing.JComponent;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractEntryField {

    public Field field;
    public FormControl formControl;
    //
    public JComponent label = null;
    public JComponent dataComponent = null;
    public JComponent selectButton = null;
    public AbstractContextMenu contextMenu = null;
    public AbstractAcceleratorKeyListener functionKey = null;
    public AbstractKeyTypedListener keyTyped = null;
    public AbstractValueListener valueListener = null;
    //
    public ValueInterface value = null;
    public AbstractValueVerifier valueverifier = null;
    //
    public EntryFieldDisplayControl display = null;
    //external component
    public EntryFieldExternalStatusOutputInterface externalStatusOutput = null;
    public EntryFieldExternalEventInterface externalEvent = null;
    public final Vector<GuiComponentInterface> allGuiComponents =
            new Vector<GuiComponentInterface>();

    public abstract void init();

    public abstract void setToInitValue();

    public void executeAction(EntryFieldEnum.FieldAction t) {
        switch (t) {
            case SHOWHELP:
                ShowHelpFactory.showHelp(formControl, this);
                break;
            case SHOWSELECT:
                break;
            case CUT:
                break;
            case COPY:
                break;
            case PASTE:
                break;
            case DELETE:
                break;
            case CLEARALL:
                break;
            case SELECTALL:
                break;
            case VERIFYVALUE:
                valueverifier.verify();
                if (display.valid) {
                    VerifyResult vr = externalEvent.verifyFieldValue(this.field.fieldName);
                    display.valid = vr.valid;
                    display.i18nErrorMessage = vr.i18nErrorMessage;
//                    display.valid = externalEvent.verifyFieldValue(this.field.fieldName);
//                    display.i18nErrorMessage = externalEvent.getI18nVerifyErrorMessage();
                }
                refreshAll();
                break;
        }
    }

    public void init(Field field, FormControl formControl) {
        this.field = field;
        this.formControl = formControl;
    }

    public AbstractEntryField() {
        display = new EntryFieldDisplayControl(this);
        externalStatusOutput = new DefaultStatusOutput();
        externalEvent = new DefaultExternalVerifier();
    }

    public final void initAll(FormControl fc, AbstractEntryField entryField) {
        display.setEditable(field.editable);
        display.setMandatory(field.mandatory);
        display.setVisible(field.visible);

        for (GuiComponentInterface i : allGuiComponents) {
            i.init(fc, entryField);
            i.init();
        }
    }

    public final void refreshAll() {
        for (GuiComponentInterface i : allGuiComponents) {
            i.refreshLook();
        }
    }
}
