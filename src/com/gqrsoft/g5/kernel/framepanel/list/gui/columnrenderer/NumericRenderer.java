/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;

/**
 *
 * @author Ng Siak Hooi
 */
public class NumericRenderer extends AbstractRenderer {

    @Override
    protected String format(AbstractRenderer comp, Field f, Object text) {
        String value = "";
        String mask = f.outputMask;
        if (mask == null) {
            mask = "";
        }
        if (text == null) {

        } else if (text instanceof Number) {
            Number n = (Number) text;
            if (!mask.equals("")) {
                value = formControl.cmd.lang.formatDouble(n.doubleValue(), mask);
            } else {
                value = n.toString();
            }
        } else {
            value = text.toString();
        }
        return value;
    }
}
