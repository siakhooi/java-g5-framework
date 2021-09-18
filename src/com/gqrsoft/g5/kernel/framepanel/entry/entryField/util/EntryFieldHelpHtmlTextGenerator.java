/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.kernel.config.EngineResource;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.developer_protected.commands.core.LanguageCommands;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldHelpHtmlTextGenerator {

    public static String generateHtmlText(
            FormControl formControl, AbstractEntryField entryField) {

        String f = EngineResource.ENTRY_FIELD_HELP_TEMPLATE;
        InputStream is = EngineResource.class.getResourceAsStream(f);
        byte[] data = null;
        try {
            data = formControl.cmd.data.inputStream2ByteArray(is);
        } catch (IOException ex) {
        }
        if (data == null) {
            return "";
        }
        String htmlText = new String(data);
        Field field = entryField.field;
        LanguageCommands lang = formControl.cmd.lang;
        String name = "", value = "";

        name = EntryFieldHelpText.LABEL_DESCRIPTION;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_DESCRIPTION;
        value = field.helpMessage;
        value = lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.TITLE_FIELDINFO;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_FIELDTITLE;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_FIELDTITLE;
        value = field.label;
        value = lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_FIELDNAME;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_FIELDNAME;
        value = field.fieldName;
        value = lang.getString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_FIELDTYPE;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_FIELDTYPE;
        value = field.fieldType.entryFieldHelpTitle;
        value = lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_MANDATORY;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_MANDATORY;
        value = getText(field.mandatory);
        value = lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_ALLOWABLECHARS;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_ALLOWABLECHARS;
        value = field.allowCharacters;
        if (NULL.isNull(value)) {
            value = EntryFieldHelpText.UNDEFINED;
            value = lang.getSystemString(value);
        }
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_TEXTTYPE;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_TEXTTYPE;
        value = field.textCaseType.entryFieldHelpTitle;
        value = lang.getSystemString(value);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_MAXLENGTH;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_MAXLENGTH;
        value = formControl.cmd.data.int2String(field.maximumLength);
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_INPUTMASK;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_INPUTMASK;
        value = field.inputMask;
        if (NULL.isNull(value)) {
            value = EntryFieldHelpText.UNDEFINED;
            value = lang.getSystemString(value);
        }
        htmlText = htmlText.replaceAll(name, value);

        name = EntryFieldHelpText.LABEL_OUTPUTMASK;
        value = lang.getSystemString(name);
        htmlText = htmlText.replaceAll(name, value);
        name = EntryFieldHelpText.VALUE_OUTPUTMASK;
        value = field.outputMask;
        if (NULL.isNull(value)) {
            value = EntryFieldHelpText.UNDEFINED;
            value = lang.getSystemString(value);
        }
        htmlText = htmlText.replaceAll(name, value);

        return htmlText;
    }

    private static String getText(boolean value) {
        return value
                ? EntryFieldHelpText.TRUE
                : EntryFieldHelpText.FALSE;
    }
}
