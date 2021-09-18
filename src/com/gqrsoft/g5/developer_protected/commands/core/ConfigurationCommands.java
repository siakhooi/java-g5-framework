/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author Ng Siak Hooi
 */
public class ConfigurationCommands extends AbstractCommandComponent {

    public void clear() {
        getFormControl().core.config.clear();
    }

    public void loadFile(String fileName) throws ConfigurationException {
        getFormControl().core.config.loadFile(fileName);
    }

    public void loadFile(File file) throws ConfigurationException {
        getFormControl().core.config.loadFile(file);
    }

    private PropertiesConfiguration getConf() {
        return getFormControl().core.config.getConfiguration();
    }

    public String getString(String key, String defaultValue) {
        return getConf().getString(key, defaultValue);
    }

    public int getInteger(String key, int defaultValue) {
        return getConf().getInt(key, defaultValue);
    }

    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        return getConf().getBigInteger(key, defaultValue);
    }

    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        return getConf().getBigDecimal(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getConf().getBoolean(key, defaultValue);
    }
}
