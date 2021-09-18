/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author Ng Siak Hooi
 */
//TODO (future) add DBF reader/writer
public class CsvReaderCommands {

    CSVReader reader;
    private char separator = CSVParser.DEFAULT_SEPARATOR;
    private char quotechar = CSVParser.DEFAULT_QUOTE_CHARACTER;
    private int skipline = CSVReader.DEFAULT_SKIP_LINES;

    public void setSeparator(char c) {
        this.separator = c;
    }

    public void setQuoteChar(char c) {
        this.quotechar = c;
    }

    public void setSkipLine(int i) {
        this.skipline = i;
    }

    public void load(String filename) throws FileNotFoundException {
        reader = new CSVReader(new FileReader(filename), separator, quotechar, skipline);
    }

    public void load(File file) throws FileNotFoundException {
        reader = new CSVReader(new FileReader(file), separator, quotechar, skipline);
    }

    public void load(InputStream is) {
        reader = new CSVReader(new InputStreamReader(is), separator, quotechar, skipline);
    }

    public void load(char[] data) {
        reader = new CSVReader(new CharArrayReader(data), separator, quotechar, skipline);
    }

    public List readAll() throws IOException {
        return reader.readAll();
    }

    public String[] readNext() throws IOException {
        return reader.readNext();
    }
}
