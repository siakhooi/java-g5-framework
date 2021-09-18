/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

/**
 *
 * @author Ng Siak Hooi
 */
public class UrlCodecCommands {

    URLCodec url = new URLCodec();

    public String decode(String pString) throws DecoderException {
        return url.decode(pString);
    }

    public String decode(String pString, String charset) throws UnsupportedEncodingException, DecoderException {
        return url.decode(pString, charset);
    }

    public String encode(String pString) throws EncoderException {
        return url.encode(pString);
    }

    public String encode(String pString, String charset) throws UnsupportedEncodingException {
        return url.encode(pString, charset);
    }
}
