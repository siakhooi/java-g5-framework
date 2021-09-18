/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.AbstractRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.BooleanRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.CalendarRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.ColorRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.NumericRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.PasswordRenderer;
import com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer.TextRenderer;
import javax.swing.JTextField;

/**
 *
 * @author Ng Siak Hooi
 */
public class FieldEnum {

    public enum FieldType {

        BOOLEAN("FieldEnum.FieldType.Help.Boolean", BooleanRenderer.class),
        TEXT("FieldEnum.FieldType.Help.Text", TextRenderer.class),
        CALENDAR("FieldEnum.FieldType.Help.Calendar", CalendarRenderer.class),
        NUMERIC("FieldEnum.FieldType.Help.Numeric", NumericRenderer.class),
        PASSWORD("FieldEnum.FieldType.Help.Password", PasswordRenderer.class),
        COLOR("FieldEnum.FieldType.Help.Color", ColorRenderer.class),
        RADIO("FieldEnum.FieldType.Help.Radio", TextRenderer.class),
        COMBO("FieldEnum.FieldType.Help.Combo", TextRenderer.class);
        public Class<? extends AbstractRenderer> listColumnClass;
        public String entryFieldHelpTitle;
        //use by EntryFieldHelp, to be convert to local language.
//            return FileRenderer.class;
//            return ImageRenderer.class;
        FieldType(String entryFieldHelpTitle,
                Class<? extends AbstractRenderer> t) {
            this.entryFieldHelpTitle = entryFieldHelpTitle;
            this.listColumnClass = t;
        }
    }

    public enum AlignmentType {

        LEFT(JTextField.LEFT),
        RIGHT(JTextField.RIGHT),
        CENTER(JTextField.CENTER);
        public int jTextFieldAlignment;

        AlignmentType(int v) {
            this.jTextFieldAlignment = v;
        }
    }

    public enum TextCaseType {

        MIX("FieldEnum.TextCaseType.Help.Mix"),
        UPPER("FieldEnum.TextCaseType.Help.AlwaysUpper"),
        LOWER("FieldEnum.TextCaseType.Help.AlwaysLower");
        public String entryFieldHelpTitle;

        TextCaseType(String entryFieldHelpTitle) {
            this.entryFieldHelpTitle = entryFieldHelpTitle;
        }
    }
}
