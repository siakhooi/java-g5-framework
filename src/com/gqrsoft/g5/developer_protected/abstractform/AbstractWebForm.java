/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
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
 * @deprecated 
 */
@Deprecated
public abstract class AbstractWebForm extends ProcessForm {

    @Override
    public final void init() {
        String i18nTitle = "WebForm.process.title";
        i18nTitle = cmd.lang.getSystemString(i18nTitle);
        super.addI18nProcess(i18nTitle, 1);
    }

    @Override
    public final void run() throws ProcessException {
        super.setMinorTotalCount(1);
        super.minorCompleted(2);//force to indeterminate mode
        execute();
        super.completed();
    }

    protected abstract void execute() throws ProcessException;
    private URL url;
    protected URLConnection urlConnection;
    private String boundary;
    private ByteArrayOutputStream baos;

    protected final void init(String url) throws ProcessException {
        try {
            init(new URL(url));
        } catch (MalformedURLException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final void init(URL url) throws ProcessException {
        String title;
        title = "WebForm.progress.InitURL";
        title = cmd.lang.getSystemString(title);
        super.setI18nProgressMessage(title);
        this.url = url;
        title = "WebForm.progress.RetrieveProxy";
        title = cmd.lang.getSystemString(title);
        super.setI18nProgressMessage(title);
        List<Proxy> p = getProxyList(url);
        title = "WebForm.progress.OpenURL";
        title = cmd.lang.getSystemString(title);
        super.setI18nProgressMessage(title);
        urlConnection = tryConnection(url, p);
    }

    protected final void setDoOutput(boolean doOutput) {
        urlConnection.setDoOutput(doOutput);
    }

    protected final void setDoInput(boolean doInput) {
        urlConnection.setDoOutput(doInput);
    }

    protected final void useMultipart() {
        boundary = cmd.data.repeatString("-", 20) +
                cmd.random.getAlphaNumericString(20) +
                cmd.random.getAlphaNumericString(20) +
                cmd.random.getAlphaNumericString(20);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        baos = new ByteArrayOutputStream();
    }

    private void println(String s) throws ProcessException {
        print(s);
        print("\r\n");
    }

    private void print(String s) throws ProcessException {
        try {
            baos.write(s.getBytes());
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    private void boundary() throws ProcessException {
        println("--" + boundary);
    }

    private void finalBoundary() throws ProcessException {
        println("--" + boundary + "--");
    }

    protected final void sendMultipart() throws ProcessException {
        try {
            finalBoundary();
            getOutputStream().write(baos.toByteArray());
            flushOutput();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    private void writeName(String name) throws ProcessException {
        //newline();
        print("Content-Disposition: form-data; ");
        println("name=\"" + name + "\"");
    }

    private void writeName(String name, String filename) throws ProcessException {
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

    private void add(InputStream in) throws ProcessException {
        try {
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
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final void addPart(String name, String value) throws ProcessException {
        boundary();
        writeName(name);
        println("");
        println(value);
    }

    protected final void addPart(String name, String filename, InputStream is) throws ProcessException {
        String title;
        title = "WebForm.progress.UploadFile.{0}";
        title = cmd.lang.getSystemString(title, filename);
        super.setI18nProgressMessage(title);

        boundary();
        writeName(name, filename);
        println("");
        add(is);
        println("");
    }

    protected final void addPart(String name, File file) throws ProcessException {
        try {
            addPart(name, file.getPath(), new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final void connect() throws ProcessException {
        try {
            String title;
            title = "WebForm.progress.Connect";
            title = cmd.lang.getSystemString(title);
            super.setI18nProgressMessage(title);
            urlConnection.connect();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final OutputStream getOutputStream() throws ProcessException {
        try {
            String title;
            title = "WebForm.progress.OpenOutputStream";
            title = cmd.lang.getSystemString(title);
            super.setI18nProgressMessage(title);
            return urlConnection.getOutputStream();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final void flushOutput() throws ProcessException {
        try {
            String title;
            title = "WebForm.progress.CloseOutputStream";
            title = cmd.lang.getSystemString(title);
            super.setI18nProgressMessage(title);
            urlConnection.getOutputStream().flush();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final InputStream getInputStream() throws ProcessException {
        try {
            String title;
            title = "WebForm.progress.OpenInputStream";
            title = cmd.lang.getSystemString(title);
            super.setI18nProgressMessage(title);
            return urlConnection.getInputStream();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    protected final void close() throws ProcessException {
        String title;
        title = "WebForm.progress.CloseConnection";
        title = cmd.lang.getSystemString(title);
        super.setI18nProgressMessage(title);
    }

    protected final byte[] getAllBytes() throws ProcessException {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = getInputStream().read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException ex) {
            throw new ProcessException(false, ex.getLocalizedMessage());
        }
    }

    private List<Proxy> getProxyList(URL url) throws ProcessException {
        try {
            return ProxySelector.getDefault().select(url.toURI());
        } catch (URISyntaxException ex) {
            String title = "WebForm.error.getProxyList.URISyntax.{0}";
            title = cmd.lang.getSystemString(title, ex.getLocalizedMessage());
            super.setMessage(title);
            throw new ProcessException(false, title);
        }
    }

    private URLConnection tryConnection(URL url, List<Proxy> proxy) throws ProcessException {
        for (int j = 0; j < proxy.size(); j++) {
            Proxy p = proxy.get(j);
            try {
                URLConnection uc = url.openConnection(p);
                return uc;
            } catch (IOException ex) {
            }
        }
        String title = "WebForm.error.NoConnection";
        title = cmd.lang.getSystemString(title);
        super.setMessage(title);
        throw new ProcessException(false, title);
    }
}
