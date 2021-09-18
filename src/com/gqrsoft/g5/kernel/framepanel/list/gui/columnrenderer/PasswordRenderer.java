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
public class PasswordRenderer extends AbstractRenderer {

    @Override
    protected String format(AbstractRenderer comp,Field f, Object text) {
        return EngineConfiguration.List.DEFAULT_TEXT_REPRESENT_PASSWORD;
    }
}
