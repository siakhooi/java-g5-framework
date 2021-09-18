/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 *
 * @author Ng Siak Hooi
 */
public class ArgumentManager {

    private Options opts = new Options();
    private CommandLineParser parser;
    public CommandLine cmdLine;
    private String[] argv;

    public void addOption(String shortOption, String longOption, boolean hasValue, String description) {
        opts.addOption(shortOption, longOption, hasValue, description);
    }

    public void setArgumentArray(String[] argv) {
        this.argv = argv;
    }

    public void printHelp(String commandLineSyntax) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(commandLineSyntax, opts);
    }

    public String getHelp(String commandLineSyntax, int width) {
        HelpFormatter formatter = new HelpFormatter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos, true);
        //int width = 80;
        String header = "";
        String footer = "";
        int leftPad = 0;
        int descPad = 0;
        formatter.printHelp(pw, width, commandLineSyntax, header,
                opts, leftPad, descPad, footer);
        pw.flush();
        return baos.toString();
    }

    public void parse() throws ParseException {
        parser = new PosixParser();
        cmdLine =
                parser.parse(opts, argv);
    }
}
