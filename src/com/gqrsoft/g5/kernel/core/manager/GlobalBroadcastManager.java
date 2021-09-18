/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class GlobalBroadcastManager {

    private Vector<UserFormInterface> allForms = new Vector<UserFormInterface>();

    public void register(UserFormInterface form) {
        allForms.add(form);
    }

    public void unregister(UserFormInterface form) {
        allForms.remove(form);
    }

    public void broadcastSignal(int signalNumber, boolean block) {
        if (block) {
            broadcastSignalBlocking(signalNumber);
        } else {
            broadcastSignalNonBlocking(signalNumber);
        }
    }

    private void broadcastSignalBlocking(int signalNumber) {
        for (final UserFormInterface i : allForms) {
            i.receiveGlobalSignal(signalNumber);
        }
    }

    private void broadcastSignalNonBlocking(final int signalNumber) {
        for (final UserFormInterface i : allForms) {
            (new Thread() {

                @Override
                public void run() {
                    i.receiveGlobalSignal(signalNumber);
                }
            }).start();
        }
    }
}
