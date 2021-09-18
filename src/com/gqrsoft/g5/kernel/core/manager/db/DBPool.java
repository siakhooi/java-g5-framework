/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author Ng Siak Hooi
 */
public class DBPool {

    private String poolName = "";
    private String driverName = "";
    private String jdbcUrl = "";
    private Properties dbProperties;
    //private String userName="";
    //private String password="";
    public DBPool(String connectionTitle, String driverName, String jdbcUrl, Properties p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//String userName, String password
        this.poolName = connectionTitle;
        this.driverName = driverName;
        this.jdbcUrl = jdbcUrl;
        this.dbProperties = p;
        //this.userName=userName;
        //this.password=password;

        GenericObjectPool connectionPool = new GenericObjectPool(null);

//        ConnectionFactory connectionFactory = 
//                new DriverManagerConnectionFactory(jdbcUrl, userName, password);

        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(jdbcUrl, dbProperties);
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(
                connectionFactory, connectionPool, null, null, false, false);
        //PoolingDriver driver = new PoolingDriver();
        driver = new PoolingDriver();
        driver.registerPool(connectionTitle, connectionPool);
        Class.forName(driverName).newInstance();
    }
    private PoolingDriver driver;

    public void closePool() throws SQLException {
        driver.closePool(poolName);
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:apache:commons:dbcp:" + poolName;
        return DriverManager.getConnection(url);
    }
//    public DBConnection getConnection() throws SQLException{
//        String url="jdbc:apache:commons:dbcp:"+poolName;
//        return new DBConnection(DriverManager.getConnection(url));
//    }
    /* TODO: how to close pool? */
    /* TODO: PgConnection 
    // TODO: PGsql use properties?
    //Properties properties=new Properties();
    //properties.setProperty("user", dbuser);
    //properties.setProperty("password",dbpass);
    //this.cnt = DriverManager.getConnection(jdbcUrl, propeties);
     */
}
