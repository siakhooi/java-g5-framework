/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.awt.AWTException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SysTrayCommands extends AbstractCommandComponent {

    public void show(SystemTrayMenuForm userForm) throws AWTException {
        getFormControl().core.sysTray.execute(getFormControl(), userForm);
    }
}
