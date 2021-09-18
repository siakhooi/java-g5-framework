/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSZE0 extends EntryForm {

    @Override
    public void initValue() {
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    protected void buildFieldList() {
        addPasswordField("ap1", "BSZE0.ap1");
        super.setFieldAllowCharacters("1234567890ABCDEF");
        super.setFieldMaximumLength(4);
        super.setFieldTextColumn(8);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        addPasswordField("ap2", "BSZE0.ap2");
        super.setFieldAllowCharacters("1234567890ABCDEF");
        super.setFieldMaximumLength(4);
        super.setFieldTextColumn(8);
        super.setFieldTextCaseType(TextCaseType.UPPER);

        addTab("test", "BSZE0.tab.test", BSZT0.class);
        addTab("write", "BSZE0.tab.write", BSZT1.class);
        addTab("read", "BSZE0.tab.read", BSZT2.class);
    }

    @Override
    public void receiveSignal(int signal) {
        try {
            if (signal == ZSignal.SIGNAL_SEND_PWD) {
                String a1 = cmd.entry.getString("ap1");
                String a2 = cmd.entry.getString("ap2");
                int ap1 = (int) cmd.data.Hex2long(a1);
                int ap2 = (int) cmd.data.Hex2long(a2);
                cmd.local.map.integers.put(ZSignal.GLOBAL_AP1, ap1);
                cmd.local.map.integers.put(ZSignal.GLOBAL_AP2, ap2);
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ap1", ex);
        } catch (NumberFormatException ex) {
            cmd.log.sysSevere("ap1", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSZE0.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeApplication(true);
    }
}
