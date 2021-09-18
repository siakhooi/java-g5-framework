/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.abstractform;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractUserForm;
import com.gqrsoft.g5.kernel.framepanel.process.control.ProcessTree;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PrivateAbstractProcessForm extends AbstractUserForm {

    public final ProcessTree processTree = new ProcessTree();
}
