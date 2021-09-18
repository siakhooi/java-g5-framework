/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.abstractform;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractFieldForm;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonEventInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.list.ListModel;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PrivateAbstractListForm extends AbstractFieldForm implements ButtonEventInterface {

    public final ListModel model = new ListModel();
    public Object[][] array;
    public ResultSet resultset;
    public Vector<Vector<Object>> vector;
    public final Vector<Field> allFieldsByIndex =
            new Vector<Field>();
    public final Vector<Field> allKeyFieldsByIndex =
            new Vector<Field>();
    public final Vector<Field> allDataFieldsByIndex =
            new Vector<Field>();
    public final HashMap<String, Field> allFieldsByName =
            new HashMap<String, Field>();
    public final HashMap<String, Field> allKeyFieldsByName =
            new HashMap<String, Field>();
    public final HashMap<String, Field> allDataFieldsByName =
            new HashMap<String, Field>();
}
