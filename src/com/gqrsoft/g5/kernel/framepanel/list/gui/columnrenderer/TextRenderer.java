/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextRenderer extends AbstractRenderer {

    @Override
    protected String format(AbstractRenderer comp,Field f, Object text) {
        String inputmask = f.inputMask;
        String outputmask = f.outputMask;
        String v = "";
        if (text == null) {
            return v;
        }
        if (outputmask == null) {
            return v;
        }
        String value = text.toString();
        Pattern p = Pattern.compile(inputmask);
        Matcher m = p.matcher(value);
        boolean isMatch = m.matches();
        if (!isMatch) {
            return value;
        }

        MessageFormat mf = new MessageFormat(outputmask);
        String[] s = new String[m.groupCount()];
        for (int i = 0; i < m.groupCount(); i++) {
            s[i] = m.group(i + 1);
        }
        return mf.format(s);
    }
}
