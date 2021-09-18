/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m40.selection;

import com.gqrsoft.g5.developer.form.SelectionForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloSingleSelection extends SelectionForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return SelectionInfo.class;
    }

    @Override
    protected int getColumn() {
        return 4;
    }

    @Override
    protected void buildSelection() {
        LinkedHashMap<String, String> a = new LinkedHashMap<String, String>();
        Vector<String> b = new Vector<String>();
        for (int i = 0; i < 18; i++) {
            a.put("A" + i, "A" + i + "-label");
        }
        b.add("A2");
        b.add("A5");
        this.use(a, b);
    }

    @Override
    public String getFormTitle() {
        return "HelloSingleSelection.title";
    }

    @Override
    protected void userCancelled() {
        cmd.form.closeForm();
    }

    @Override
    protected void userSelected(Vector<String> selectedValues) {
        cmd.linewrite.init();
        for (String s : selectedValues) {
            cmd.linewrite.println("selected: " + s);
        }
        cmd.local.stringValue = cmd.linewrite.getString();
        cmd.form.broadcastBlockingSignal(0);
    }

    @Override
    protected void userClick(String name) {
        cmd.local.stringValue = "userClick: " + name + ":" + getSelected(name);
        cmd.form.broadcastBlockingSignal(0);
    }

}
