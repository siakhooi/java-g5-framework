/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 *
 * @author Ng Siak Hooi
 */
public class WebCommands extends AbstractCommandComponent {
    /** usage
     * 1) GET
     * init("http://www.mysoftwarehouse.com/getuserlist.php?a=3&amp;b=3");
     * setDoInput(true);
     * connect();
     * InputStream is=getInputStream();
     * or
     * byte[] b=getAllBytes();
     * ...
     * close();
     * 
     * 2) POST
     * init("http://www.mysoftwarehouse.com/getuserlist.php");
     * setDoInput(true);
     * setDoOutput(true);
     * connect();
     * OutputStream os=getOutputStream();
     * ...
     * flushOutput();
     * InputStream is=getInputStream();
     * or
     * byte[] b=getAllBytes();
     * ...
     * close();
     * 
     * 3) multi part POST
     * init("http://www.mysoftwarehouse.com/getuserlist.php");
     * setDoInput(true);
     * useMultipart();
     * connect();
     * addPart("name", "g5");
     * addPart("uploadfile", "filename", inputstream);
     * addPart("uploadfile", File);
     * ...
     * sendMultipart();
     * InputStream is=getInputStream();
     * or
     * byte[] b=getAllBytes();
     * ...
     * close();
     * 
     */
//    private URL url;
    protected URLConnection urlConnection;
    private String boundary;
    private ByteArrayOutputStream baos;

    public void init(String url) throws MalformedURLException, URISyntaxException, Exception {
        init(new URL(url));
    }

    public void init(URL url) throws URISyntaxException, Exception {
        List<Proxy> p = getProxyList(url);
        urlConnection = tryConnection(url, p);
    }

    private List<Proxy> getProxyList(URL url) throws URISyntaxException {
        return ProxySelector.getDefault().select(url.toURI());
    }

    private URLConnection tryConnection(URL url, List<Proxy> proxy) throws Exception {
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

    public void setDoOutput(boolean doOutput) {
        urlConnection.setDoOutput(doOutput);
    }

    public void setDoInput(boolean doInput) {
        urlConnection.setDoOutput(doInput);
    }

    public void connect() throws IOException {
        urlConnection.connect();
    }

    public void close() {
    }

    public InputStream getInputStream() throws IOException {
        return urlConnection.getInputStream();
    }

    public byte[] getAllBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = getInputStream().read(buf)) > 0) {
            bos.write(buf, 0, len);
        }
        return bos.toByteArray();
    }

    public OutputStream getOutputStream() throws IOException {
        return urlConnection.getOutputStream();
    }

    public void flushOutput() throws IOException {
        urlConnection.getOutputStream().flush();
    }

    public void useMultipart() {
        boundary = getFormControl().cmd.data.repeatString("-", 20) +
                getFormControl().cmd.random.getAlphaNumericString(20) +
                getFormControl().cmd.random.getAlphaNumericString(20) +
                getFormControl().cmd.random.getAlphaNumericString(20);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        baos = new ByteArrayOutputStream();
    }

    public void sendMultipart() throws IOException, IOException {
        finalBoundary();
        getOutputStream().write(baos.toByteArray());
        flushOutput();
    }

    public void addPart(String name, String value) throws IOException {
        boundary();
        writeName(name);
        println("");
        println(value);
    }

    public void addPart(String name, String filename, InputStream is) throws IOException {
        boundary();
        writeName(name, filename);
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

    private void writeName(String name) throws IOException {
        //newline();
        print("Content-Disposition: form-data; ");
        println("name=\"" + name + "\"");
    }

    private void writeName(String name, String filename) throws IOException {
        //newline();
        print("Content-Disposition: form-data; ");
        print("name=\"" + name + "\"; ");
        println("filename=\"" + filename + "\"");
        print("Content-Type: ");
        String type = URLConnection.guessContentTypeFromName(filename);
        if (type == null) {
            type = "application/octet-stream";
        }
        println(type);
    }

    private void add(InputStream in) throws IOException {
        byte[] buf = new byte[500000];
        int nread;
        synchronized (in) {
            while ((nread = in.read(buf, 0, buf.length)) >= 0) {
                baos.write(buf, 0, nread);
            //      total += nread;
            //      total += nread;
            }
        }
        baos.flush();
        buf = null;
    }
}
