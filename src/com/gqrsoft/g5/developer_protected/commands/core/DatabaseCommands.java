/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class DatabaseCommands extends AbstractCommandComponent {

    private Connection cnt;

    public Connection getThreadConnection() {
        return getFormControl().threadControl.connection;
    }

    public void initThreadDBConnection() throws SQLException {
        cnt = getFormControl().core.dbm.getConnection();
        getFormControl().threadControl.connection = cnt;
    }

    public void useThreadDBConnection() {
        cnt = getFormControl().threadControl.connection;
    }

    public void newDBConnection() throws SQLException {
        cnt = getFormControl().core.dbm.getConnection();
    }

    public void newDBConnection(String title) throws SQLException {
        cnt = getFormControl().core.dbm.getConnection(title);
    }

//    public java.sql.Connection getSQLConnection() {
//        return cnt.getConnection();
//    }
//    public java.sql.ResultSet getSQLResultSet(String rsName) {
//        return getFormControl().local.map.sqlResultsets.get(rsName);
//    //return cnt.RS().getResultSet(rsName);
//    }
    public ResultSet getResultSet(String rsName) {
        return getFormControl().local.map.sqlResultsets.get(rsName);
    }

    public PreparedStatement getPreparedStatement(String psName) {
        return getFormControl().local.map.sqlStatements.get(psName);
    }

    public void smartStart() throws SQLException {
        begin();
    }

    public void smartComplete() throws SQLException {
        try {
            commit();
        } catch (SQLException ex) {
            rollback();
            throw ex;
        }
    }

    public void begin() throws SQLException {
        cnt.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        cnt.commit();
    }

    public void rollback() throws SQLException {
        cnt.rollback();
    }

    public void disconnect() throws SQLException {
        cnt.close(); //.disconnect();
    }

    public void setAutoCommit(boolean value) throws SQLException {
        cnt.setAutoCommit(value);
    }

    public void setStatement(String psName, String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        PreparedStatement ps;
        ps = cnt.prepareStatement(sql, resultSetType, resultSetConcurrency);
        getFormControl().local.map.sqlStatements.put(psName, ps);

    }

    public void setStatement(String psName, String sql) throws SQLException {
        setStatement(psName, sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public void execQuery(String psName, String rsName) throws SQLException {
        ResultSet rs;
        rs = getPreparedStatement(psName).executeQuery();
        getFormControl().local.map.sqlResultsets.put(rsName, rs);
    }

    public int execUpdate(String psName) throws SQLException {
        return getPreparedStatement(psName).executeUpdate();
    }

    public boolean first(String rsName) throws SQLException {
        return getResultSet(rsName).first();
    }

    public boolean next(String rsName) throws SQLException {
        return getResultSet(rsName).next();
    }

    public boolean previous(String rsName) throws SQLException {
        return getResultSet(rsName).previous();
    }

    public boolean last(String rsName) throws SQLException {
        return getResultSet(rsName).last();
    }

    public void beforeFirst(String rsName) throws SQLException {
        getResultSet(rsName).beforeFirst();
    }

    public void afterLast(String rsName) throws SQLException {
        getResultSet(rsName).afterLast();
    }
//
    public void setParameter(String psName, int index, int value) throws SQLException {
        getPreparedStatement(psName).setInt(index, value);
    }

    public void setParameter(String psName, int index, short value) throws SQLException {
        getPreparedStatement(psName).setShort(index, value);
    }

    public void setParameter(String psName, int index, long value) throws SQLException {
        getPreparedStatement(psName).setLong(index, value);
    }

    public void setParameter(String psName, int index, float value) throws SQLException {
        getPreparedStatement(psName).setFloat(index, value);
    }

    public void setParameter(String psName, int index, double value) throws SQLException {
        getPreparedStatement(psName).setDouble(index, value);
    }

    public void setParameter(String psName, int index, String value) throws SQLException {
        getPreparedStatement(psName).setString(index, value);
    }

    public void setParameter(String psName, int index, BigDecimal value) throws SQLException {
        getPreparedStatement(psName).setBigDecimal(index, value);
    }

    public void setParameter(String psName, int index, Calendar value) throws SQLException {
        getPreparedStatement(psName).setTimestamp(index,
                new java.sql.Timestamp(value.getTimeInMillis()));
    }

    public void setParameter(String psName, int index, java.sql.Date value) throws SQLException {
        getPreparedStatement(psName).setDate(index, value);
    }

    public void setParameter(String psName, int index, java.sql.Timestamp value) throws SQLException {
        getPreparedStatement(psName).setTimestamp(index, value);
    }

    public void setParameter(String psName, int index, byte[] value) throws SQLException {
        getPreparedStatement(psName).setBytes(index, value);
    }

    public String getString(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getString(colName);
    }

    public int getInteger(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getInt(colName);
    }

    public long getLong(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getLong(colName);
    }

    public short getShort(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getShort(colName);
    }

    public float getFloat(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getFloat(colName);
    }

    public double getDouble(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getDouble(colName);
    }

    public BigDecimal getBigDecimal(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getBigDecimal(colName);
    }

    public java.sql.Date getDate(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getDate(colName);
    }

    public java.sql.Timestamp getTimestamp(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getTimestamp(colName);
    }

    public byte[] getBytes(String rsName, String colName) throws SQLException {
        return getResultSet(rsName).getBytes(colName);
    }
}
