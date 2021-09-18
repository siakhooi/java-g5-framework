/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.forms;

import com.gqrsoft.g5.developer.form.ImageForm2;
import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class MainScreen extends ImageForm2 {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return MainScreenAction.class;
    }

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return MainMenu.class;
    }


    @Override
    public void onEscapeKeyPressed() {
        //cmd.form.closeApplication(false);
        cmd.form.closeForm();
    }

    @Override
    public String getFormTitle() {
        return "MainScreen.title";
    }

    @Override
    public Image getImage() {
        return cmd.image.createImageIcon(
                MainScreen.class, ToolkitConfiguration.getLogoURL()).getImage();
    }
}
