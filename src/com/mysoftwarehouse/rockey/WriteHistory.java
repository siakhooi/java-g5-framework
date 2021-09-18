/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class WriteHistory {

    static void add(UserFormInterface form, int hardwareID) throws FileNotFoundException, IOException {
        File f1 = new File(form.cmd().sysprop.userDirectory());
        File f2 = new File(f1, "writehistory.lst");
        form.cmd().debug.println(f2.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(f2, true);
        PrintWriter pw = new PrintWriter(fos);
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = f.format(rightNow.getTime());
        s += ", " + hardwareID;
        pw.println(s);
        pw.flush();
        fos.flush();
        pw.close();
        fos.close();
    }
}
