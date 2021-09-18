/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.awt.Color;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColorRenderer extends AbstractRenderer {

    @Override
    protected String format(AbstractRenderer comp, Field f, Object text) {
        String value = null;
        Color c = null;

        if (text == null) {

        } else if (text instanceof Color) {
            c = (Color) text;
        } else if (text instanceof Number) {
            Number n = (Number) text;
            c = formControl.cmd.data.long2Color(n.longValue());
        } else {
            String n = text.toString();
            c = formControl.cmd.data.Hex2Color(n);
        }

        if (c != null) {
            value = formControl.cmd.data.Color2Hex(c);
            comp.setBackground(c);
        }
        return value;
    }
}
