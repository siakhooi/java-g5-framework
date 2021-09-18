/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;

/**
 *
 * @author Ng Siak Hooi
 */
public class SignalCommands extends AbstractCommandComponent {

    public void register(UserFormInterface form) {
        getFormControl().core.gbc.register(form);
    }

    public void unregister(UserFormInterface form) {
        getFormControl().core.gbc.unregister(form);
    }

    public void broadcast(int signalNumber, boolean block) {
        getFormControl().core.gbc.broadcastSignal(signalNumber, block);
    }
}
