/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;

/**
 *
 * @author Ng Siak Hooi
 */
public class BooleanRenderer extends AbstractRenderer {

    @Override
    protected String format(AbstractRenderer comp,Field f, Object text) {
        boolean value = false;
        if (text == null) {
            value = false;
        } else if (text instanceof Boolean) {
            Boolean i = (Boolean) text;
            value = i.booleanValue();
        } else if (text instanceof Number) {
            Number i = (Number) text;
            value = i.intValue() != 0;
        } else if (text instanceof String) {
            value = formControl.cmd.data.isTrue((String) text);
        } else {
            //not null=true
            value = true;
        }
        return value ? EngineConfiguration.YES : EngineConfiguration.NO;
    }
}
