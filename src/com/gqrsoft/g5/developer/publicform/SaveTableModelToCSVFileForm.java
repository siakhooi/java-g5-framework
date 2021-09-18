/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer_protected.commands.util.CsvWriterCommands;
import java.io.IOException;

/**
 * set cmd.out.fileValue
 * set cmd.out.intValues (optional)
 * set cmd.out.tableModel
 * @author Ng Siak Hooi
 */
public class SaveTableModelToCSVFileForm extends ProcessForm {

    private boolean saveAll = true;
    private CsvWriterCommands csvWriter = new CsvWriterCommands();

    @Override
    public void init() {
        super.setUserManualStart(false);
        super.setUserManualClose(true);
        super.setUserAllowCancel(true);

        if (cmd.in.intValues != null) {
            saveAll = false;
        }
        int i = getNumberOfRecordsToWrite();
        String processTitle = "";
        processTitle = "SaveTableModelToCSVFileForm.WriteColumnHeaders.process.title";
        processTitle = cmd.lang.getSystemString(processTitle);
        addI18nProcess(processTitle, 1);
        processTitle = "SaveTableModelToCSVFileForm.GeneratingCSVRecords.process.title";
        processTitle = cmd.lang.getSystemString(processTitle);
        addI18nProcess(processTitle, i * 2);
        processTitle = "SaveTableModelToCSVFileForm.WriteToFile.process.title";
        processTitle = cmd.lang.getSystemString(processTitle);
        addI18nProcess(processTitle, i);
    }

    private int getNumberOfRecordsToWrite() {
        return saveAll ? cmd.in.tableModel.getRowCount() : cmd.in.intValues.length;
    }

    private int getRecordIndex(int i) {
        return saveAll ? i : cmd.in.intValues[i];
    }

    @Override
    public void run() throws ProcessException {
        csvWriter.clear();

        int c = cmd.in.tableModel.getColumnCount();
        String[] col = new String[c];
        for (int i = 0; i < c; i++) {
            col[i] = cmd.in.tableModel.getColumnName(i);
        }
        csvWriter.write(col);
        super.completed();
        
        int r = getNumberOfRecordsToWrite();
        setMinorTotalCount(r);

        String msg = "SaveTableModelToCSVFileForm.generateRecordOf.%n.%m";
        msg = cmd.lang.getSystemString(msg);
        for (int i = 0; i < r; i++) {
            String[] data = new String[c];
            int recordIndex = getRecordIndex(i);
            for (int j = 0; j < c; j++) {
                Object userValue = cmd.in.tableModel.getValueAt(recordIndex, j);
                data[j] = userValue.toString();
            }
            csvWriter.write(data);
            String t = msg.replaceAll("%n", cmd.data.int2String(i));
            t = t.replaceAll("%m", cmd.data.int2String(r));

            setI18nProgressMessage(t);
            minorCompleted();
        }
        super.completed();
        try {
            csvWriter.save(cmd.in.fileValue);
        } catch (IOException ex) {
            String msg1 = ex.getLocalizedMessage();
            showSavingError(msg1);
        }
        super.completed();
    }

    private void showSavingError(String errorMessage) {
        String t = "SaveTableModelToCSVFileForm.ErrorSavingCSVFile";
        t = cmd.lang.getSystemString(t);
        cmd.common.showI18nMessage(
                DialogMessageType.ERROR, t, errorMessage);
    }

    @Override
    public void userCancel() {
        try {
            csvWriter.save(cmd.in.fileValue);
        } catch (IOException ex) {
            String msg = ex.getLocalizedMessage();
            showSavingError(msg);
        }
    }

    @Override
    public String getFormTitle() {
        return null;
    }

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SaveTableModelToCSVFileForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }
}
