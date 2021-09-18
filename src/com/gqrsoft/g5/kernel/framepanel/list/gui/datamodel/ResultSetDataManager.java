/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel;

import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class ResultSetDataManager extends AbstractListDataManager {

    private ResultSet getResultSet() {
        return formControl.listForm.resultset;
    }

    @Override
    public int getRowCount() {
        ResultSet rs = getResultSet();
        if (rs == null) {
            return 0;
        }
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            String msg = "ListPanel.ResultSetDataManager.getRowCount.error.{0}";
            msg = formControl.cmd.lang.getString(msg, ex.getLocalizedMessage());
            formControl.cmd.log.log(Level.SEVERE, msg, ex);
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultSet rs = getResultSet();
        if (rs == null) {
            return "";
        }
        try {
            rs.absolute(rowIndex + 1);
            Field f = formControl.listForm.allFieldsByIndex.elementAt(columnIndex);
            String s = f.fieldName;
            return rs.getObject(s);
        } catch (SQLException ex) {
            String msg = "ListPanel.ResultsetDataManager.getValueAt.error.{0}";
            msg = formControl.cmd.lang.getString(msg, ex.getLocalizedMessage());
            formControl.cmd.log.log(Level.SEVERE, msg, ex);
            return null;
        }
    }

    @Override
    public void init() {
    }
}
