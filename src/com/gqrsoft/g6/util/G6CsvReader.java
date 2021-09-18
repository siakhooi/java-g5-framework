/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 *
 * @author SHNG
 */
public class G6CsvReader {

    CSVReader reader;
    private char separator = CSVParser.DEFAULT_SEPARATOR;
    private char quotechar = CSVParser.DEFAULT_QUOTE_CHARACTER;
    private int skipline = CSVReader.DEFAULT_SKIP_LINES;
    private boolean strictQuotes = CSVParser.DEFAULT_STRICT_QUOTES;
    private char escapeChar = CSVParser.DEFAULT_ESCAPE_CHARACTER;
    private boolean ignoreLeadingWhiteSpace = CSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE;

    public void setStrictQuotes(boolean b) {
        this.strictQuotes = b;
    }

    public void setEscapeChar(char c) {
        this.escapeChar = c;
    }

    public void setIgnoreLeadingWhiteSpace(boolean b) {
        this.ignoreLeadingWhiteSpace = b;
    }

    public void setSeparator(char c) {
        this.separator = c;
    }

    public void setQuoteChar(char c) {
        this.quotechar = c;
    }

    public void setSkipLine(int i) {
        this.skipline = i;
    }

    public void load(Reader r) {
        reader = new CSVReader(r, separator, quotechar, escapeChar, skipline,
                strictQuotes, ignoreLeadingWhiteSpace);
    }

    public void load(String filename) throws FileNotFoundException {
        load(new FileReader(filename));
    }

    public void load(File file) throws FileNotFoundException {
        load(new FileReader(file));
    }

    public void load(InputStream is) {
        load(new InputStreamReader(is));
    }

    public void load(char[] data) {
        load(new CharArrayReader(data));
    }

    public List<String[]> readAll() throws IOException {
        return reader.readAll();
    }

    public String[] readNext() throws IOException {
        return reader.readNext();
    }
}
