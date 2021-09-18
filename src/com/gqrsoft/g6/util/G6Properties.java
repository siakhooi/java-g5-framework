/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author SHNG
 */
public class G6Properties {

    public static Properties getFile(String filePath) throws IOException {
        Properties p = new Properties();
        p.load(new FileReader(filePath));
        return p;
    }
    public static Properties getJarFile(Class clazz, String path) throws IOException{
        Properties p = new Properties();
        p.load(clazz.getResourceAsStream(path));
        return p;
    }
}
