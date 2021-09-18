/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.core.manager.db.DBPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class DatabaseManager extends AbstractCoreComponent {

    private HashMap<String, DBPool> allDBPool = new HashMap<String, DBPool>();
    private DBPool defaultPool;

    public void addDatabase(String connectionTitle, String driverName,
            String jdbcUrl, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DBPool a = new DBPool(connectionTitle, driverName, jdbcUrl, p);

        allDBPool.put(connectionTitle, a);
        if (defaultPool == null) {
            defaultPool = a;
        }
    }

    public void setDefaultDatabase(String connectionTitle) {
        if (allDBPool.containsKey(connectionTitle)) {
            defaultPool = allDBPool.get(connectionTitle);
        }
    }

    public void addDatabase(String connectionTitle, String driverName,
            String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties p = new Properties();
        p.setProperty("user", userName);
        p.setProperty("password", password);
        addDatabase(connectionTitle, driverName, jdbcUrl, p);
    }

    public void addHsqldbDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.addDatabase(connectionTitle, "org.hsqldb.jdbcDriver", jdbcUrl, userName, password);
    }

    public void addHsqldbDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        p.setProperty("user", userName);
        p.setProperty("password", password);
        this.addDatabase(connectionTitle, "org.hsqldb.jdbcDriver", jdbcUrl, p);
    }

    public void addMysqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.addDatabase(connectionTitle, "com.mysql.jdbc.Driver", jdbcUrl, userName, password);
    }

    public void addMysqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        p.setProperty("user", userName);
        p.setProperty("password", password);
        this.addDatabase(connectionTitle, "com.mysql.jdbc.Driver", jdbcUrl, p);
    }

    public void addPgsqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.addDatabase(connectionTitle, "org.postgresql.Driver", jdbcUrl, userName, password);
    }

    public void addPgsqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        p.setProperty("user", userName);
        p.setProperty("password", password);
        this.addDatabase(connectionTitle, "org.postgresql.Driver", jdbcUrl, p);
    }

    public Connection getConnection(String connectionTitle) throws SQLException {
        return allDBPool.get(connectionTitle).getConnection();
    }

    public Connection getConnection() throws SQLException {
        if (defaultPool == null) {
            throw new SQLException("No connection defined");
        }
        //return this.getConnection(defaultPool);
        return defaultPool.getConnection();
    }

    public void closeAll() {
        for (DBPool dbPool : allDBPool.values()) {
            try {
                dbPool.closePool();
            } catch (SQLException ex) {
                core().log.getEngineLogger().log(
                        Level.SEVERE, "dbm.closeAll", ex);
            }

        }
    }
//    public DBConnection getConnection(String connectionTitle) throws SQLException {
//        return dbPool.get(connectionTitle).getConnection();
//    }
//
//    public DBConnection getConnection() throws SQLException {
//        //return this.getConnection(defaultPool);
//        return defaultPool.getConnection();
//    }
}
