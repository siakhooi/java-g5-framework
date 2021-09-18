/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractUserForm;
import javax.swing.JPanel;

/**
 * The most basic form. it only has basic window handling mechanism, 
 * all components have to be programmed at JDK level.
 * 
 * @author Ng Siak Hooi
 */
public abstract class BlankForm extends AbstractUserForm {

    /**
     * initialize the GUI of this <code>userForm</code>. All components in this 
     * <code>userForm</code> have to sit inside <code>parent</code>.
     * @param parent is the main container for all components in this 
     * <code>userForm</code>.
     */
    public abstract void buildBlankForm(JPanel parent);
}
