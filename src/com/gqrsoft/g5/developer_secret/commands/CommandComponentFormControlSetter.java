/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.commands;

import com.gqrsoft.g5.developer_protected.commands.Commands;
import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public class CommandComponentFormControlSetter {

    public static void setFormControl(Commands cmd, FormControl fc) {
        //super.setFormControl(fc);
        cmd.appinfo.setFormControl(fc);
        cmd.log.setFormControl(fc);
        cmd.config.setFormControl(fc);
        cmd.argv.setFormControl(fc);
        cmd.cron.setFormControl(fc);
        cmd.lang.setFormControl(fc);
        cmd.dba.setFormControl(fc);
        cmd.engine.setFormControl(fc);
        cmd.in = fc.in;
        cmd.out = fc.out;
        cmd.global = fc.core.param;
        cmd.thread = fc.threadControl.param;
        cmd.local = fc.local;

        cmd.parent.setFormControl(fc);
        cmd.frame.setFormControl(fc);
        cmd.common.setFormControl(fc);
        cmd.form.setFormControl(fc);
        cmd.process.setFormControl(fc);
        cmd.list.setFormControl(fc);
        cmd.entry.setFormControl(fc);
        cmd.report.setFormControl(fc);
        cmd.mode.setFormControl(fc);

        cmd.button.setFormControl(fc);
        cmd.systray.setFormControl(fc);
        cmd.signal.setFormControl(fc);

        cmd.sys.setFormControl(fc);
        cmd.random.setFormControl(fc);
        cmd.looknfeel.setFormControl(fc);
        cmd.web.setFormControl(fc);
        cmd.cal.setFormControl(fc);

        cmd.db.setFormControl(fc);

    }
}
