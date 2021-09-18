/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Ng Siak Hooi
 */
public class DataCommands {

    public String selectText(int i, String... values) {
        if (values.length > i) {
            return values[i];
        }
        return null;
    }

    public String boolean2String(boolean value) {
        return value ? EngineConfiguration.YES : EngineConfiguration.NO;
    }

    public String unNullString(String s) {
        return (s == null) ? "" : s;
    }

    public String ifNull(String s, String value) {
        return isNull(s) ? value : s;
    }

    public boolean isNull(String s) {
        if (s == null) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        return false;
    }

    public String int2String(int n) {
        return "" + n;
    }

    public String long2String(long n) {
        return "" + n;
    }

    public String repeatString(String s, int n) {
        StringBuffer sb = new StringBuffer();
        while (n-- > 0) {
            sb.append(s);
        }
        return sb.toString();
    }

//    public String formatDouble(double d, String mask) {
//        DecimalFormat temp = new DecimalFormat(mask);
//        return temp.format(d).trim();
//    }
    public boolean isTrue(String s) {
        String s1 = s.toUpperCase().trim();
        return s1.equals("Y") ||
                s1.equals("T") ||
                s1.equals("TRUE") ||
                s1.equals("YES") ||
                s1.equals("1");
    }

    public boolean isFalse(String s) {
        String s1 = s.toUpperCase().trim();
        return s1.equals("N") ||
                s1.equals("F") ||
                s1.equals("FALSE") ||
                s1.equals("NO") ||
                s1.equals("0");
    }

    public boolean isBoolean(String s) {
        return isTrue(s) || isFalse(s);
    }

    public String Color2Hex(Color n) {
        return long2Hex(Color2long(n), 6);
    }

    public String long2Hex(long i, int size) {
        String s = Long.toHexString(i).toUpperCase();
        if (s.length() > size) {
            return s.substring(s.length() - size);
        }
        while (s.length() < size) {
            s = "0" + s;
        }
        return s;
    }

    public long Color2long(Color n) {
        long r = n.getRed();
        long g = n.getGreen();
        long b = n.getBlue();
        r = r << 16;
        g = g << 8;
        long v = r | g | b;
        v = v & 0xffffff;
        return v;
    }

    public Color Hex2Color(String n) {
        return long2Color(Hex2long(n));
    }

    public Color long2Color(long n) {
        int b = (int) (n & 0xff);
        n = n >> 8;
        int g = (int) (n & 0xff);
        n = n >> 8;
        int r = (int) (n & 0xff);
        return new Color(r, g, b);
    }

    public long Hex2long(String s) {
        return Long.parseLong(s, 16);
    }

    public String Array2String(String s[], String separator) {
        if (s == null) {
            return "";
        }
        String j = "";
        for (int i = 0; i < s.length; i++) {
            if (i > 0) {
                j = j + separator;
            }
            j = j + s[i];
        }
        return j;
    }

    public String[] String2Array(String s, String separator) {
        return s.split(separator);
    }

    public byte[] inputStream2ByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = is.read(buffer)) >= 0) {
            ba.write(buffer, 0, len);
        }

        return ba.toByteArray();
    }
    public String inputStream2String(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
