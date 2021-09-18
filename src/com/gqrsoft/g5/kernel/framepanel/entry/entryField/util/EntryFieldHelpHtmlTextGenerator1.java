/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.developer_protected.commands.core.LanguageCommands;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.developer_protected.commands.util.StringPrintWriter;

/**
 *
 * @author Ng Siak Hooi
 * @deprecated 
 */
@Deprecated
public class EntryFieldHelpHtmlTextGenerator1 {

    private static String getHTMLForTableLine(
            String label, String value) {
        return "<TR><TD align=right>" + label +
                "</TD><TD>" + value + "</TD></TR>";
    }

    public static String generateHtmlText(
            FormControl formControl, AbstractEntryField entryField) {
        StringPrintWriter spw = new StringPrintWriter();
        Field field = entryField.field;
        LanguageCommands lang = formControl.cmd.lang;
        String s = "";

        spw.print("<HTML><BODY>");
        s = "EntryField.Help.Description";
        s = lang.getSystemString(s);
        spw.print("<H3><U>" + s + "</U></H3>");

        s = field.helpMessage;
        s = lang.getString(s);
        spw.print(s);

        spw.print("<BR><HR>");

        spw.print("<P>");
        s = "EntryField.Help.Field.Information";
        s = lang.getSystemString(s);
        spw.print("<H3><U>" + s + "</U></H3>");

        spw.print("<TABLE border=1 width=100%>");

        String label = "", value = "";
        label = "EntryField.Help.label.Field.Title";
        label = lang.getSystemString(label);
        value = field.label;
        value = lang.getString(value);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Field.Name";
        label = lang.getSystemString(label);
        value = field.fieldName;
        value = lang.getString(value);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Field.Type";
        label = lang.getSystemString(label);
        value = field.fieldType.entryFieldHelpTitle;
        value = lang.getSystemString(value);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Mandatory";
        label = lang.getSystemString(label);
        value = getText(field.mandatory);
        value = lang.getSystemString(value);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Allowable.Characters";
        label = lang.getSystemString(label);
        value = field.allowCharacters;
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Field.Text.Type";
        label = lang.getSystemString(label);
        value = field.textCaseType.entryFieldHelpTitle;
        value = lang.getSystemString(value);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Maximum.Length";
        label = lang.getSystemString(label);
        value = formControl.cmd.data.int2String(field.maximumLength);
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Input.Mask";
        label = lang.getSystemString(label);
        value = field.inputMask;
        spw.print(getHTMLForTableLine(label, value));

        label = "EntryField.Help.label.Output.Mask";
        label = lang.getSystemString(label);
        value = field.outputMask;
        spw.print(getHTMLForTableLine(label, value));

        spw.print("</TABLE>");
        spw.println("</BODY></HTML>");
        CONSOLE.println(spw.getString());
        return spw.getString();
    }

    private static String getText(boolean value) {
        return value
                ? "EntryField.Help.Boolean.Value.True"
                : "EntryField.Help.Boolean.Value.False";
    }
}
