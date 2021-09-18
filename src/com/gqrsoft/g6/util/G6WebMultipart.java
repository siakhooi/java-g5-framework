/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import com.gqrsoft.g6.util.values.G6String;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 *
 * @author SHNG
 */
public class G6WebMultipart {

    private URLConnection urlConnection;
    private String boundary;
    private ByteArrayOutputStream baos;

    public void init(URL url) throws Exception {
        List<Proxy> p = ProxySelector.getDefault().select(url.toURI());
        urlConnection = G6Web.tryConnection(url, p);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        G6Random r = new G6Random();
        boundary = G6String.repeat("-", 20) +
                r.getAlphaNumericString(20) +
                r.getAlphaNumericString(20) +
                r.getAlphaNumericString(20);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        urlConnection.connect();

        baos = new ByteArrayOutputStream();
    }

    public InputStream submit() throws IOException {
        finalBoundary();
        urlConnection.getOutputStream().write(baos.toByteArray());
        urlConnection.getOutputStream().flush();
        return urlConnection.getInputStream();

    }

    public void addPart(String name, String value) throws IOException {
        boundary();
        print("Content-Disposition: form-data; ");
        println("name=\"" + name + "\"");
        println("");
        println(value);
    }

    private String getContentType(String filename) {
        String type = URLConnection.guessContentTypeFromName(filename);
        if (type == null) {
            type = "application/octet-stream";
        }
        return type;
    }

    public void addPart(String name, String filename, InputStream is) throws IOException {
        boundary();
        print("Content-Disposition: form-data; ");
        print("name=\"" + name + "\"; ");
        println("filename=\"" + filename + "\"");
        println("Content-Type: " + getContentType(filename));
        println("");
        add(is);
        println("");
    }

    public void addPart(String name, File file) throws FileNotFoundException, IOException {
        addPart(name, file.getPath(), new FileInputStream(file));
    }

    private void println(String s) throws IOException {
        print(s);
        print("\r\n");
    }

    private void print(String s) throws IOException {
        baos.write(s.getBytes());
    }

    private void boundary() throws IOException {
        println("--" + boundary);
    }

    private void finalBoundary() throws IOException {
        println("--" + boundary + "--");
    }

    private void add(InputStream in) throws IOException {
        byte[] buf = new byte[500000];
        int nread;
        synchronized (in) {
            while ((nread = in.read(buf, 0, buf.length)) >= 0) {
                baos.write(buf, 0, nread);
            }
        }
        baos.flush();
        buf = null;
    }
}
