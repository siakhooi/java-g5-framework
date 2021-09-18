/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldGroup {

    public String tabName;
    public String tabI18nDescription;
    public EntryFormEnum.TabType type;
    //
    public Class<? extends UserFormInterface> form;
    //
    public int columns = 0;
    public Vector<Field> allFieldsByIndex = new Vector<Field>();
    public HashMap<String, Field> allFieldsByName = new HashMap<String, Field>();
    //
    public JPanel generatedPanel = null;
    public Vector<AbstractEntryField> allEntryFieldsByIndex = new Vector<AbstractEntryField>();
    public HashMap<String, AbstractEntryField> allEntryFieldsByName = new HashMap<String, AbstractEntryField>();
    //
    public void add(Field i) {
        allFieldsByIndex.add(i);
        allFieldsByName.put(i.fieldName, i);

    }
//    public void setColumns(int value) {
//        this.columns = value;
//    }
}
