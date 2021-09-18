/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.commands.Commands;

/**
 *
 * @author Ng Siak Hooi
 */
public interface UserFormInterface {

    public Commands cmd();

    /**
     * return Form Title in I18n format to be display at window title bar.
     * @return
     */
    public String getFormI18nTitle();

    /**
     * method to initialize <code>WindowThread</code> informations.
     * All <code>userForm</code> execution will call this method.
     * 
     * @param newThread is true if this <code>userForm </code>
     * is a new WindowsThread.
     */
    public void initThread(boolean newThread);

    /**
     * 
     */
    public void exitThread();

    /**
     * 
     */
    public void onEscapeKeyPressed();

    /**
     * to initialize components before <code>userForm</code> is shown.
     */
    public void initForm();

    /**
     * event after <code>userForm</code> is shown, 
     * useful for auto started process.
     */
    public void eventAfterVisible();

    public void eventBeforeVisible();

    /**
     * return Locale Base Name to be used in this <code>userForm</code>.
     * By default, engine will use Application Default Locale Base Name. 
     * {@link com.gqrsoft.g5.deploy.ApplicationControlInterface#getApplicationLevelLocaleBaseName() }
     * 
     * @return
     */
    public String getFormLevelLocaleBaseName();

    /**
     * used by engine.
     * @param value
     */
    public void setCommands(Commands value);

    /**
     * return the <code>MenuForm</code> class that will be used 
     * to generate Menu Bar.
     * 
     * @return
     */
    public Class<? extends MenuForm> getMenuForm();

    /**
     * return the <code>MenuForm</code> class that will be used 
     * to generate Menu Toolbar Bar.
     * 
     * @return
     */
    public Class<? extends MenuForm> getMenuFormForToolbar();

    /**
     * return the <code>UserFormInterface</code> class that to be 
     * embedded in the Line End (Right) side of main windows.
     * @return
     */
    public Class<? extends UserFormInterface> getRightForm();

    /**
     * return the <code>UserFormInterface</code> class that to be 
     * embedded in the Line Start (Left) side of main windows.
     * @return
     */
    public Class<? extends UserFormInterface> getLeftForm();

    /**
     * return the <code>UserFormInterface</code> class that to be 
     * embedded in the Page Start(Top) side of main windows.
     * @return
     */
    public Class<? extends UserFormInterface> getTopForm();

    /**
     * return the <code>UserFormInterface</code> class that to be 
     * embedded in the Page End (Bottom) side of main windows.
     * @return
     */
    public Class<? extends UserFormInterface> getBottomForm();

    /**
     * event when this <code>userForm</code> is start.
     */
    public void onInEnter();

    /**
     * event when this <code>userForm</code> is close.
     */
    public void onInExit();

    /**
     * event when a child <code>userForm</code> is start.
     */
    public void onOutExit();

    /**
     * event when a child <code>userForm</code> is close 
     * and back to this <code>userForm</code>.
     */
    public void onOutEnter();

    /**
     * event when other <code>userForm</code> in same window send signal.
     * 
     * @param signalNumber
     * @see com.gqrsoft.g5.maskclassname.commands.form#broadcastSignal(final int signalNumber)
     */
    public void receiveSignal(int signalNumber);

    public void receiveSystemSignal(int signalNumber, String signalCode);
    
    public void receiveGlobalSignal(int signalNumber);
}
