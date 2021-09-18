/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import java.io.File;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author Ng Siak Hooi
 */
//TODO (future) allow multi configuration files, using CombinedConfiguration or CompositeConfiguration
public class ConfigurationManager {

    PropertiesConfiguration pc = new PropertiesConfiguration();

    public PropertiesConfiguration getConfiguration() {
        return pc;
    }

    public void clear() {
        pc = new PropertiesConfiguration();
    }

    public void loadFile(String fileName) throws ConfigurationException {
        pc = new PropertiesConfiguration(fileName);
    }

    public void loadFile(File file) throws ConfigurationException {
        pc = new PropertiesConfiguration(file);
    }
}
