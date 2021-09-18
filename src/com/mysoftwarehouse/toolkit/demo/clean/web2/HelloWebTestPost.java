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
public class HelloWebTestPost extends WebForm {

    @Override
    protected void execute() throws ProcessException {
        super.init("http://www.mysoftwarehouse.com/j2me/getprojectlist.php");
        super.setDoInput(true);
        super.useMultipart();
        super.connect();
        super.addPart("name1", "g5");
        super.addPart("name2", "g5");
        //super.addPart("uploadfile", "filename", inputstream);
        //super.addPart("uploadfile", new File("/temp/test.jpg"));
        super.addPart("uploadfile2", "testimage.jpg", HelloWebTestPost.class.getResourceAsStream("/com/mysoftwarehouse/demo/logo.jpg"));
        super.sendMultipart();
        byte[] b = getAllBytes();
        super.close();
        cmd.debug.println(new String(b));

    }

    @Override
    public String getFormTitle() {
        return "HelloWebTestPost.title";
    }
}
