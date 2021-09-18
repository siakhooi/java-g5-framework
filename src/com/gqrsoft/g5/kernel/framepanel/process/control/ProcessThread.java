/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.control;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessThread extends Thread {

    ProcessForm pf;
    ProcessFormFramePanel pbf;

    public ProcessThread(FormControl fc) {
        this.pf = fc.processForm;
        this.pbf = fc.processPanel;
    }

    @Override
    public void run() {
        try {
            pf.run();
            pbf.taskCompleted();
        } catch (ProcessException ex) {
            pbf.taskCancelled(ex);
        }
    }
}
