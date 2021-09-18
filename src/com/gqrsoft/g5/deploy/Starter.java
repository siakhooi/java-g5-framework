/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.deploy;

import com.gqrsoft.g5.kernel.core.Core;

/**
 * The <code>Starter</code> class is the engine starting object. 
 * All application using this engine required to initiate this class, and 
 * register all required application information/classes, that is 
 * {@link ApplicationControlInterface}, {@link ApplicationInformationInterface},
 * and command line argurments, before call the <code>run()</code> 
 * method.
 * 
 * @author Ng Siak Hooi
 */
public final class Starter {

    private Core core;

    public Starter() {
        core = new Core();
        core.init(this);
    }

    /**
     * 
     * @param applicationInformation 
     */
    public void init(ApplicationInformationInterface applicationInformation) {
        core.init(applicationInformation);
    }

    /**
     * 
     * @param applicationControl
     */
    public void init(ApplicationControlInterface applicationControl) {
        core.init(applicationControl);
    }

    /**
     * register the command line arguments with the engine.
     * the <code>commandLineArgument</code> parameter is a 
     * <code>String[]</code> array passed by JRE into 
     * <code>public static void main(String[] argv)</code> method.
     * 
     * @param commandLineArgument
     */
    public void init(String[] commandLineArgument) {
        core.argv.setArgumentArray(commandLineArgument);
    }

    /**
     * start the engine. The application control passed to engine until engine 
     * exit.
     */
    public final void run() {
        core.run();
    }
}
