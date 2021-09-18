/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonEventInterface;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public abstract class AbstractButtonForm extends AbstractUserForm
        implements ButtonEventInterface {

    public ButtonsBuilder buttons = new ButtonsBuilder(this);

    /**
     * Similar to {@link com.gqrsoft.g5.developer.form.BlankForm#buildBlankForm(JPanel parent)}
     * @param parent
     */
    public abstract void buildButtonForm(JPanel parent);

    public abstract boolean showButtons();
}
