/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author Ng Siak Hooi
 */
public class LogManager {

    public void attachLogger(String loggerTitle, String fileName) throws IOException {
        FileHandler fh = new FileHandler(fileName, true);
        Logger.getLogger(loggerTitle).addHandler(fh);
    }

    public void attachGlobal(String fileName) throws IOException {
        FileHandler fh = new FileHandler(fileName, true);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).addHandler(fh);
    }

    public void attachEngine(String fileName) throws IOException {
        FileHandler fh = new FileHandler(fileName, true);
        Logger.getLogger(EngineConfiguration.Log.G5SYSTEM_LOGGER_NAME).addHandler(fh);
    }

    public Logger getGlobalLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    public Logger getLogger(String loggerTitle) {
        return Logger.getLogger(loggerTitle);
    }

    public Logger getEngineLogger() {
        return Logger.getLogger(EngineConfiguration.Log.G5SYSTEM_LOGGER_NAME);
    }
}
