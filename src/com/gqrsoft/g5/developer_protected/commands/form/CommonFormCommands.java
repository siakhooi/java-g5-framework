/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.developer_secret.tools.ResultSetTableModel;
import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.table.TableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class CommonFormCommands extends AbstractCommandComponent {

    public void showI18nMessage(FormEnum.DialogMessageType t,
            String i18nTitle, String i18nMessage) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Form.DEFAULT_MESSAGE_DIALOG_FORM);
        getFormControl().out.dialogMessageType = t;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().out.message = i18nMessage;
        getFormControl().cmd.form.execute(form);
    }

    public void showMessage(FormEnum.DialogMessageType t,
            String title, String message) {
        title = getFormControl().cmd.lang.getString(title);
        message = getFormControl().cmd.lang.getString(message);
        showI18nMessage(t, title, message);
    }

    public boolean showI18nConfirmation(FormEnum.DialogMessageType t,
            String i18nTitle, String i18nMessage) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Form.DEFAULT_CONFIRMATION_DIALOG_FORM);
        getFormControl().out.dialogMessageType = t;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().out.message = i18nMessage;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.booleanValue;
    }

    public boolean showConfirmation(FormEnum.DialogMessageType t,
            String title, String message) {
        title = getFormControl().cmd.lang.getString(title);
        message = getFormControl().cmd.lang.getString(message);
        return showI18nConfirmation(t, title, message);
    }

    public Calendar chooseCalendar(Calendar initialCalendar) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SELECT_CALENDAR_FORM);
        getFormControl().out.calendarValue = initialCalendar;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.calendarValue;
    }

    public Color chooseColor(Color initialColor) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SELECT_COLOR_FORM);
        getFormControl().out.colorValue = initialColor;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.colorValue;
    }

    public File chooseOpenDirectory(File currentPath) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SELECT_DIRECTORY_FORM);
        getFormControl().out.fileValue = currentPath;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.fileValue;
    }

    public void viewImage(java.awt.Image value, String title) {
        title = getFormControl().cmd.lang.getString(title);
        viewI18nImage(value, title);
    }

    public void viewI18nImage(java.awt.Image value, String i18nTitle) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_VIEW_IMAGE_FORM);
        getFormControl().out.imageValue = value;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().cmd.form.execute(form);
    }

    public void viewHTML(String value, String title) {
        title = getFormControl().cmd.lang.getString(title);
        viewI18nHTML(value, title);
    }

    public void viewI18nHTML(String value, String i18nTitle) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_VIEW_HTML_FORM);
        getFormControl().out.message = value;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().cmd.form.execute(form);
    }

    public File chooseSaveFile(File currentPath) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SAVE_FILE_FORM);
        getFormControl().out.fileValue = currentPath;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.fileValue;
    }

    public File chooseSaveFile(
            File currentPath,
            String i18nTitle,
            String extension,
            String i18nExtensionDescription) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SAVE_FILE_WITH_EXT_FORM);
        getFormControl().out.fileValue = currentPath;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().out.extension = extension;
        getFormControl().out.i18nExtensionDescription = i18nExtensionDescription;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.fileValue;
    }

    public File chooseSavePDFFile(File initPath) {
        String title = "CommonForm.SelectSavePDFFile.title";
        title = getFormControl().cmd.lang.getSystemString(title);

        String extDesc = "CommonForm.SelectSavePDFFile.PDFDescription";
        extDesc = getFormControl().cmd.lang.getSystemString(extDesc);

        return chooseSaveFile(initPath, title, "pdf", extDesc);
    }

    public File chooseSaveCSVFile(File initPath) {
        String title = "CommonForm.SelectSaveCSVFile.title";
        title = getFormControl().cmd.lang.getSystemString(title);

        String extDesc = "CommonForm.SelectSaveCSVFile.CSVDescription";
        extDesc = getFormControl().cmd.lang.getSystemString(extDesc);

        return chooseSaveFile(initPath, title, "csv", extDesc);
    }

    public File chooseOpenFile(File currentPath) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_OPEN_FILE_FORM);
        getFormControl().out.fileValue = currentPath;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.fileValue;
    }

    public File chooseOpenFile(
            File currentPath,
            String i18nTitle,
            String extension,
            String i18nExtensionDescription) {
        UserFormInterface form;
        form = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_OPEN_FILE_WITH_EXT_FORM);

        getFormControl().out.fileValue = currentPath;
        getFormControl().out.i18nTitle = i18nTitle;
        getFormControl().out.extension = extension;
        getFormControl().out.i18nExtensionDescription = i18nExtensionDescription;
        getFormControl().cmd.form.execute(form);
        return getFormControl().out.fileValue;
    }

    public void saveTableModelToCSVFile(TableModel tm, File file) {
        UserFormInterface f;
        f = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SAVE_TABLE_MODEL_TO_CSV_FORM);

        getFormControl().out.fileValue = file;
        getFormControl().out.tableModel = tm;
        getFormControl().cmd.form.execute(f);
    }

    public void saveTableModelToCSVFile(TableModel tm, File file, int[] selected) {
        UserFormInterface f;
        f = getFormControl().cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_SAVE_TABLE_MODEL_TO_CSV_FORM);
        getFormControl().out.fileValue = file;
        getFormControl().out.tableModel = tm;
        getFormControl().out.intValues = selected;
        getFormControl().cmd.form.execute(f);
    }

    public void saveResultSetToCSVFile(ResultSet rs, File file) {
        ResultSetTableModel rstm = new ResultSetTableModel(rs);
        saveTableModelToCSVFile(rstm, file);
    }

    public void saveResultSetToCSVFile(ResultSet rs, File file, int[] selected) {
        ResultSetTableModel rstm = new ResultSetTableModel(rs);
        saveTableModelToCSVFile(rstm, file, selected);
    }
}
