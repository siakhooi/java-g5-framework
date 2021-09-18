/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

/**
 *
 * @author Ng Siak Hooi
 */
public class StringCommands {

    public String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    public String remove0D0A(String source) {
        return source.replaceAll("\n", "").replaceAll("\r", "");
    }
}
