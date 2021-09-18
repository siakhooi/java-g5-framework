/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.parameters;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class GlobalParameters {

    public HashMap<String, Object> objects = new HashMap<String, Object>();
    public HashMap<String, String> texts = new HashMap<String, String>();
    public HashMap<String, Integer> integers = new HashMap<String, Integer>();
    public HashMap<String, Long> longs = new HashMap<String, Long>();
    public HashMap<String, Calendar> calendars = new HashMap<String, Calendar>();
    public HashMap<String, BigDecimal> decimals = new HashMap<String, BigDecimal>();
    public HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
    public HashMap<String, File> files = new HashMap<String, File>();
    public HashMap<String, Image> images = new HashMap<String, Image>();
    public HashMap<String, Color> colors = new HashMap<String, Color>();
    public HashMap<String, String[]> textArrays = new HashMap<String, String[]>();
    public HashMap<String, int[]> intArrays = new HashMap<String, int[]>();
    public HashMap<String, Object[]> objectArrays = new HashMap<String, Object[]>();
    //
    public HashMap<String, Vector> vectors = new HashMap<String, Vector>();
    public HashMap<String, ResultSet> sqlResultsets = new HashMap<String, ResultSet>();
    public HashMap<String, PreparedStatement> sqlStatements = new HashMap<String, PreparedStatement>();
}
