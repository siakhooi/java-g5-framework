/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.abstractform;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractFieldForm;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonEventInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldGroup;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PrivateAbstractEntryForm extends AbstractFieldForm implements ButtonEventInterface {

    public final Vector<Field> allFieldsByIndex = new Vector<Field>();
    public final HashMap<String, Field> allFieldsByName = new HashMap<String, Field>();
    //
    public final EntryFieldGroup keyEntryFieldGroup = new EntryFieldGroup();
    public final LinkedHashMap<String, EntryFieldGroup> allTab =
            new LinkedHashMap<String, EntryFieldGroup>();
    //    
    public EntryFieldGroup currentEntryFieldGroup;
}
