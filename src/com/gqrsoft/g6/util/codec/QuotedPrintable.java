/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;

/**
 *
 * @author SHNG
 */
public class QuotedPrintable {

    private QuotedPrintableCodec qpc = new QuotedPrintableCodec();

    public String quotedPrintableEncode(String text) throws EncoderException {
        return qpc.encode(text);
    }

    public String quotedPrintableEncode(String text, String charset) throws UnsupportedEncodingException {
        return qpc.encode(text, charset);
    }

    public String quotedPrintableDecode(String text) throws DecoderException {
        return qpc.decode(text);
    }

    public String quotedPrintableDecode(String text, String charset) throws DecoderException, UnsupportedEncodingException {
        return qpc.decode(text, charset);
    }
}
