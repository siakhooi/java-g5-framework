/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CommandLineArgumentsCommands extends AbstractCommandComponent {

    public void addOption(
            String shortOption,
            String longOption,
            boolean hasValue,
            String description) {
        getFormControl().core.argv.addOption(
                shortOption,
                longOption,
                hasValue,
                description);
    }

    public void parse() throws ParseException {
        getFormControl().core.argv.parse();
    }

    public boolean hasOption(String option) {
        return getFormControl().core.argv.cmdLine.hasOption(option);
    }

    public String getOptionValue(String option) {
        return getFormControl().core.argv.cmdLine.getOptionValue(option);
    }

    public String getOptionValue(String option, String defaultValue) {
        return getFormControl().core.argv.cmdLine.getOptionValue(option, defaultValue);
    }

    public String[] getOptionValues(String option) {
        return getFormControl().core.argv.cmdLine.getOptionValues(option);
    }

    public void printHelp(String applicationPath) {
        getFormControl().core.argv.printHelp(applicationPath);
    }

    public String getHelp(String commandLineSyntax, int width) {
        return getFormControl().core.argv.getHelp(commandLineSyntax, width);
    }
}
