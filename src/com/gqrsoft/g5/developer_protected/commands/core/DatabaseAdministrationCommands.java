/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class DatabaseAdministrationCommands extends AbstractCommandComponent {

    public void setDefaultDatabase(String connectionTitle) {
        getFormControl().core.dbm.setDefaultDatabase(connectionTitle);
    }

    public void addDatabase(String connectionTitle, String driverName,
            String jdbcUrl, String userName, String password)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addDatabase(
                connectionTitle, driverName, jdbcUrl, userName, password);
    }

    /**
     * 
     * jdbc can be:
     * memory: jdbc:hsqldb:mem:aname
     * inprocess/standalone: jdbc:hsqldb:file:testdb
     * inprocess/standalone: jdbc:hsqldb:file:/opt/db/testdb
     * hsqldb server: jdbc:hsqldb:hsql://localhost/xdb
     * from jar file: jdbc:hsqldb:res: 
     * jdbc:hsqldb:hsql:  
     * jdbc:hsqldb:hsqls:  
     * jdbc:hsqldb:http:  
     * jdbc:hsqldb:https: 
     * 
     * @param connectionTitle
     * @param jdbcUrl
     * @param userName
     * @param password
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public void addHsqldbDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addHsqldbDatabase(
                connectionTitle, jdbcUrl, userName, password);
    }

    public void addHsqldbDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addHsqldbDatabase(
                connectionTitle, jdbcUrl, userName, password, p);
    }

    public void startHsqldbDatabaseManager(String jdbcUrl,
            String username, String password) {
        startHsqldbDatabaseManager(null, jdbcUrl, username, password);
    }

    public void startHsqldbDatabaseManager(String driver,
            String jdbcUrl, String username, String password) {
        Vector<String> a = new Vector<String>();
        if (getFormControl().cmd.data.isNull(driver)) {
            driver = "org.hsqldb.jdbcDriver";
        }
        a.add("--noexit");
        a.add("--driver");
        a.add(driver);

        if (!getFormControl().cmd.data.isNull(jdbcUrl)) {
            a.add("--url");
            a.add(jdbcUrl);
        }
        if (!getFormControl().cmd.data.isNull(username)) {
            a.add("--user");
            a.add(username);
        }
        if (!getFormControl().cmd.data.isNull(password)) {
            a.add("--password");
            a.add(password);
        }
        String[] b = new String[a.size()];
        b = a.toArray(b);
        org.hsqldb.util.DatabaseManager.main(b);
    }

    public void addMysqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addMysqlDatabase(
                connectionTitle, jdbcUrl, userName, password);
    }

    public void addMysqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addMysqlDatabase(
                connectionTitle, jdbcUrl, userName, password, p);
    }

    public void addPgsqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addPgsqlDatabase(
                connectionTitle, jdbcUrl, userName, password);
    }

    public void addPgsqlDatabase(String connectionTitle, String jdbcUrl, String userName, String password, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        getFormControl().core.dbm.addPgsqlDatabase(
                connectionTitle, jdbcUrl, userName, password, p);
    }
}
