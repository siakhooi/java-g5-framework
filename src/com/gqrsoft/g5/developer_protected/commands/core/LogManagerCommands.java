/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ng Siak Hooi
 */
public class LogManagerCommands extends AbstractCommandComponent {

    /**
     * attach new file handler to Global logger.
     * @param fileName to be use for file handler
     * @throws java.io.IOException
     */
    public void attachGlobal(String fileName) throws IOException {
        getFormControl().core.log.attachGlobal(fileName);
    }

    /**
     * attach new file handler to G5 System logger.
     * @param fileName to be use for file handler
     * @throws java.io.IOException
     */
    public void attachSystem(String fileName) throws IOException {
        getFormControl().core.log.attachEngine(fileName);
    }

    /**
     * attach new file handler to logger
     * @param loggerTitle Logger title to add file hanlder
     * @param fileName to be use for file handler
     * @throws java.io.IOException
     */
    public void attachLogger(String loggerTitle, String fileName) throws IOException {
        getFormControl().core.log.attachLogger(loggerTitle, fileName);
    }

    public void attachApplication(String fileName) throws IOException {
        attachLogger(getFormControl().core.ctrl().getApplicationLoggerTitle(), fileName);
    }

    private Logger getGlobalLogger() {
        return getFormControl().core.log.getGlobalLogger();
    }

    private Logger getEngineLogger() {
        return getFormControl().core.log.getEngineLogger();
    }

    private Logger getLogger(String loggerTitle) {
        return getFormControl().core.log.getLogger(loggerTitle);
    }

    private Logger getApplicationLogger() {
        return getLogger(getFormControl().core.ctrl().getApplicationLoggerTitle());
    }

    public void log(Level level, String msg) {
        log(getFormControl().core.ctrl().getApplicationLoggerTitle(),
                level, msg);
    }

    public void log(Level level, String msg, Throwable thrown) {
        log(getFormControl().core.ctrl().getApplicationLoggerTitle(),
                level, msg, thrown);
    }

    public void log(String loggerTitle, Level level, String msg) {
        getGlobalLogger().log(level, msg);
        getLogger(loggerTitle).log(level, msg);
    }

    public void log(String loggerTitle, Level level, String msg, Throwable thrown) {
        getGlobalLogger().log(level, msg, thrown);
        getLogger(loggerTitle).log(level, msg, thrown);
    }

    public void sysLog(Level level, String msg) {
        getGlobalLogger().log(level, msg);
        getEngineLogger().log(level, msg);
    }

    public void sysLog(Level level, String msg, Throwable thrown) {
        getGlobalLogger().log(level, msg, thrown);
        getEngineLogger().log(level, msg, thrown);
    }

    public void sysSevere(String msg) {
        sysLog(Level.SEVERE, msg);
    }

    public void sysSevere(String msg, Throwable thrown) {
        sysLog(Level.SEVERE, msg, thrown);
    }

    public void sysWarning(String msg) {
        sysLog(Level.WARNING, msg);
    }

    public void sysWarning(String msg, Throwable thrown) {
        sysLog(Level.WARNING, msg, thrown);
    }

    public void sysInfo(String msg) {
        sysLog(Level.INFO, msg);
    }

    public void sysInfo(String msg, Throwable thrown) {
        sysLog(Level.INFO, msg, thrown);
    }

    public void severe(String msg) {
        log(Level.SEVERE, msg);
    }

    public void severe(String msg, Throwable thrown) {
        log(Level.SEVERE, msg, thrown);
    }

    public void warning(String msg) {
        log(Level.WARNING, msg);
    }

    public void warning(String msg, Throwable thrown) {
        log(Level.WARNING, msg, thrown);
    }

    public void info(String msg) {
        log(Level.INFO, msg);
    }

    public void info(String msg, Throwable thrown) {
        log(Level.INFO, msg, thrown);
    }

//        Level.CONFIG, Level.FINE,
//                Level.FINER, Level.FINEST
//    public void severe(String loggerTitle, String msg) {
//        log(loggerTitle, Level.SEVERE, msg);
//    }
//
//    public void severe(String loggerTitle, String msg, Throwable thrown) {
//        log(loggerTitle, Level.SEVERE, msg, thrown);
//    }
}
