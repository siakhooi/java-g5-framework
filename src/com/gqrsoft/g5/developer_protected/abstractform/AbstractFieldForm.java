/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FieldEnum;
import com.gqrsoft.g5.developer_secret.abstractform.PrivateAbstractFieldForm;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractFieldForm extends PrivateAbstractFieldForm {

    protected abstract void addNewField(Field field);

    public void addField(FieldEnum.FieldType type,
            String fieldName, String fieldLabel) {
        currentField = new Field(type, fieldName);
        currentField.label = fieldLabel;
        fieldLabel = cmd.lang.getString(fieldLabel);
        currentField.i18nLabel = fieldLabel;
        addNewField(currentField);
    }

    protected final void addBooleanField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.BOOLEAN, fieldName, fieldLabel);
    }

    protected final void addRadioboxField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.RADIO, fieldName, fieldLabel);
        currentField.optionValues = new Vector<String>();
        currentField.optionI18nLabels = new Vector<String>();
    }

    @Deprecated
    protected final void addRadioboxfield(String fieldName, String fieldLabel) {
        addRadioboxField(fieldName, fieldLabel);
    }

    protected final void addComboField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.COMBO, fieldName, fieldLabel);
        currentField.optionValues = new Vector<String>();
        currentField.optionI18nLabels = new Vector<String>();
    }

    @Deprecated
    protected final void addCombofield(String fieldName, String fieldLabel) {
        addComboField(fieldName, fieldLabel);
    }

    protected final void addTextField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
    }

    protected final void addCalendarField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.CALENDAR, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_SELECT_CALENDAR_FORM;

        setFieldFormat(true, false);
        currentField.textColumns = 12;
        currentField.maximumLength = 8;
    }

    protected final void addNumericField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.NUMERIC, fieldName, fieldLabel);
        setFieldAllowCharacters("0123456789-.");
        currentField.alignment = FieldEnum.AlignmentType.RIGHT;
    }

    protected final void addPasswordField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.PASSWORD, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
    }

//    protected final void addNoteField(String fieldName, String fieldLabel) {
//        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
//    }
    protected final void addColorField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.COLOR, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.CENTER;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_SELECT_COLOR_FORM;
        setFieldAllowCharacters("0123456789ABCDEFabcdef");

        currentField.textColumns = 8;
        currentField.maximumLength = 6;
        this.setFieldTextCaseType(FieldEnum.TextCaseType.UPPER);
    }

    protected final void addDirectoryField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_SELECT_DIRECTORY_FORM;

        currentField.textColumns = 20;
        currentField.maximumLength = 0;
    }

    protected final void addNewFileField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_SAVE_FILE_FORM;

        currentField.textColumns = 20;
        currentField.maximumLength = 0;
    }

    protected final void addOpenFileField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_OPEN_FILE_FORM;

        currentField.textColumns = 20;
        currentField.maximumLength = 0;
    }

    protected final void addImageField(String fieldName, String fieldLabel) {
        addField(FieldEnum.FieldType.TEXT, fieldName, fieldLabel);
        currentField.alignment = FieldEnum.AlignmentType.LEFT;
        currentField.textColumns = 20;
        currentField.maximumLength = 0;
        currentField.selectFormInterface =
                EngineConfiguration.Entry.DEFAULT_OPEN_IMAGE_FILE_FORM;
        currentField.viewFormInterface =
                EngineConfiguration.Entry.DEFAULT_VIEW_IMAGE_FORM;
    }

    protected final void setFieldHelpMessage(String helpMessage) {
        currentField.helpMessage = helpMessage;
        helpMessage = cmd.lang.getString(helpMessage);
        currentField.i18nHelpMessage = helpMessage;
    }

    protected final void setFieldTextAlignment(FieldEnum.AlignmentType t) {
        currentField.alignment = t;
    }

    protected final void setFieldAllowCharacters(String s) {
        currentField.allowCharacters = s;
    }

    protected final void setFieldAllowCharacters(boolean numeric, boolean upper, boolean lower) {
        StringBuffer s = new StringBuffer();
        if (numeric) {
            s.append("1234567890");
        }
        if (upper) {
            s.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (lower) {
            s.append("abcdefghijklmnopqrstuvwxyz");
        }
        setFieldAllowCharacters(s.toString());
    }

    protected final void setFieldTextColumn(int textColumns) {
        currentField.textColumns = textColumns;
    }

    protected final void setFieldMaximumLength(int maxLength) {
        currentField.maximumLength = maxLength;
    }

    protected final void setFieldMandatory(boolean mandatory) {
        currentField.mandatory = mandatory;
    }

    protected final void setFieldEditable(boolean editable) {
        currentField.editable = editable;
    }

    protected final void setFieldVisible(boolean visible) {
        currentField.visible = visible;
    }

    protected final void setFieldSelectForm(
            Class<? extends UserFormInterface> selectFormInterface) {
        currentField.selectFormInterface = selectFormInterface;
    }

    protected final void setFieldViewForm(
            Class<? extends UserFormInterface> viewFormInterface) {
        currentField.viewFormInterface = viewFormInterface;
    }

    protected final void setFieldListColumnWidth(int value) {
        currentField.listColumnWidth = value;
    }

    protected final void setFieldFormat(String inputMask, String outputMask) {
        currentField.inputMask = inputMask;
        currentField.outputMask = outputMask;
    }

    protected final void setFieldInputFormat(String inputMask) {
        currentField.inputMask = inputMask;
    }

    protected final void setFieldOutputFormat(String outputMask) {
        currentField.outputMask = outputMask;
    }

    protected final void setFieldFormat(boolean date, boolean time) {
        StringBuffer inputMask = new StringBuffer();
        StringBuffer outputMask = new StringBuffer();
        int outputSize = 0;
        int inputSize = 0;
        if (date) {
            inputMask.append("yyyyMMdd");
            inputSize += 8;
        }
        if (time) {
            inputMask.append("HHmmss");
            inputSize += 6;
        }
        if (date) {
            outputMask.append("d MMM, yyyy");
            outputSize += 12;
        }
        if (time) {
            if (date) {
                outputMask.append(" ");
                outputSize++;
            }
            outputMask.append("HH:mm:ss");
            outputSize += 8;
        }
        setFieldFormat(inputMask.toString(), outputMask.toString());
        setFieldTextColumn(outputSize);
        setFieldMaximumLength(inputSize);
    }

    protected final void setFieldToCurrencyFormat() {
        //TODO: need to check locale
        setFieldOutputFormat("#,##0.00#;(#,##0.00#)");
    }

    protected final void setFieldTextCaseType(FieldEnum.TextCaseType t) {
        currentField.textCaseType = t;
    }

    protected final void addOption(String value, String label) {
        label = cmd.lang.getString(label);
        addI18nOption(value, label);
    }

    protected final void addI18nOption(String value, String i18nLabel) {
        currentField.optionI18nLabels.add(i18nLabel);
        currentField.optionValues.add(value);
    }

    protected final void spanColumn(int n) {
        currentField.entryFormColumnSpan = n;
    }

//    protected final void setColumns(int n) {
//        model().currentFieldGroup.setColumns(n);
//    }
}
