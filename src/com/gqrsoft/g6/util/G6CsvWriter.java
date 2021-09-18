/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author SHNG
 */
public class G6CsvWriter {

    private char separator = CSVWriter.DEFAULT_SEPARATOR;
    private char quotechar = CSVWriter.DEFAULT_QUOTE_CHARACTER;
    private char escapechar = CSVWriter.DEFAULT_ESCAPE_CHARACTER;
    private String lineend = CSVWriter.DEFAULT_LINE_END;

    public void setSeparator(char c) {
        this.separator = c;
    }

    public void setQuoteChar(char c) {
        this.quotechar = c;
    }

    public void setEscapeChar(char c) {
        this.escapechar = c;
    }

    public void noQuoteChar() {
        this.quotechar = CSVWriter.NO_QUOTE_CHARACTER;
    }

    public void noEscapeChar() {
        this.escapechar = CSVWriter.NO_ESCAPE_CHARACTER;
    }

    public void setLineEnd(String s) {
        this.lineend = s;
    }
    CSVWriter writer;
    CharArrayWriter caw;

    public void init() {
        caw = new CharArrayWriter();
        writer = new CSVWriter(caw, separator, quotechar, escapechar, lineend);
    }

    public void save(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        writer.flush();
        writer.close();
        caw.flush();
        fw.write(caw.toCharArray(), 0, caw.toCharArray().length);
        fw.flush();
        fw.close();
    }

    public void save(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        writer.flush();
        writer.close();
        caw.flush();
        fw.write(caw.toCharArray(), 0, caw.toCharArray().length);
        fw.flush();
        fw.close();
    }

    public void save(OutputStream os) throws IOException {
        OutputStreamWriter fw = new OutputStreamWriter(os);
        writer.flush();
        writer.close();
        caw.flush();
        fw.write(caw.toCharArray(), 0, caw.toCharArray().length);
        fw.flush();
        fw.close();
    }

    public char[] save() {
        return caw.toCharArray();
    }

    public void write(String[] s) {
        writer.writeNext(s);
    }

    public void write(List<String[]> s) {
        writer.writeAll(s);
    }

    public void write(ResultSet rs, boolean includeHeaders) throws SQLException, IOException {
        writer.writeAll(rs, includeHeaders);
    }
}
