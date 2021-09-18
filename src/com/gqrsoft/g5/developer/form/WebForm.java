/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer_protected.abstractform.AbstractWebForm;

/**
 *
 * @author Ng Siak Hooi
 * @deprecated 
 */
@Deprecated
public abstract class WebForm extends AbstractWebForm {

    @Override
    protected abstract void execute() throws ProcessException;
    /** usage
     * 1) GET
     * init("http://www.mysoftwarehouse.com/getuserlist.php?a=3&b=3");
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
}
