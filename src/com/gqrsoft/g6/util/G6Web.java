/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import com.gqrsoft.g6.util.codec.G6URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.EncoderException;

/**
 *
 * @author SHNG
 */
public class G6Web {

    public String getParameters(HashMap<String, String> parameters) throws EncoderException {
        String s = null;
        G6URL u = new G6URL();
        for (String key : parameters.keySet()) {
            if (s == null) {
                s = "";
            } else {
                s += "&";
            }
            s += u.encode(key);
            s += "=";
            s += u.encode(parameters.get(key));
        }
        return s;
    }

    public URL getURL(String url) throws MalformedURLException {
        return new URL(url);
    }

    public InputStream GET(URL url) throws URISyntaxException, Exception {
        List<Proxy> p = ProxySelector.getDefault().select(url.toURI());
        URLConnection urlConnection = tryConnection(url, p);
        urlConnection.setDoInput(true);
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    public InputStream GET(String url, HashMap<String, String> parameters) throws URISyntaxException, Exception {
        String s = getParameters(parameters);
        return GET(getURL(url + "?" + s));
    }

    public InputStream POST(URL url, HashMap<String, String> parameters) throws URISyntaxException, Exception {
        return POST(url, getParameters(parameters).getBytes());
    }

    public InputStream POST(URL url, byte[] outdata) throws URISyntaxException, Exception {
        List<Proxy> p = ProxySelector.getDefault().select(url.toURI());
        URLConnection urlConnection = tryConnection(url, p);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        OutputStream os = urlConnection.getOutputStream();
        os.write(outdata);
        os.flush();
        return urlConnection.getInputStream();
    }

    public static URLConnection tryConnection(URL url, List<Proxy> proxy) throws Exception {
        for (int j = 0; j < proxy.size(); j++) {
            Proxy p = proxy.get(j);
            try {
                URLConnection uc = url.openConnection(p);
                return uc;
            } catch (IOException ex) {
            }
        }
        throw new Exception("Connection Not Found!");
    }
}
