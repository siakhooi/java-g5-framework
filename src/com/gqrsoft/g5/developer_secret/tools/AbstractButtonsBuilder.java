/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.tools;

import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.kernel.framepanel.button.G5JButton;
import java.awt.Component;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class AbstractButtonsBuilder {

    public final Vector<Component> allComponents = new Vector<Component>();
    public final HashMap<String, G5JButton> allButtonsByName =
            new HashMap<String, G5JButton>();
    public G5JButton defaultButton;
    public FormEnum.ButtonHorizontalLocation horizontalLocation =
            FormEnum.ButtonHorizontalLocation.CENTER;
    public FormEnum.ButtonPageLocation verticalLocation =
            FormEnum.ButtonPageLocation.BOTTOM;
    protected G5JButton currentButton;
}
