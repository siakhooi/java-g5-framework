/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.clean.web2;

import com.gqrsoft.g5.developer.form.WebForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloWeb extends WebForm {

    @Override
    protected void execute() throws ProcessException {
        super.init("http://www.mysoftwarehouse.com/j2me/login.php");
        super.setDoInput(true);
        super.connect();
//     * InputStream is=getInputStream();
        //   * or
        byte[] b = getAllBytes();
        super.close();
        cmd.debug.println(new String(b));
    }

    @Override
    public String getFormTitle() {
        return "HelloWeb.title";
    }
}
