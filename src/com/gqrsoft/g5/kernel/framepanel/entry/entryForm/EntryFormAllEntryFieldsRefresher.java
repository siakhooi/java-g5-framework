/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public class EntryFormAllEntryFieldsRefresher {

    public static void init(EntryForm form) {
        EntryFieldGroup i = form.keyEntryFieldGroup;
        Vector<AbstractEntryField> v = i.allEntryFieldsByIndex;
        for (AbstractEntryField p : v) {
            p.refreshAll();
        }
        HashMap<String, EntryFieldGroup> b = form.allTab;
        for (EntryFieldGroup c : b.values()) {
            v = c.allEntryFieldsByIndex;
            for (AbstractEntryField p : v) {
                p.refreshAll();
            }
        }
    }
}
