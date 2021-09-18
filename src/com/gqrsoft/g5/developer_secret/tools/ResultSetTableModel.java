/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.tools;

import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ResultSetTableModel extends AbstractTableModel {

    private ResultSet rs;

    public ResultSetTableModel(ResultSet rs) {
        super();
        this.rs = rs;
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rs.getMetaData().getColumnName(column + 1);
        } catch (SQLException ex) {
            CONSOLE.error("ResultSetTableModel.getColumnName", ex);
            return "err";
        }
    }

    @Override
    public int getRowCount() {
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            CONSOLE.error("ResultSetTableModel.getRowCount", ex);
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try {
            return rs.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            CONSOLE.error("ResultSetTableModel.getColumnCount", ex);
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            CONSOLE.error("ResultSetTableModel.getValueAt", ex);
            return null;
        }
    }
}
