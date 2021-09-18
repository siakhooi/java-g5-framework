/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import javax.jnlp.BasicService;
import javax.jnlp.ClipboardService;
import javax.jnlp.ExtendedService;
import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.FileSaveService;
import javax.jnlp.PersistenceService;
import javax.jnlp.PrintService;
import javax.jnlp.ServiceManager;
import javax.jnlp.SingleInstanceListener;
import javax.jnlp.SingleInstanceService;
import javax.jnlp.UnavailableServiceException;

/**
 *
 * @author Ng Siak Hooi
 */
public class JnlpServiceCommands {

    private BasicService getBasicService() throws UnavailableServiceException {
        return (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
    }

    private ClipboardService getClipboardService() throws UnavailableServiceException {
        return (ClipboardService) ServiceManager.lookup("javax.jnlp.ClipboardService");
    }

    private ExtendedService getExtendedService() throws UnavailableServiceException {
        return (ExtendedService) ServiceManager.lookup("javax.jnlp.ExtendedService");
    }

    private FileOpenService getFileOpenService() throws UnavailableServiceException {
        return (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
    }

    private FileSaveService getFileSaveService() throws UnavailableServiceException {
        return (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
    }

    private PersistenceService getPersistenceService() throws UnavailableServiceException {
        return (PersistenceService) ServiceManager.lookup("javax.jnlp.PersistenceService");
    }

    private PrintService getPrintService() throws UnavailableServiceException {
        return (PrintService) ServiceManager.lookup("javax.jnlp.PrintService");
    }

    private SingleInstanceService getSingleInstanceService() throws UnavailableServiceException {
        return (SingleInstanceService) ServiceManager.lookup("javax.jnlp.SingleInstanceService");
    }

    public java.net.URL getCodeBase() throws UnavailableServiceException {
        return getBasicService().getCodeBase();
    }

    public boolean isOffline() throws UnavailableServiceException {
        return getBasicService().isOffline();
    }

    public boolean isWebBrowserSupported() throws UnavailableServiceException {
        return getBasicService().isWebBrowserSupported();
    }

    public boolean showWebDocument(java.net.URL url) throws UnavailableServiceException {
        return getBasicService().showDocument(url);
    }

    public java.awt.datatransfer.Transferable getClipboardContents() throws UnavailableServiceException {
        return getClipboardService().getContents();
    }

    public void setClipboardContents(java.awt.datatransfer.Transferable contents) throws UnavailableServiceException {
        getClipboardService().setContents(contents);
    }

    public FileContents openFile(java.io.File file) throws java.io.IOException, UnavailableServiceException {
        return getExtendedService().openFile(file);
    }

    public FileContents[] openFiles(java.io.File[] files) throws java.io.IOException, UnavailableServiceException {
        return getExtendedService().openFiles(files);
    }

    public FileContents openFileDialog(
            java.lang.String pathHint, java.lang.String[] extensions)
            throws java.io.IOException, UnavailableServiceException {
        return getFileOpenService().openFileDialog(pathHint, extensions);
    }

    public FileContents[] openMultiFileDialog(
            java.lang.String pathHint, java.lang.String[] extensions)
            throws java.io.IOException, UnavailableServiceException {
        return getFileOpenService().openMultiFileDialog(pathHint, extensions);
    }

    public FileContents saveFileDialog(
            java.lang.String pathHint, java.lang.String[] extensions,
            java.io.InputStream stream, java.lang.String name)
            throws java.io.IOException, UnavailableServiceException {
        return getFileSaveService().saveFileDialog(pathHint, extensions, stream, name);
    }

    public FileContents saveAsFileDialog(
            java.lang.String pathHint, java.lang.String[] extensions,
            FileContents contents)
            throws java.io.IOException, UnavailableServiceException {
        return getFileSaveService().saveAsFileDialog(pathHint, extensions, contents);
    }

    public long createPersistence(java.net.URL url, long maxsize)
            throws java.net.MalformedURLException, java.io.IOException,
            UnavailableServiceException {
        return getPersistenceService().create(url, maxsize);
    }

    public FileContents getPersistence(java.net.URL url)
            throws java.net.MalformedURLException, java.io.IOException,
            java.io.FileNotFoundException, UnavailableServiceException {
        return getPersistenceService().get(url);
    }

    public void deletePersistence(java.net.URL url)
            throws java.net.MalformedURLException,
            java.io.IOException, UnavailableServiceException {
        getPersistenceService().delete(url);
    }

    public java.lang.String[] getPersistenceNames(java.net.URL url)
            throws java.net.MalformedURLException,
            java.io.IOException, UnavailableServiceException {
        return getPersistenceService().getNames(url);
    }

    public int getPersistenceTag(java.net.URL url)
            throws java.net.MalformedURLException,
            java.io.IOException, UnavailableServiceException {
        return getPersistenceService().getTag(url);
    }

    public void setPersistenceTag(java.net.URL url, int tag)
            throws java.net.MalformedURLException,
            java.io.IOException, UnavailableServiceException {
        getPersistenceService().setTag(url, tag);
    }

    public java.awt.print.PageFormat getDefaultPage() throws UnavailableServiceException {
        return getPrintService().getDefaultPage();
    }

    public java.awt.print.PageFormat showPageFormatDialog(java.awt.print.PageFormat page) throws UnavailableServiceException {
        return getPrintService().showPageFormatDialog(page);
    }

    public boolean print(java.awt.print.Pageable document) throws UnavailableServiceException {
        return getPrintService().print(document);
    }

    public boolean print(java.awt.print.Printable painter) throws UnavailableServiceException {
        return getPrintService().print(painter);
    }

    public void addSingleInstanceListener(SingleInstanceListener sil) throws UnavailableServiceException {
        getSingleInstanceService().addSingleInstanceListener(sil);
    }

    public void removeSingleInstanceListener(SingleInstanceListener sil) throws UnavailableServiceException {
        getSingleInstanceService().removeSingleInstanceListener(sil);
    }
}


